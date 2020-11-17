package com.toastmakerr.game.gameworld;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.toastmakerr.game.Assets;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.animations.PlayerAnimation;
import com.toastmakerr.game.controllers.GameObject;
import com.toastmakerr.game.controllers.Player;
import com.toastmakerr.game.DesertMarauderMain;

import java.util.Random;

public class WorldManager extends World {
    private PlayerAnimation playerAnimation;
    private final static float GROUND_HEIGHT = 3f;
    private final static float PLATFORM_WIDTH = 10f;
    private final static float PLATFORM_HEIGHT = 1f;
    private final static float LERP = 0.1f;
    private final static float SCROLLING_SPEED = 0.6f;
    private Player player;
    private GameObject ground;
    private GameObject platform, platform2, platform3, platform4, platform5, platform6, platform7, platform8;
    private Texture BG1, BG1_2, BG2, BG2_2, PLATFORM;
    private float bg1PosX, bg2PosX;
    public WorldManager(AssetsManager assetManager){
        playerAnimation = new PlayerAnimation(assetManager);
        player = new Player(assetManager);
        ground = new GameObject(new Vector2(0,0), new Vector2(DesertMarauderMain.WIDTH / 40, GROUND_HEIGHT));
        platform = new GameObject(new Vector2(20f,8f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT));
        platform2 = new GameObject(new Vector2(30f,8f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT));
        platform3 = new GameObject(new Vector2(35f,15f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT));
        platform4 = new GameObject(new Vector2(45f,15f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT));
        platform5 = new GameObject(new Vector2(55f,22f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT));
        platform6 = new GameObject(new Vector2(65f,8f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT));
        platform7 = new GameObject(new Vector2(70f,8f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT));
        platform8 = new GameObject(new Vector2(80f,15f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT));

        bg1PosX = 0;
        bg2PosX = 0;

        this.addGroundObj(ground);
        this.addPlatformObj(platform);
        this.addPlatformObj(platform2);
        this.addPlatformObj(platform3);
        this.addPlatformObj(platform4);
        this.addPlatformObj(platform5);
        this.addPlatformObj(platform6);
        this.addPlatformObj(platform7);
        this.addPlatformObj(platform8);

        BG1 = assetManager.am.get(Assets.DESERT_BG_1);
        BG1_2 = assetManager.am.get(Assets.DESERT_BG_1);
        BG2 = assetManager.am.get(Assets.DESERT_BG_2);
        BG2_2 = assetManager.am.get(Assets.DESERT_BG_2);
        PLATFORM = assetManager.am.get(Assets.DESERT_PLATFORM);
    }

    public void draw(SpriteBatch batch, Camera camera){
        batch.begin();
        if(bg1PosX + DesertMarauderMain.WIDTH / 40 < camera.position.x - DesertMarauderMain.WIDTH / 80){
           bg1PosX += DesertMarauderMain.WIDTH / 40;
        }
        if(bg2PosX + DesertMarauderMain.WIDTH / 40 < camera.position.x - DesertMarauderMain.WIDTH / 80){
            bg2PosX += DesertMarauderMain.WIDTH / 40;
        }
        batch.draw(BG1, bg1PosX,0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(BG1_2, bg1PosX + DesertMarauderMain.WIDTH / 40,0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(BG2, bg2PosX,0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(BG2_2, bg2PosX + DesertMarauderMain.WIDTH / 40,0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);

        playerAnimation.draw(batch);
        batch.draw(PLATFORM, platform.getPosition().x, platform.getPosition().y, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(PLATFORM, platform2.getPosition().x, platform2.getPosition().y, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(PLATFORM, platform3.getPosition().x, platform3.getPosition().y, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(PLATFORM, platform4.getPosition().x, platform4.getPosition().y, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(PLATFORM, platform5.getPosition().x, platform5.getPosition().y, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(PLATFORM, platform6.getPosition().x, platform6.getPosition().y, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(PLATFORM, platform7.getPosition().x, platform7.getPosition().y, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(PLATFORM, platform8.getPosition().x, platform8.getPosition().y, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.end();
    }

    public void update(Camera camera){
        playerAnimation.setAnimation();
        player.update();
        player.collisions(getGroundObj(), getPlatformObj(), getWallObj());
        screenScroller(camera);
        platformManager(camera);
    }

    public void screenScroller(Camera camera){
        float scrollingDist = SCROLLING_SPEED * LERP;
        camera.position.x += scrollingDist;
        ground.shiftPositionX(scrollingDist);
        bg1PosX -= scrollingDist * 0.25f;
        bg2PosX -= scrollingDist * 0.2f;
    }

    public void platformManager(Camera camera){
        Random randX = new Random();
        randX.nextFloat();
        Random randY = new Random();
        float x = 3 * (randX.nextInt(4));
        float y = 7 * (randY.nextInt(3) + 1) + 1;
        for(int i = 0; i < getPlatformObj().size(); i++) {
            if(getPlatformObj().get(i).getRectangle().x + getPlatformObj().get(i).getRectangle().width < camera.position.x - DesertMarauderMain.WIDTH / 80){
                if(i > 1){
                    if(getPlatformObj().get(i-1).getRectangle().y == 8){
                        getRandomYWithExclusion(22);
                    }
                    else{
                        getRandomYWithExclusion(-1);
                    }
                    if(getPlatformObj().get(i-1).getRectangle().y < getPlatformObj().get(i).getRectangle().y){
                        getRandomXWithExclusion(0);
                    }
                    else{
                        getRandomXWithExclusion(-1);
                    }
                }
                else if(i == 0){
                    if(getPlatformObj().get(getPlatformObj().size() - 1).getRectangle().y == 8){
                        getRandomYWithExclusion(22);
                    }
                    else{
                        getRandomYWithExclusion(-1);
                    }
                    if(getPlatformObj().get(getPlatformObj().size() - 1).getRectangle().y < getPlatformObj().get(i).getRectangle().y){
                        getRandomXWithExclusion(0);
                    }
                    else{
                        getRandomXWithExclusion(-1);
                    }
                }
                getPlatformObj().get(i).setPosition(x + camera.position.x + DesertMarauderMain.WIDTH / 80, y);
            }
        }
    }

    public int getRandomYWithExclusion(int exclusion){
        Random randY = new Random();
        int y = 7 * (randY.nextInt(3) + 1) + 1;
        while(y == exclusion){
            y = 7 * (randY.nextInt(3) + 1) + 1;
        }
        return y;
    }

    public int getRandomXWithExclusion(int exclusion){
        Random randX = new Random();
        int x = 4 * (randX.nextInt(2));
        while(x == exclusion){
            x = 4 * (randX.nextInt(2));
        }
        return x;
    }
}
