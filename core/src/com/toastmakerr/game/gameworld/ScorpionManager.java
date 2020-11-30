package com.toastmakerr.game.gameworld;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.controllers.GameObject;
import com.toastmakerr.game.controllers.Player;
import com.toastmakerr.game.controllers.Scorpion;
import java.util.ArrayList;

public class ScorpionManager {
    private Scorpion scorpion;
    private AssetsManager assetManager;
    public ScorpionManager(AssetsManager assetManager, Vector2 startingPos, boolean move){
        scorpion = new Scorpion(assetManager, startingPos, move);
        this.assetManager = assetManager;
    }

    public void update(Player player, Camera camera){
        scorpion.update(player, camera);
    }
    public void draw(SpriteBatch batch){
        scorpion.draw(batch);
    }

    public Scorpion getScorpion(){
        return scorpion;
    }
}
