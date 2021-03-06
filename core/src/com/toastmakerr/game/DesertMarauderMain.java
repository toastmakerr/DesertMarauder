package com.toastmakerr.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.toastmakerr.game.states.GameStateManager;
import com.toastmakerr.game.states.MenuState;

public class DesertMarauderMain extends ApplicationAdapter {
	public static final String TITLE = "Desert Marauder";
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;

	private GameStateManager manager;
	private SpriteBatch batch;
	private AssetsManager assetManager;
	
	@Override
	public void create () {
		manager = new GameStateManager();
		batch = new SpriteBatch();
		assetManager = new AssetsManager();
		manager.push(new MenuState(manager, assetManager));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		manager.update(Gdx.graphics.getDeltaTime());
		manager.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
