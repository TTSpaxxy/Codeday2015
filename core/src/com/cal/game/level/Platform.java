package com.cal.game.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cal.game.entity.Entity;

/**
 * Created by acohen on 5/23/15.
 */
public class Platform extends Actor {

    public static final TextureRegion SMALL_PLATFORM = new TextureRegion(
            new Texture(Gdx.files.internal("Game Pieces/SmallPlat.png")), 0, 19, 70, 10);

    public TextureRegion tex;
    public Rectangle hitbox;

    public Platform(float xS, float yS, float width, float height) {
        setX(xS);
        setY(yS);
        setWidth(width);
        setHeight(height);

        tex = SMALL_PLATFORM;
        hitbox = new Rectangle(getX() + 15, getY(), getWidth() - 30, getHeight() / 2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color batchColor = batch.getColor();
        batch.setColor(batchColor.r, batchColor.g, batchColor.b, parentAlpha);
        batch.draw(tex, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

}
