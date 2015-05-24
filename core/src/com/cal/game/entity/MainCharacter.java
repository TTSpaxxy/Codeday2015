package com.cal.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.cal.game.gfx.AnimLoader;
import com.cal.game.level.Layer;
import com.cal.game.level.Platform;

/**
 * Created by acohen on 5/23/15.
 */
public class MainCharacter extends Entity {

    private static final Animation IDLE_RIGHT = AnimLoader.loadAnim("Animation/MainCharacter.png", 0, 2, 1f / 2f);
    private static final Animation IDLE_LEFT = AnimLoader.loadAnim("Animation/MainCharacter.png", 8, 10, 1f / 2f);

    private static final Animation WALK_RIGHT = AnimLoader.loadAnim("Animation/MainCharacter.png", 16, 19, 1f/ 6f);
    private static final Animation WALK_LEFT = AnimLoader.loadAnim("Animation/MainCharacter.png", 24, 27, 1f / 6f);

    public boolean moveRight, moveLeft;

    public LayerIndicator indicator;

    public MainCharacter() {
        super(200, 200, 32, 32);
        setAnim(IDLE_RIGHT);
        gravMultiplier = 1;

        indicator = new LayerIndicator();
    }

    public void setMoveRight(boolean t) {
        if(moveLeft && t) moveLeft = false;
        moveRight = t;
    }

    public void setMoveLeft(boolean t) {
        if(moveRight && t) moveRight = false;
        moveLeft = t;
    }

    public void jump() {
        System.out.println(grounded);
        if(grounded) {
            yV = 150;
            grounded = false;
        }
    }

    public void jumpLayers() {

    }

    @Override
    public void setVel(float xVel, float yVel) {
        if(xVel == 0) {
            setAnim((xV >= 0) ? IDLE_RIGHT : IDLE_LEFT);
        } else {
            setAnim((xVel > 0) ? WALK_RIGHT : WALK_LEFT);
        }

        super.setVel(xVel, yVel);
    }

    @Override
    public void act(float delta) {
        if(!moveRight && !moveLeft) {
            setAnim((xV >= 0) ? IDLE_RIGHT : IDLE_LEFT);
            xV = 0;
        } else {
            if(moveRight) xV = 50;
            else if(moveLeft) xV = -50;

            setAnim((xV > 0) ? WALK_RIGHT : WALK_LEFT);
        }

        indicator.setPosition(getX(), getY());
        indicator.setSize(getWidth(), getHeight());
        indicator.setRotation(getRotation());

        super.act(delta);
    }

}
