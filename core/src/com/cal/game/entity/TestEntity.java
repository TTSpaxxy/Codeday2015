package com.cal.game.entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.cal.game.gfx.AnimLoader;

/**
 * Created by acohen on 5/23/15.
 */
public class TestEntity extends Entity {

    private static final Animation IDLE_RIGHT = AnimLoader.loadAnim("Animation/MainCharacter.png", 0, 2, 1f / 2f);
    private static final Animation IDLE_LEFT = AnimLoader.loadAnim("Animation/MainCharacter.png", 8, 10, 1f / 2f);

    private static final Animation WALK_RIGHT = AnimLoader.loadAnim("Animation/MainCharacter.png", 16, 18, 1f/ 6f);
    private static final Animation WALK_LEFT = AnimLoader.loadAnim("Animation/MainCharacter.png", 25, 27, 1f / 6f);

    public TestEntity() {
        super(200, 200, 64, 64);
        currentAnim = WALK_RIGHT;
        currentAnim.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        gravMultiplier = 0;
        setVel(20, 0);
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

}
