package com.cal.game.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.cal.game.entity.item.Item;
import com.cal.game.entity.item.LaserGun;
import com.cal.game.level.Layer;

/**
 * Created by acohen on 5/24/15.
 */
public class LivingEntity extends Entity {

    public int health;
    public float invincibilityCooldown;

    public Group items;
    public Group projectiles;
    boolean hasInit = false;

    public LivingEntity(float xS, float yS, float width, float height, int startHealth) {
        super(xS, yS, width, height);

        health = startHealth;
        items = new Group();
        projectiles = new Group();
        addActor(items);
        addActor(projectiles);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        for(Actor i : items.getChildren()) {
            Item item = (Item) i;
            if(facingRight) i.setPosition(getX() + getWidth() - 25, getY() - 5);
            else i.setPosition(getX() - 8, getY() - 5);
        }

        for(Actor a : items.getChildren()) {
            a.act(delta);
        }
        for(Actor a : projectiles.getChildren()) {
            ((Layer) getParent()).projectiles.addActor(a);
        }

        if(invincibilityCooldown > 0) {
            for (Actor a : ((Layer) getParent()).projectiles.getChildren()) {
                LaserGun.Bullet b = (LaserGun.Bullet) a;
                if (b.owner.equals(this)) continue;

                if (hitLeft.overlaps(b.hitbox) || hitRight.overlaps(b.hitbox) || hitBottom.overlaps(b.hitbox) || hitTop.overlaps(b.hitbox)) {
                    isHit();
                    System.out.println("ow");
                    b.setX(1000);
                }
            }
        }

        invincibilityCooldown -= delta;
        if(invincibilityCooldown < 0) invincibilityCooldown = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for(Actor a : items.getChildren()) {
            a.draw(batch, parentAlpha);
        }
    }

    public void isHit() {
        invincibilityCooldown = 1;
    }

}
