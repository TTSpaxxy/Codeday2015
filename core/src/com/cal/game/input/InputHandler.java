package com.cal.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.cal.game.entity.Entity;
import com.cal.game.entity.MainCharacter;

/**
 * Created by acohen on 5/23/15.
 */
public class InputHandler implements InputProcessor {

    MainCharacter c;

    boolean KEY_LEFT_DOWN;
    boolean KEY_RIGHT_DOWN;

    public InputHandler(MainCharacter mainCharacter) {
        c = mainCharacter;
    }

    @Override
    public boolean keyDown (int keycode) {
        switch(keycode) {
            case Input.Keys.LEFT:
                c.setWalk(-1);
                KEY_LEFT_DOWN = true;
                break;
            case Input.Keys.RIGHT:
                c.setWalk(1);
                KEY_RIGHT_DOWN = true;
                break;
            case Input.Keys.SPACE:
                c.jump();
                break;
            case Input.Keys.SHIFT_LEFT:
                c.jumpLayers();
                break;
            case Input.Keys.Z:
                c.gun.shoot(c);
            default:
                break;
        }

        return true;
    }

    @Override
    public boolean keyUp (int keycode) {
        switch(keycode) {
            case Input.Keys.LEFT:
                KEY_LEFT_DOWN = false;
                if(!KEY_RIGHT_DOWN) c.setWalk(0);
                else c.setWalk(1);
                break;
            case Input.Keys.RIGHT:
                KEY_RIGHT_DOWN = false;
                if(!KEY_LEFT_DOWN) c.setWalk(0);
                else c.setWalk(-1);
                break;
            default:
                break;
        }

        return true;
    }


    @Override
    public boolean keyTyped (char character) {
        return false;
    }

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved (int x, int y) {
        return false;
    }

    @Override
    public boolean scrolled (int amount) {
        return false;
    }

}
