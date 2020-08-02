package com.toastmakerr.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected GameStateManager manager;
    protected OrthographicCamera camera;
    protected Vector3 mouse;

    protected State(GameStateManager manager){
        this.manager = manager;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void inputHandler();

    public abstract void update(float delta);

    public abstract void render(SpriteBatch batch);

}
