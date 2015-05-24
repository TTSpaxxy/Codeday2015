package com.cal.game.entity.item;

import com.badlogic.gdx.graphics.g2d.Animation;
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
        setPosition(xS, yS);
        setSize(16, 16);

        groundAnim = AnimLoader.loadAnim("Animation/Weapons.png", 19, 20, 1f);
        heldAnim = AnimLoader.loadAnim("Animation/Weapons.png", 19, 21, 1f / 2f);
    }

    public void shoot() {
        if(!isHeld) return;
        if(cooldown == 0) {
            ((Layer) holder.getParent()).addActor(new Bullet(this, (holder.facingRight) ? 1 : -1));
            cooldown = type.reloadSpeed;
        }
    }

    @Override
    public void act(float delta) {
        for(Bullet b : ((Layer) holder.getParent()).getBullets()) {
            if(!b.exists) {
                ((Layer) holder.getParent()).removeBullet(b);
            }
        }
    }

    public class Bullet extends Entity {

        Animation bulletTex = AnimLoader.loadAnim("Animation/Weapons.png", 5, 6, 1f);

        public boolean exists;
        public Rectangle hitbox;

        public Bullet(LaserGun gun, int dir) {
            super(gun.getX(), gun.getY(), gun.type.bulletSize, 5);

            xV = gun.type.bulletSpeed * dir;
        }

        public void hasHit(LivingEntity e) {
            exists = false;
            e.isHit();
        }

        public void act(float delta) {
            if(getX() > 640 || getX() < 0) exists = false;

            super.act(delta);
        }

    }

    public enum GunType {
        PISTOL_BLUE(70, 1, 5),
        PISTOL_RED(70, 1, 5),
        RIFLE_BLUE(100, 0.2f, 10),
        RIFLE_RED(100, 0.2f, 10);

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
