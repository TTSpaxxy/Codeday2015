package com.cal.game.entity.item;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.ReflectionPool;
import com.cal.game.entity.Entity;
import com.cal.game.entity.LivingEntity;
import com.cal.game.entity.MainCharacter;
import com.cal.game.gfx.AnimLoader;
import com.cal.game.level.Layer;

import java.util.ArrayList;

/**
 * Created by acohen on 5/24/15.
 */
public class LaserGun extends Item {

    public GunType type;

    private int ammo;
    private float cooldown;

    public LaserGun(float xS, float yS, GunType type) {
        super(xS, yS, 32, 32);

        groundAnim = AnimLoader.loadAnim("Animation/Weapons.png", 19, 20, 1f);
        heldAnimRight = AnimLoader.loadAnim("Animation/Weapons.png", 19, 21, 1f / 2f);
        heldAnimLeft = AnimLoader.loadAnim("Animation/Weapons.png", 21, 23, 1f / 2f);

        this.type = type;
    }

    public void shoot(Entity owner) {
        if(!isHeld) return;
        if(cooldown == 0) {
            holder.projectiles.addActor(new Bullet(this, (holder.facingRight) ? 1 : -1, owner));
            cooldown = type.reloadSpeed;
        }
    }

    @Override
    public void act(float delta) {
        if(cooldown > 0) cooldown -= delta;
        if(cooldown < 0) cooldown = 0;

        super.act(delta);
    }

    public class Bullet extends Entity {

        Animation bulletTex = AnimLoader.loadAnim("Animation/Weapons.png", 18, 19, 1f);

        public boolean exists;
        public Entity owner;
        public Rectangle hitbox;

        public Bullet(LaserGun gun, int dir, Entity owner) {
            super(gun.getX(), gun.getY() - 12, gun.type.bulletSize, gun.type.bulletSize);

            xV = gun.type.bulletSpeed * dir;

            currentAnim = bulletTex;
            this.owner = owner;
        }

    }

    public enum GunType {
        PISTOL_BLUE(150, 0.4f, 48),
        PISTOL_RED(150, 0.4f, 48),
        RIFLE_BLUE(200, 0.2f, 32),
        RIFLE_RED(200, 0.2f, 32);

        float bulletSpeed;
        float reloadSpeed;
        float bulletSize;

        GunType(float bSpeed, float rSpeed, float size) {
            bulletSize = size;
            bulletSpeed = bSpeed;
            reloadSpeed = rSpeed;
        }
    }

}
