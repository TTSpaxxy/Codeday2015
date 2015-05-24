package com.cal.game.gfx;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by acohen on 5/23/15.
 */
public class Effect extends Actor {

    public static final Effect LAYER_JUMP = new Effect(AnimLoader.loadAnim("Animation/Warp.png", 0, 8, 1f / 10f), Animation.PlayMode.NORMAL, true);

    public Animation effect;
    public Animation.PlayMode playMode;

    public boolean isPlaying;
    public float animTime;
    public boolean layerIndependent;

    public Effect(Animation anim, Animation.PlayMode mode, boolean ind) {
        effect = anim;
        playMode = mode;
        layerIndependent = ind;
        setVisible(false);
    }

    public void play(float x, float y, float width, float height, float rotation) {
        setPosition(x, y);
        setSize(width, height);
        setRotation(rotation);
        animTime = 0;
        setVisible(true);
        effect.setPlayMode(playMode);
        isPlaying = true;
    }

    @Override
    public void act(float delta) {
        if(isPlaying) {
            animTime += delta;
            if (animTime > effect.getAnimationDuration()) {
                setVisible(false);
                isPlaying = false;
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(!isPlaying) return;
        Color batchColor = batch.getColor();
        batch.setColor(batchColor.r, batchColor.g, batchColor.b, layerIndependent ? 1.0f : parentAlpha);
        batch.draw(effect.getKeyFrame(animTime), getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

}
