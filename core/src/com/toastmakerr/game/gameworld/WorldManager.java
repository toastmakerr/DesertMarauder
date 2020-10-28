package com.toastmakerr.game.gameworld;

import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.animations.PlayerAnimation;
import com.toastmakerr.game.controllers.Player;

public class WorldManager extends World {
    private PlayerAnimation playerAnimation;
    private Player player;
    public WorldManager(AssetsManager assetManager){
        playerAnimation = new PlayerAnimation(assetManager);
        player = new Player(assetManager);
    }

    public void update(){
        playerAnimation.setAnimation();
        player.update();
    }

    public PlayerAnimation getPlayerAnimation(){
        return playerAnimation;
    }
}
