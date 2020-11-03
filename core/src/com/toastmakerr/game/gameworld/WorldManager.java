package com.toastmakerr.game.gameworld;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.animations.PlayerAnimation;
import com.toastmakerr.game.controllers.GameObject;
import com.toastmakerr.game.controllers.Player;
import com.toastmakerr.game.DesertMarauderMain;

public class WorldManager extends World {
    private PlayerAnimation playerAnimation;
    private final static float GROUND_HEIGHT = 2.5f;
    private final static float platformWidth = 9.15f;
    private final static float platformHeight = 1.5f;
    private Player player;
    private GameObject ground;
    private GameObject platform, platform2;
    public WorldManager(AssetsManager assetManager, Camera camera){
        super();
        playerAnimation = new PlayerAnimation(assetManager);
        player = new Player(assetManager);
        ground = new GameObject(new Vector2(0,0), new Vector2(DesertMarauderMain.WIDTH, GROUND_HEIGHT));
        platform = new GameObject(new Vector2(20f,8f), new Vector2(platformWidth, platformHeight));
        platform2 = new GameObject(new Vector2(29.15f,8f), new Vector2(platformWidth, platformHeight));
        this.addObj(ground);
        this.addObj(platform);
        this.addObj(platform2);
    }

    public void update(){
        playerAnimation.setAnimation();
        player.update();
        player.onGround(floorObjs);
    }

    public float getPlatformPosX(){
        return platform.getPosition().x + 0.2f;
    }

    public float getPlatformPosY(){
        return platform.getPosition().y;
    }

    public float getPlatform2PosX(){
        return platform2.getPosition().x + 0.2f;
    }

    public float getPlatform2PosY(){
        return platform2.getPosition().y;
    }

    public PlayerAnimation getPlayerAnimation(){
        return playerAnimation;
    }
}
