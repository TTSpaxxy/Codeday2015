package com.cal.game.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;

/**
 * Created by acohen on 5/23/15.
 */
public class Layer extends Group {

    public Layer otherLayer;

    public boolean isCurrentLayer = false;
    public boolean isBackLayer = false;

    public ArrayList<Platform> platforms;

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
        if(isBackLayer) {
            Matrix4 scaledMatrix = new Matrix4(new Vector3(64, 48, 0), new Quaternion(), new Vector3(0.8f, 0.8f, 0.8f));
            batch.setTransformMatrix(scaledMatrix);
        }
        for(Actor a : getChildren()) {
            a.draw(batch, isCurrentLayer ? 1.0f : 0.3f);
        }

        batch.setTransformMatrix(new Matrix4());
    }

}
