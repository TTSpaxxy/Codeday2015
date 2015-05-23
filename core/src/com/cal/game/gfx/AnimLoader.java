package com.cal.game.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by acohen on 5/23/15.
 */
public class AnimLoader {

    public static Animation loadAnim(String animPath, int startFrame, int endFrame, float frameDiff) {
        Texture frames = new Texture(Gdx.files.internal(animPath));
        int WIDTH_TILES = frames.getWidth() / 32;
        int HEIGHT_TILES = frames.getHeight() / 32;

        TextureRegion[][] tmp = TextureRegion.split(frames, 32, 32);
        TextureRegion[] finalFrames = new TextureRegion[endFrame - startFrame];
        for(int y = 0; y < HEIGHT_TILES; y++) {
            for(int x = 0; x < WIDTH_TILES; x++) {
                int frameNum =  y * WIDTH_TILES + x;
                if(frameNum >= startFrame && frameNum < endFrame) {
                    finalFrames[frameNum - startFrame] = tmp[y][x];
                }
            }
        }

        return new Animation(frameDiff, finalFrames);
    }

}
