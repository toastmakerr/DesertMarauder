package com.toastmakerr.game.gameworld;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.controllers.GameObject;
import com.toastmakerr.game.controllers.Player;
import com.toastmakerr.game.controllers.Scorpion;

import java.util.ArrayList;

public class ScorpionManager {
    private static Boolean dead;
    private Scorpion scorpion;
    public ScorpionManager(AssetsManager assetManager){
        scorpion = new Scorpion(assetManager);
        dead = false;
    }

    public void update(Player player, ArrayList<GameObject> ground, ArrayList<GameObject> platform, ArrayList<GameObject> wall){
        scorpion.update(player);
        scorpion.collisions(ground, platform, wall);
    }
    public void draw(SpriteBatch batch, Camera camera){
        scorpion.draw(batch);
    }

    public Scorpion getScorpion(){
        return scorpion;
    }

    public static Boolean isDead(){
        return dead;
    }

}
