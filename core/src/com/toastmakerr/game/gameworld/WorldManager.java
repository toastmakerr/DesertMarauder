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
    private final static float GROUND_HEIGHT = 3f;
    private final static float PLATFORM_WIDTH = 9f;
    private final static float PLATFORM_HEIGHT = 1f;
    private final static float CAMERA_OFFSET = 10f;
    private Player player;
    private GameObject ground;
    private GameObject platform, platform2, platform3;
    private GameObject wall, ceiling;
    public WorldManager(AssetsManager assetManager, Camera camera){
        super();
        playerAnimation = new PlayerAnimation(assetManager);
        player = new Player(assetManager);
        ground = new GameObject(new Vector2(0,0), new Vector2(DesertMarauderMain.WIDTH / 40, GROUND_HEIGHT));
        platform = new GameObject(new Vector2(20f,8f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT));
        platform2 = new GameObject(new Vector2(30f,15f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT));
        platform3 = new GameObject(new Vector2(40f,21f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT));
        ceiling = new GameObject(new Vector2(0,DesertMarauderMain.HEIGHT / 40), new Vector2(DesertMarauderMain.WIDTH / 40, 2f));
        wall = new GameObject(new Vector2(-2f,0), new Vector2(2f, camera.viewportHeight));
        this.addFloorObj(ground);
        this.addFloorObj(platform);
        this.addFloorObj(platform2);
        this.addFloorObj(platform3);
        this.addFloorObj(ceiling);
        this.addWallObj(wall);
    }

    public void update(){
        playerAnimation.setAnimation();
        player.update();
        player.collisions(floorObjs, wallObjs);
    }

    public void cameraScroller(Camera camera){
        if(getPlayerPositionX() + CAMERA_OFFSET < DesertMarauderMain.WIDTH/80)
            camera.position.x = DesertMarauderMain.WIDTH/80;
        else
            camera.position.x = getPlayerPositionX() + CAMERA_OFFSET;
    }

    public float getPlatformPosX(GameObject floorObj){
        return floorObj.getPosition().x;
    }

    public float getPlatformPosY(GameObject floorObj){
        return floorObj.getPosition().y;
    }

    public float getPlayerPositionX(){
        return player.getPosition().x;
    }

    public GameObject getGround(){
        return ground;
    }

    public GameObject getPlatform(){
        return platform;
    }

    public GameObject getPlatform2(){
        return platform2;
    }

    public GameObject getPlatform3(){
        return platform3;
    }


    public PlayerAnimation getPlayerAnimation(){
        return playerAnimation;
    }
}
