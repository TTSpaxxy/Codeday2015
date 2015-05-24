package com.cal.game.entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.cal.game.gfx.AnimLoader;
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
    public boolean faceRight, faceLeft;
    public boolean isMoving;

    public MainCharacter() {
        super(200, 200, 32, 32);
        currentAnim = WALK_RIGHT;
        currentAnim.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        gravMultiplier = 1;
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

    @Override
    public void act(float delta) {

        if(moveRight) {
            xV = 50;
            setAnim(WALK_RIGHT);
        }
        else if(moveLeft) {
            xV = -50;
            setAnim(WALK_LEFT);
        }
        if (isMoving == false) {
            xV = 0;
            if(faceRight) setAnim(IDLE_RIGHT);
            else if (faceLeft) setAnim(IDLE_LEFT);

        }


        super.act(delta);
    }

}
