package com.cal.game.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.cal.game.entity.Entity;

/**
 * Created by acohen on 5/23/15.
 */
public class Platform extends Entity {

    public Texture platformTex;

    public Platform() {
        super(200, 100, 100, 20);
    }

}
