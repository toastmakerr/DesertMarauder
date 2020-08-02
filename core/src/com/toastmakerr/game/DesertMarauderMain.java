package com.toastmakerr.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.toastmakerr.game.States.GameStateManager;
import com.toastmakerr.game.States.MenuState;

public class DesertMarauderMain extends ApplicationAdapter {
	public static final String TITLE = "Desert Marauder";
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	private GameStateManager manager;
	private SpriteBatch batch;
	
	@Override
	public void create () {
		manager = new GameStateManager();
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		manager.push(new MenuState(manager));
		manager.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
