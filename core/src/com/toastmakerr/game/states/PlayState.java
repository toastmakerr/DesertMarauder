package com.toastmakerr.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.toastmakerr.game.Assets;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.DesertMarauderMain;
import com.toastmakerr.game.animations.PlayerAnimation;
import com.toastmakerr.game.controllers.Player;
import com.toastmakerr.game.gameworld.WorldManager;

public class PlayState extends State{
    private Texture BG1, BG2, BG3, BG4, BG5, PLATFORM, P;
    private WorldManager world;
    public PlayState(GameStateManager manager, AssetsManager assetManager) {
        super(manager, assetManager);
        camera.setToOrtho(false,  DesertMarauderMain.WIDTH / 40, DesertMarauderMain.HEIGHT / 40);
        world = new WorldManager(assetManager, camera);
        BG1 = assetManager.am.get(Assets.DESERT_BG_1);
        BG2 = assetManager.am.get(Assets.DESERT_BG_2);
        BG3 = assetManager.am.get(Assets.DESERT_BG_3);
        BG4 = assetManager.am.get(Assets.DESERT_BG_4);
        BG5 = assetManager.am.get(Assets.DESERT_BG_5);
        P = assetManager.am.get(Assets.PLAYER_IDLE);
        PLATFORM = assetManager.am.get(Assets.DESERT_PLATFORM);
    }

    @Override
    public void inputHandler() {

    }

    @Override
    public void update(float delta) {
        world.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(BG1,0,0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(BG2,0,0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(BG3,0,0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(BG4,0,0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(BG5,0,0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(PLATFORM, world.getPlatformPosX(), world.getPlatformPosY(), DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        world.getPlayerAnimation().draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
