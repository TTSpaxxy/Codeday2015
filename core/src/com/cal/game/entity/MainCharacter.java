package com.cal.game.entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.cal.game.gfx.AnimLoader;
import com.cal.game.gfx.Effect;
import com.cal.game.level.Layer;

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
    public boolean isMoving, leftDown, rightDown;

    public LayerIndicator indicator;
    public float layerCooldown = 0;

    public MainCharacter(float xS, float yS) {
        super(xS, yS, 32, 32);
        setAnim(IDLE_RIGHT);
        gravMultiplier = 1;

        setName("Michael Jackson");
        indicator = new LayerIndicator();
    }

    public void setMoveRight(boolean t) {
        moveRight = t;
        if(moveRight) {
            faceRight = true;
            faceLeft = false;
        }
        isMoving = t;
    }

    public void setMoveLeft(boolean t) {
        moveLeft = t;
        if(moveLeft) {
            faceLeft = true;
            faceRight = false;
        }
        isMoving = t;
    }

    public void jump() {
        if(grounded) {
            yV = 200;
            grounded = false;
        }
    }

    public void jumpLayers() {
        if(layerCooldown <= 0) {
            Effect jump = Effect.LAYER_JUMP;
            Effect end = Effect.LAYER_JUMP_END;

            getParent().addActor(indicator);
            getParent().addActor(jump);
            ((Layer) getParent()).isCurrentLayer = false;
            ((Layer) getParent()).otherLayer.addActor(this);
            ((Layer) getParent()).isCurrentLayer = true;
            getParent().addActor(end);

            jump.play(getX(), getY(), getWidth(), getHeight(), 0);
            end.play(getX(), getY(), getWidth(), getHeight(), 0);

            layerCooldown = 2;
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

        indicator.setPosition(getX(), getY());
        indicator.setSize(getWidth(), getHeight());
        indicator.setRotation(getRotation());

        if(layerCooldown > 0) layerCooldown -= delta;
        else layerCooldown = 0;

        super.act(delta);
    }

}
