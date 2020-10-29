package com.toastmakerr.game.gameworld;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.animations.PlayerAnimation;
import com.toastmakerr.game.controllers.GameObject;
import com.toastmakerr.game.controllers.Player;

public class WorldManager extends World {
    private PlayerAnimation playerAnimation;
    private final static float GROUND_HEIGHT = 2.5f;
    private Player player;
    private GameObject ground;
    public WorldManager(AssetsManager assetManager, Camera camera){
        playerAnimation = new PlayerAnimation(assetManager);
        player = new Player(assetManager);
        ground = new GameObject(new Vector2(0,0), new Vector2(camera.viewportWidth, GROUND_HEIGHT));
    }

    public void update(){
        playerAnimation.setAnimation();
        player.update();
        player.onGround(ground);
    }

    public PlayerAnimation getPlayerAnimation(){
        return playerAnimation;
    }
}
