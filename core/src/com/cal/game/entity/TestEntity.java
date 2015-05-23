package com.cal.game.entity;

import com.cal.game.gfx.AnimLoader;

/**
 * Created by acohen on 5/23/15.
 */
public class TestEntity extends Entity {

    public TestEntity() {
        super(200, 200, 32, 32);
        currentAnim = AnimLoader.loadAnim("test.png", 0, 1, 1f / 4f);
    }


}
