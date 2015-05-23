package com.cal.game.entity;

/**
 * Created by acohen on 5/23/15.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import com.cal.game.gfx.AnimLoader;
import com.cal.game.level.Platform;

public abstract class Entity {

    public static final float GRAVITY = 200.0f;

    public float x, y;
    public float width, height;
    public Rectangle rect;
    private float xP, yP;
    public float xV, yV;
    public boolean grounded;
    public float gravMultiplier;

    public Animation currentAnim;
    public float animTime;

    public Entity(float xS, float yS, float width, float height) {
        x = xS;
        y = yS;
        this.width = width;
        this.height = height;
        rect = new Rectangle(x, y, width, height);
    }

    public void setAnim(Animation a) {
        currentAnim = a;
        currentAnim.setPlayMode(Animation.PlayMode.LOOP);
    }

    public void setVel(float xVel, float yVel) {
        xV = xVel;
        yV = yVel;
    }

    public void tick(float delta) {
        if(!grounded) {
            yV -= GRAVITY * gravMultiplier * delta;
        }

        xP = x;
        yP = y;

        x += delta * xV;
        y += delta * yV;

        rect.set(x, y, width, height);

        animTime += delta;
    }

    public void render(SpriteBatch batch) {
        batch.draw(currentAnim.getKeyFrame(animTime), x, y, width, height);
    }

    public void collide(Platform p) {
        if(rect.overlaps(p.rect)) {

        }
    }

    public void collide(Entity e) {

    }

}
