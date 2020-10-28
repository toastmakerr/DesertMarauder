package com.toastmakerr.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.toastmakerr.game.Assets;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.DesertMarauderMain;
import com.toastmakerr.game.animations.PlayerAnimation;
import com.toastmakerr.game.controllers.Player;
import com.toastmakerr.game.entities.GroundObject;

public class PlayState extends State{
    private Texture BG1, BG2, BG3, BG4, BG5;
    private World world;
    private static final float STEP_TIME = 1f/60f;
    private float accumulator;
    private PlayerAnimation playerAnimation;
    private Player player;
    private GroundObject ground;

    public PlayState(GameStateManager manager, AssetsManager assetManager) {
        super(manager, assetManager);
        camera.setToOrtho(false,  15, 15 * (DesertMarauderMain.WIDTH / DesertMarauderMain.HEIGHT));
        world = new World(new Vector2(0,-9.8f), true);
        accumulator = 0;
        playerAnimation = new PlayerAnimation(assetManager);
        player = new Player(assetManager, world);
        ground = new GroundObject(world, camera);
        BG1 = assetManager.am.get(Assets.DESERT_BG_1);
        BG2 = assetManager.am.get(Assets.DESERT_BG_2);
        BG3 = assetManager.am.get(Assets.DESERT_BG_3);
        BG4 = assetManager.am.get(Assets.DESERT_BG_4);
        BG5 = assetManager.am.get(Assets.DESERT_BG_5);
    }

    @Override
    public void inputHandler() {

    }

    @Override
    public void update(float delta) {
        accumulator += Math.min(delta, 0.25f);
        player.inputHandler();
        playerAnimation.setAnimation();
        player.update();
        ground.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(BG1,0,0, 15, 15 * (DesertMarauderMain.WIDTH / DesertMarauderMain.HEIGHT));
        batch.draw(BG2,0,0, 15, 15 * (DesertMarauderMain.WIDTH / DesertMarauderMain.HEIGHT));
        batch.draw(BG3,0,0, 15, 15 * (DesertMarauderMain.WIDTH / DesertMarauderMain.HEIGHT));
        batch.draw(BG4,0,0, 15, 15 * (DesertMarauderMain.WIDTH / DesertMarauderMain.HEIGHT));
        batch.draw(BG5,0,0, 15, 15 * (DesertMarauderMain.WIDTH / DesertMarauderMain.HEIGHT));
        playerAnimation.draw(batch);
        batch.end();
        stepWorld();
    }

    @Override
    public void dispose() {

    }
    private void stepWorld() {
        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;
            world.step(STEP_TIME, 6, 2);
        }
    }
}
