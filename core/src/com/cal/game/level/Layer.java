package com.cal.game.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.cal.game.entity.item.LaserGun;

import java.util.ArrayList;

/**
 * Created by acohen on 5/23/15.
 */
public class Layer extends Group {

    public Layer otherLayer;

    public boolean isCurrentLayer = false;
    public boolean isBackLayer = false;

    public Group projectiles;

    public ArrayList<Platform> platforms;

    public Layer() {
        projectiles = new Group();
    }

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
    public void act(float delta) {
        super.act(delta);

        for(Actor a : projectiles.getChildren()) {
            if(a.getX() < 0 || a.getX() > 640) {
                projectiles.removeActor(a);
                a.clear();
                continue;
            }
            a.act(delta);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(isBackLayer) {
            Matrix4 scaledMatrix = new Matrix4(new Vector3(96, 72, 0), new Quaternion(), new Vector3(0.7f, 0.7f, 0.7f));
            batch.setTransformMatrix(scaledMatrix);
        }
        for(Actor a : getChildren()) {
            a.draw(batch, isCurrentLayer ? 1.0f : 0.3f);
        }
        for(Actor a : projectiles.getChildren()) {
            a.draw(batch, isCurrentLayer ? 1.0f : 0.3f);
        }

        batch.setTransformMatrix(new Matrix4());
    }

}
