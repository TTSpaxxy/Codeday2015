package com.cal.game.entity;

/**
 * Created by acohen on 5/23/15.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cal.game.gfx.AnimLoader;
import com.cal.game.level.Platform;

public abstract class Entity extends Actor {

    public static final float GRAVITY = 200.0f;

    public float xV, yV;
    public boolean grounded;
    public float gravMultiplier;

    public Animation currentAnim;
    public float animTime;

    public Rectangle hitBottom, hitTop, hitLeft, hitRight;

    public Entity(float xS, float yS, float width, float height) {
        setX(xS);
        setY(yS);
        setWidth(width);
        setHeight(height);

        hitBottom = new Rectangle(getX(), getY(), getWidth(), getHeight() / 8);
        hitTop = new Rectangle(getX(), getY() + 7 / 8 * getHeight(), getWidth(), getHeight() / 8);
        hitLeft = new Rectangle(getX(), getY(), getWidth() / 8, getHeight());
        hitRight = new Rectangle(getX() + 7 / 8 * getWidth(), getY(), getWidth() / 8, getHeight());
    }

    public void setAnim(Animation a) {
        currentAnim = a;
        currentAnim.setPlayMode(Animation.PlayMode.LOOP);
    }

    public void setVel(float xVel, float yVel) {
        xV = xVel;
        yV = yVel;
    }

    @Override
    public void act(float delta) {
        if(!grounded) {
            yV -= GRAVITY * gravMultiplier * delta;
        }

        moveBy(delta * xV, delta * yV);

        animTime += delta;

        if (gravMultiplier > 0) {

        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(currentAnim.getKeyFrame(animTime), getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

}
