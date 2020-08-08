package com.toastmakerr.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.toastmakerr.game.AssetsManager;

public abstract class State {
    protected com.toastmakerr.game.states.GameStateManager manager;
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected AssetsManager assetManager;

    protected State(GameStateManager manager, AssetsManager assetManager){
        this.manager = manager;
        this.assetManager = assetManager;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void inputHandler();

    public abstract void update(float delta);

    public abstract void render(SpriteBatch batch);

    public abstract void dispose();

}
