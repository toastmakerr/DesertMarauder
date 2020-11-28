package com.toastmakerr.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.DesertMarauderMain;
import com.toastmakerr.game.gameworld.PlayerManager;
import com.toastmakerr.game.gameworld.WorldManager;

public class PlayState extends State{
    private WorldManager world;
    public PlayState(GameStateManager manager, AssetsManager assetManager) {
        super(manager, assetManager);
        camera.setToOrtho(false,  DesertMarauderMain.WIDTH / 40, DesertMarauderMain.HEIGHT / 40);
        world = new WorldManager(assetManager);
        world.createCollisionListener();
    }

    @Override
    public void inputHandler() {
        if(PlayerManager.isDead()){
            manager.set(new GameOverState(manager, assetManager));
            dispose();
        }
    }

    @Override
    public void update(float delta) {
        world.update(camera);
        camera.update();
        inputHandler();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        world.draw(batch, camera);
    }

    @Override
    public void dispose() {
        world.dispose(camera);
    }
}
