package com.toastmakerr.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.toastmakerr.game.Assets;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.DesertMarauderMain;
import com.toastmakerr.game.controllers.DynamicGameObject;
import com.toastmakerr.game.controllers.GameObject;
import com.toastmakerr.game.controllers.Player;
import com.toastmakerr.game.controllers.Scorpion;

import java.util.ArrayList;

public class PlayerManager {
    private Player player;
    private Texture heart1, heart2, heart3;
    private Boolean h1, h2, h3;
    private static Boolean dead;
    private static Boolean tookDmg;
    private static float invulnerabilityTimer;
    public PlayerManager(AssetsManager assetManager){
        player = new Player(assetManager);
        heart1 = assetManager.am.get(Assets.HEART);
        heart2 = assetManager.am.get(Assets.HEART);
        heart3 = assetManager.am.get(Assets.HEART);
        h1 = true;
        h2 = true;
        h3 = true;
        dead = false;
        invulnerabilityTimer = 0.0f;
        tookDmg = false;
    }

    public void update(ArrayList<ScorpionManager> scorpions, Camera camera){
        ifOffScreen(camera);
        player.update();
        for(int i = 0; i < scorpions.size(); i++) {
            if(Player.getContactAmount() > 0 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                player.dealDamage(scorpions.get(i).getScorpion());
            }
        }
        if(tookDmg) {
            invulnerabilityTimer += Gdx.graphics.getDeltaTime();
            if(invulnerabilityTimer >= 1) {
                tookDmg = false;
                invulnerabilityTimer = 0.0f;
            }
        }
        updateHeart();
        dead = !player.isAlive();
    }
    public void draw(SpriteBatch batch, Camera camera){
        player.draw(batch);
        if(h1)
            batch.draw(heart1, camera.position.x - DesertMarauderMain.WIDTH / 80f + 1f, 0.3f, 2.3f,2.3f);
        if(h2)
            batch.draw(heart2, camera.position.x - DesertMarauderMain.WIDTH / 80f + 4f, 0.3f, 2.3f,2.3f);
        if(h3)
            batch.draw(heart3, camera.position.x - DesertMarauderMain.WIDTH / 80f + 7f, 0.3f, 2.3f,2.3f);
    }

    public void updateHeart(){
        if(player.getLifePoints() == 2)
            h3 = false;
        else if(player.getLifePoints() == 1)
            h2 = false;
        else if (player.getLifePoints() == 0) {
            h1 = false;
        }
    }

    public void ifOffScreen(Camera camera){
        if(player.getPosition().x < camera.position.x - DesertMarauderMain.WIDTH / 80f - 5){
            if(!tookDmg) {
                player.takeDamage();
                tookDmg = true;
            }

        }
    }
    public static boolean isDead(){
        return dead;
    }

    public Player getPlayer(){
        return player;
    }

    public static void setTookDmg(boolean tof){
        tookDmg = tof;
    }

    public void playerTakeDmg(){
        player.playerTakeDmg();
    }

    public void pushPlayer(){
        player.pushPlayer();
    }
    public static boolean getTookDmg(){
        return tookDmg;
    }
}
