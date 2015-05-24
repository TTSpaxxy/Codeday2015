package com.cal.game.entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.cal.game.entity.item.LaserGun;
import com.cal.game.gfx.AnimLoader;
import com.cal.game.gfx.Effect;
import com.cal.game.level.Layer;

/**
 * Created by acohen on 5/23/15.
 */
public class MainCharacter extends LivingEntity {

    private static final Animation IDLE_RIGHT_FRONT = AnimLoader.loadAnim("Animation/MainCharacter.png", 35, 37, 1f / 2f);
    private static final Animation IDLE_LEFT_FRONT = AnimLoader.loadAnim("Animation/MainCharacter.png", 43, 45, 1f / 2f);

    private static final Animation WALK_RIGHT_FRONT = AnimLoader.loadAnim("Animation/MainCharacter.png", 51, 54, 1f/ 6f);
    private static final Animation WALK_LEFT_FRONT = AnimLoader.loadAnim("Animation/MainCharacter.png", 59, 62, 1f / 6f);

    private static final Animation IDLE_RIGHT_BACK = AnimLoader.loadAnim("Animation/MainCharacter.png", 3, 5, 1f / 2f);
    private static final Animation IDLE_LEFT_BACK = AnimLoader.loadAnim("Animation/MainCharacter.png", 11, 13, 1f / 2f);

    private static final Animation WALK_RIGHT_BACK = AnimLoader.loadAnim("Animation/MainCharacter.png", 19, 22, 1f/ 6f);
    private static final Animation WALK_LEFT_BACK = AnimLoader.loadAnim("Animation/MainCharacter.png", 27, 30, 1f / 6f);

    public LayerIndicator indicator;
    public float layerCooldown = 0;

    public LaserGun gun;

    public boolean hasSuit;

    public MainCharacter(float xS, float yS) {
        super(xS, yS, 32, 32, 3);
        setAnim(IDLE_RIGHT_FRONT);
        gravMultiplier = 1;
        gun = new LaserGun(xS + 20, yS + 15, LaserGun.GunType.PISTOL_BLUE);
        gun.pickUp(this);
        gun.isHeld = true;

        setName("Michael Jackson");
        indicator = new LayerIndicator();
    }



    public void setWalk(int dir) {
        if(dir > 0) {
            xV = 50;
        } else if(dir < 0) {
            xV = -50;
        } else {
            xV = 0;
        }
    }

    public void jump() {
        if(grounded) {
            yV = 400;
            grounded = false;
        }
    }

    public void jumpLayers() {
        if(layerCooldown <= 0) {
            Effect jump = null, end = null;
            if(((Layer) getParent()).isBackLayer) {
                jump = Effect.LAYER_JUMP_ORANGE;
                end = Effect.LAYER_JUMP_BLUE;
            } else {
                jump = Effect.LAYER_JUMP_BLUE;
                end = Effect.LAYER_JUMP_ORANGE;
            }

            getParent().addActor(indicator);
            getParent().addActor(jump);
            ((Layer) getParent()).isCurrentLayer = false;
            ((Layer) getParent()).otherLayer.addActor(this);
            ((Layer) getParent()).isCurrentLayer = true;
            getParent().addActor(end);

            jump.play(getX(), getY(), getWidth(), getHeight(), 0);
            end.play(getX(), getY(), getWidth(), getHeight(), 0);

            layerCooldown = 0;
        }
    }

    public void setAnim(String anim) {
        if(((Layer) getParent()).isBackLayer) {
            if(anim.contentEquals("IDLE_LEFT")) setAnim(IDLE_LEFT_BACK);
            else if(anim.contentEquals("IDLE_RIGHT")) setAnim(IDLE_RIGHT_BACK);
            else if(anim.contentEquals("WALK_LEFT")) setAnim(WALK_LEFT_BACK);
            else if(anim.contentEquals("WALK_RIGHT")) setAnim(WALK_RIGHT_BACK);
        } else {
            if(anim.contentEquals("IDLE_LEFT")) setAnim(IDLE_LEFT_FRONT);
            else if(anim.contentEquals("IDLE_RIGHT")) setAnim(IDLE_RIGHT_FRONT);
            else if(anim.contentEquals("WALK_LEFT")) setAnim(WALK_LEFT_FRONT);
            else if(anim.contentEquals("WALK_RIGHT")) setAnim(WALK_RIGHT_FRONT);
        }
    }

    @Override
    public void act(float delta) {

        if(facingRight && xV > 0) {
            xV = 500;
            setAnim("WALK_RIGHT");
        }
        else if(!facingRight && xV < 0) {
            xV = -500;
            setAnim("WALK_LEFT");
        }
        else if(xV == 0){
            if(facingRight) setAnim("IDLE_RIGHT");
            else setAnim("IDLE_LEFT");
        }

        indicator.setPosition(getX(), getY());
        indicator.setSize(getWidth(), getHeight());
        indicator.setRotation(getRotation());

        if(layerCooldown > 0) layerCooldown -= delta;
        else layerCooldown = 0;

        super.act(delta);
    }

}
