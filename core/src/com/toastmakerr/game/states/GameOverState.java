package com.toastmakerr.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.toastmakerr.game.Assets;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.DesertMarauderMain;

public class GameOverState extends State{
    private Texture background, endTitle;
    private float clock;
    private Boolean displayTitle;

    public GameOverState(GameStateManager manager, AssetsManager assetManager) {
        super(manager,assetManager);
        background = assetManager.am.get(Assets.DESERT_BG);
        endTitle = assetManager.am.get(Assets.END_TITLE);
        camera.setToOrtho(false,  DesertMarauderMain.WIDTH / 40, DesertMarauderMain.HEIGHT / 40);
        clock = 0;
        displayTitle = true;
    }

    @Override
    public void inputHandler() {
        if(Gdx.input.justTouched()){
            manager.set(new MenuState(manager, assetManager));
            dispose();
        }
    }

    @Override
    public void update(float delta) {
        inputHandler();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        ;
        batch.draw(background, 0, 0, DesertMarauderMain.WIDTH / 40, DesertMarauderMain.HEIGHT / 40);
        clock += Gdx.graphics.getDeltaTime();
        if(displayTitle)
            batch.draw(endTitle, 0, 0, DesertMarauderMain.WIDTH / 40, DesertMarauderMain.HEIGHT / 40);
        if (displayTitle && clock > 0.5) {
            displayTitle = false;
            clock = 0;
        }
        else if(!displayTitle && clock > 0.5) {
            displayTitle = true;
            clock = 0;
        }
        batch.end();
    }

    @Override
    public void dispose() {
    }
}
