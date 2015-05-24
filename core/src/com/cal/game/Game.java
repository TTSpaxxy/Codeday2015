package com.cal.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.cal.game.entity.Entity;
import com.cal.game.entity.MainCharacter;
import com.cal.game.input.InputHandler;
import com.cal.game.level.Layer;
import com.cal.game.level.Level;
import com.cal.game.level.Platform;

import java.util.Random;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture border;

	public static boolean nextLevel = false;
	public static boolean restart = false;
	public static Level level;
	public static int levelCounter = 30;

	public static void nextLevel() {
		levelCounter -= 1;
		if(level != null) level.dispose();
		if(levelCounter == 0) {
			System.out.println("You win!");
			System.exit(0);
		}
		level = Level.generateRandomLevel(new Random());
	}

	@Override
	public void create () {
		batch = new SpriteBatch();

		background = new Texture(Gdx.files.internal("Game Pieces/Background.png"));
		border = new Texture(Gdx.files.internal("Animation/Border.png"));

		nextLevel();
	}

	@Override
	public void render () {
		level.act(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);
		batch.end();
		level.draw();
		batch.begin();
		batch.draw(border, 0, 0);
		batch.end();

		if(nextLevel) {
			nextLevel();
			nextLevel = false;
		}
		if(restart) {
			restart();
			restart = false;
		}
	}

	public static void restart () {
		levelCounter += 1;
		nextLevel();
	}
}
