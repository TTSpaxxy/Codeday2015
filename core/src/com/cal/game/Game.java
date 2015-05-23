package com.cal.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cal.game.entity.Entity;
import com.cal.game.entity.TestEntity;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Entity e;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		e = new TestEntity();
	}

	@Override
	public void render () {
		e.tick(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		e.render(batch);
		batch.end();
	}
}
