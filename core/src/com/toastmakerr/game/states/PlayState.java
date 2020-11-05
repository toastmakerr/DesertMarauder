package com.toastmakerr.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.toastmakerr.game.Assets;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.DesertMarauderMain;
import com.toastmakerr.game.gameworld.WorldManager;

public class PlayState extends State{
    private Texture BG1, BG2, BG3, BG4, GROUND, PLATFORM;
    private WorldManager world;
    public PlayState(GameStateManager manager, AssetsManager assetManager) {
        super(manager, assetManager);
        camera.setToOrtho(false,  DesertMarauderMain.WIDTH / 40, DesertMarauderMain.HEIGHT / 40);
        world = new WorldManager(assetManager, camera);
        BG1 = assetManager.am.get(Assets.DESERT_BG_1);
        BG2 = assetManager.am.get(Assets.DESERT_BG_2);
        BG3 = assetManager.am.get(Assets.DESERT_BG_3);
        BG4 = assetManager.am.get(Assets.DESERT_BG_4);
        GROUND = assetManager.am.get(Assets.DESERT_BG_5);
        PLATFORM = assetManager.am.get(Assets.DESERT_PLATFORM);
    }

    @Override
    public void inputHandler() {

    }

    @Override
    public void update(float delta) {
        world.update();
        camera.update();
        world.screenScroller(camera);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        world.draw(batch);
    }

    @Override
    public void dispose() {

    }
}
