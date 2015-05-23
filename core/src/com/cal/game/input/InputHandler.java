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

    public InputHandler(MainCharacter mainCharacter) {
        c = mainCharacter;
    }

    @Override
    public boolean keyDown (int keycode) {
        switch(keycode) {
            case Input.Keys.LEFT:
                c.setMoveLeft(true);
                break;
            case Input.Keys.RIGHT:
                c.setMoveRight(true);
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public boolean keyUp (int keycode) {
        switch(keycode) {
            case Input.Keys.LEFT:
                c.setMoveLeft(false);
                break;
            case Input.Keys.RIGHT:
                c.setMoveRight(false);
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
