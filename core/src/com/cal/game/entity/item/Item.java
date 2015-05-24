package com.cal.game.entity.item;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cal.game.entity.Entity;

/**
 * Created by acohen on 5/24/15.
 */
public abstract class Item extends Actor {

    public Animation groundAnim;
    public Animation heldAnim;
    public boolean onGround;
    public boolean isHeld;
    public Entity holder;

}
