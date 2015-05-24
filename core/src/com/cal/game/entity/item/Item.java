package com.cal.game.entity.item;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cal.game.entity.Entity;
import com.cal.game.entity.LivingEntity;

/**
 * Created by acohen on 5/24/15.
 */
public abstract class Item extends Entity {

    public Animation groundAnim;
    public Animation heldAnimRight;
    public Animation heldAnimLeft;
    public boolean onGround;
    public boolean isHeld;
    public LivingEntity holder;

    public Item(float xS, float yS, float width, float height) {
        super(xS, yS, width, height);
    }

    public void pickUp(LivingEntity e) {
        onGround = false;
        isHeld = true;
        holder = e;
        holder.items.addActor(this);
    }

    public void setHeldAnim() {
        if(((LivingEntity) getParent().getParent()).facingRight) setAnim(heldAnimRight);
        else setAnim(heldAnimLeft);
    }

    @Override
    public void act(float delta) {
        if(onGround) setAnim(groundAnim);
        else if(!onGround && isHeld) setHeldAnim();
        else currentAnim = null;

        if(currentAnim == null) setVisible(false);

        super.act(delta);
    }

}
