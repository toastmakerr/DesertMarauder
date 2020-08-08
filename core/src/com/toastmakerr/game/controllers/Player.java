package com.toastmakerr.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.animations.PlayerAction;
import com.toastmakerr.game.animations.PlayerAnimation;

public class Player {
    private static final float MAX_VELOCITY = 20;
    private PlayerAnimation playerAnimation;
    private DynamicGameObject player;

    public Player(AssetsManager assetManager, World world){
        player = new DynamicGameObject(world, new Vector2(50,50), new Vector2(0,0));
        playerAnimation = new PlayerAnimation(assetManager);
    }

    public void inputHandler(){ //Need to add joypad option for android
        if(!Gdx.input.isKeyPressed(Input.Keys.W) || !Gdx.input.isKeyPressed(Input.Keys.A) || !Gdx.input.isKeyPressed(Input.Keys.S) || !Gdx.input.isKeyPressed(Input.Keys.D)){
            playerAnimation.setFrameDuration(0.25f);
            playerAnimation.setAction(PlayerAction.IDLE);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            playerAnimation.setFrameDuration(0.08f);
            playerAnimation.setAction(PlayerAction.JUMP);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.W)){
            playerAnimation.setFrameDuration(0.07f);
            playerAnimation.setAction(PlayerAction.JUMP);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)){
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            playerAnimation.setFrameDuration(0.05f);
            playerAnimation.setAction(PlayerAction.RUN);
            playerAnimation.flipSprite(true);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            playerAnimation.setFrameDuration(0.07f);
            playerAnimation.setAction(PlayerAction.WALK);
            playerAnimation.flipSprite(true);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            playerAnimation.setFrameDuration(0.05f);
            playerAnimation.setAction(PlayerAction.RUN);
            playerAnimation.flipSprite(false);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            playerAnimation.setFrameDuration(0.07f);
            playerAnimation.setAction(PlayerAction.WALK);
            playerAnimation.flipSprite(false);
        }
    }

}
