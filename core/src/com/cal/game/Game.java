package com.cal.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.cal.game.entity.Entity;
import com.cal.game.entity.MainCharacter;
import com.cal.game.input.InputHandler;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Stage stage;
	MainCharacter e;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		stage = new Stage(new StretchViewport(640, 480));
		e = new MainCharacter();

		stage.addActor(e);

		Gdx.input.setInputProcessor(new InputHandler(e));
	}

	@Override
	public void render () {
		stage.act(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}
}
