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

public class WorldManager extends World {
    private PlayerAnimation playerAnimation;
    private final static float GROUND_HEIGHT = 3f;
    private final static float PLATFORM_WIDTH = 9f;
    private final static float PLATFORM_HEIGHT = 1f;
    private final static float LERP = 0.1f;
    private final static float SCROLLING_SPEED = 0.6f;
    private Player player;
    private GameObject ground;
    private GameObject platform, platform2, platform3;
    private GameObject wall, ceiling;
    private Texture BG1, BG2, BG3, BG4, GROUND, PLATFORM;
    private float bg1PosX, bg1PosX_2, bg2PosX, bg2PosX_2, bg3PosX, bg3PosX_2, bg4PosX, bg4PosX_2, groundPosX, groundPosX_2;
    public WorldManager(AssetsManager assetManager, Camera camera){
        playerAnimation = new PlayerAnimation(assetManager);
        player = new Player(assetManager);
        ground = new GameObject(new Vector2(0,0), new Vector2(DesertMarauderMain.WIDTH / 40, GROUND_HEIGHT));
        platform = new GameObject(new Vector2(20f,8f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT));
        platform2 = new GameObject(new Vector2(30f,15f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT));
        platform3 = new GameObject(new Vector2(40f,21f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT));
        ceiling = new GameObject(new Vector2(0,DesertMarauderMain.HEIGHT / 40), new Vector2(DesertMarauderMain.WIDTH / 40, 2f));
        wall = new GameObject(new Vector2(-2f,0), new Vector2(2f, DesertMarauderMain.HEIGHT / 40));

        bg1PosX = 0;
        bg1PosX_2 = DesertMarauderMain.WIDTH / 40;
        bg2PosX = 0;
        bg2PosX_2 = DesertMarauderMain.WIDTH / 40;
        bg3PosX = 0;
        bg3PosX_2 = DesertMarauderMain.WIDTH / 40;
        bg4PosX = 0;
        bg4PosX_2 = DesertMarauderMain.WIDTH / 40;
        groundPosX = 0;
        groundPosX_2 = DesertMarauderMain.WIDTH / 40;

        this.addFloorObj(ground);
        this.addFloorObj(platform);
        this.addFloorObj(platform2);
        this.addFloorObj(platform3);
        this.addFloorObj(ceiling);
        this.addWallObj(wall);

        BG1 = assetManager.am.get(Assets.DESERT_BG_1);
        BG2 = assetManager.am.get(Assets.DESERT_BG_2);
        BG3 = assetManager.am.get(Assets.DESERT_BG_3);
        BG4 = assetManager.am.get(Assets.DESERT_BG_4);
        GROUND = assetManager.am.get(Assets.DESERT_BG_5);
        PLATFORM = assetManager.am.get(Assets.DESERT_PLATFORM);
    }

    public void draw(SpriteBatch batch){
        batch.begin();
        batch.draw(BG1, bg1PosX,0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(BG1, bg1PosX_2,0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(BG2, bg2PosX,0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(BG2, bg2PosX_2,0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(BG3, bg3PosX,0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(BG3, bg3PosX_2,0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(BG4, bg4PosX,0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(BG4, bg4PosX_2,0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(GROUND, groundPosX, 0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(GROUND, groundPosX_2, 0, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(PLATFORM, platform.getPosition().x, platform.getPosition().y, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(PLATFORM, platform2.getPosition().x, platform2.getPosition().y, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        batch.draw(PLATFORM, platform3.getPosition().x, platform3.getPosition().y, DesertMarauderMain.WIDTH / 40,DesertMarauderMain.HEIGHT / 40);
        playerAnimation.draw(batch);
        batch.end();
    }

    public void update(){
        playerAnimation.setAnimation();
        player.update();
        player.collisions(floorObjs, wallObjs);
    }

    public void screenScroller(Camera camera){
        float scrollingDist = SCROLLING_SPEED * LERP;
        camera.position.x += scrollingDist;
        ground.shiftPositionX(scrollingDist);
        ceiling.shiftPositionX(scrollingDist);
        wall.shiftPositionX(scrollingDist);

        bg1PosX -= scrollingDist * 0.25f;
        bg1PosX_2 -= scrollingDist * 0.25f;
        bg2PosX -= scrollingDist * 0.2f;
        bg2PosX_2 -= scrollingDist * 0.2f;
        bg3PosX -= scrollingDist * 0.15f;
        bg3PosX_2 -= scrollingDist * 0.15f;
        bg4PosX -= scrollingDist * 0.1;
        bg4PosX_2 -= scrollingDist * 0.1;
        groundPosX -= scrollingDist * 0.05;
        groundPosX_2 -= scrollingDist * 0.05;

        if(bg1PosX + DesertMarauderMain.WIDTH / 40 <= camera.position.x - DesertMarauderMain.WIDTH / 80){
            bg1PosX += DesertMarauderMain.WIDTH / 20;
        }
        else if(bg1PosX_2 + DesertMarauderMain.WIDTH / 40 <= camera.position.x - DesertMarauderMain.WIDTH / 80){
            bg1PosX_2 += DesertMarauderMain.WIDTH / 20;
        }

        if(bg2PosX + DesertMarauderMain.WIDTH / 40 <= camera.position.x - DesertMarauderMain.WIDTH / 80){
            bg2PosX += DesertMarauderMain.WIDTH / 20;
        }
        else if(bg2PosX_2 + DesertMarauderMain.WIDTH / 40 <= camera.position.x - DesertMarauderMain.WIDTH / 80){
            bg2PosX_2 += DesertMarauderMain.WIDTH / 20;
        }

        if(bg3PosX + DesertMarauderMain.WIDTH / 40 <= camera.position.x - DesertMarauderMain.WIDTH / 80){
            bg3PosX += DesertMarauderMain.WIDTH / 20;
        }
        else if(bg3PosX_2 + DesertMarauderMain.WIDTH / 40 <= camera.position.x - DesertMarauderMain.WIDTH / 80){
            bg3PosX_2 += DesertMarauderMain.WIDTH / 20;
        }

        if(bg4PosX + DesertMarauderMain.WIDTH / 40 <= camera.position.x - DesertMarauderMain.WIDTH / 80){
            bg4PosX += DesertMarauderMain.WIDTH / 20;
        }
        else if(bg4PosX_2 + DesertMarauderMain.WIDTH / 40 <= camera.position.x - DesertMarauderMain.WIDTH / 80){
            bg4PosX_2 += DesertMarauderMain.WIDTH / 20;
        }

        if(groundPosX + DesertMarauderMain.WIDTH / 40 <= camera.position.x - DesertMarauderMain.WIDTH / 80){
            groundPosX += DesertMarauderMain.WIDTH / 20;
        }
        else if(groundPosX_2 + DesertMarauderMain.WIDTH / 40 <= camera.position.x - DesertMarauderMain.WIDTH / 80){
            groundPosX_2 += DesertMarauderMain.WIDTH / 20;
        }
    }

    public void updateScrollingDist(){

    }
}
