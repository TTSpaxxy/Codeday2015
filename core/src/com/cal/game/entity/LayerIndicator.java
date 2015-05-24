package com.cal.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by acohen on 5/23/15.
 */
public class LayerIndicator extends Actor {

    private TextureRegion indicatorTex = new TextureRegion(new Texture(Gdx.files.internal("Animation/LayerIndicator.png")));

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color batchColor = batch.getColor();
        batch.setColor(batchColor.r, batchColor.g, batchColor.b, parentAlpha);
        batch.draw(indicatorTex, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

}
