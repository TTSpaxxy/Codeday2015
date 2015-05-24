package com.cal.game.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;

/**
 * Created by acohen on 5/23/15.
 */
public class Layer extends Group {

    public boolean isCurrentLayer = false;
    public boolean isBackLayer = false;

    private ArrayList<Platform> platforms;

    public ArrayList<Platform> getPlatforms() {
        if(platforms == null) {
            platforms = new ArrayList<Platform>();
            for(Actor a : getChildren()) {
                if(a instanceof Platform) {
                    platforms.add((Platform) a);
                }
            }
        }

        return platforms;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for(Actor a : getChildren()) {
            a.draw(batch, isCurrentLayer ? 1.0f : 0.3f);
        }
    }

}
