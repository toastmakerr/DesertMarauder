package com.toastmakerr.game.gameworld;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.toastmakerr.game.Assets;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.DesertMarauderMain;
import com.toastmakerr.game.controllers.GameObject;

import java.util.Random;

public class PlatformManager {
    public final static float PLATFORM_WIDTH = 5f;
    public final static float PLATFORM_HEIGHT = 0.45f;
    private final Texture PLATFORM;
    private GameObject platform;
    private boolean hasEnemy = true;
    private static int platformsCreated = 0;

    public PlatformManager(AssetsManager assetManager, Vector2 startingPos){
        String temp = "Platform" + platformsCreated;
        PLATFORM = assetManager.am.get(Assets.DESERT_PLATFORM);
        platform = new GameObject(startingPos, new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT), BodyDef.BodyType.KinematicBody, temp);
        platformsCreated++;
    }

    public void update(){

    }

    public void draw(SpriteBatch batch){
        batch.draw(PLATFORM, platform.getPosition().x - PLATFORM_WIDTH, platform.getPosition().y - PLATFORM_HEIGHT, DesertMarauderMain.WIDTH / 40f,DesertMarauderMain.HEIGHT / 40f);
    }

    public GameObject getPlatform(){
        return platform;
    }

    public void updatePlatform(Camera camera, PlatformManager prevPlatform) {
        Random randX = new Random();
        randX.nextFloat();
        Random randY = new Random();
        float x = 5 * (randX.nextInt(2) + 1);
        float y = 7 * (randY.nextInt(3) + 1) + 1;
        if (platform.getPosition().x + PlatformManager.PLATFORM_WIDTH < camera.position.x - DesertMarauderMain.WIDTH / 80) {
            if (prevPlatform.getPlatform().getPosition().y == 8) {
                getRandomYWithExclusion(22);
            } else {
                getRandomYWithExclusion(-1);
            }
            if (prevPlatform.getPlatform().getPosition().y < y) {
                getRandomXWithExclusion(0);
            } else if (prevPlatform.getPlatform().getPosition().y == y) {
                x = 10;
            } else {
                getRandomXWithExclusion(-1);
            }
            platform.setPosition(x + camera.position.x + DesertMarauderMain.WIDTH / 80 + 10, y);
        }
    }

    public void setHasEnemy(boolean tof){
        hasEnemy = tof;
    }

    public boolean getHasEnemy(){
        return hasEnemy;
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
        int x = 5 * (randX.nextInt(3));
        while(x == exclusion){
            x = 5 * (randX.nextInt(3));
        }
        return x;
    }
}
