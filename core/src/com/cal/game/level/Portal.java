package com.cal.game.level;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cal.game.Game;
import com.cal.game.entity.Entity;
import com.cal.game.gfx.AnimLoader;

/**
 * Created by acohen on 5/24/15.
 */
public class Portal extends Entity {

    public static final Animation PORTAL_ANIM = AnimLoader.loadAnim("Animation/Portal.png", 0, 3, 1f / 6f);
    public Rectangle hitbox;

    public Portal(float xS, float yS) {
        super(xS, yS, 32, 32);
        hitbox = new Rectangle(getX(), getY(), getWidth(), getHeight());
        setAnim(PORTAL_ANIM);
    }

    @Override
    public void act(float delta) {
        Entity player = (Entity) getParent().findActor("Michael Jackson");
        if(player != null) {
            if(hitbox.overlaps(player.hitBottom)) {
                Game.nextLevel = true;
            }
        }

        super.act(delta);
    }

}
