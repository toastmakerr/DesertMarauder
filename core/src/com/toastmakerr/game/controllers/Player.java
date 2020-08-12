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
        playerAnimation = new PlayerAnimation(assetManager);
        player = new DynamicGameObject(world, playerAnimation.getPos());
    }

    public void update(){
        playerAnimation.setPos(player.getPosition());
    }

    public void inputHandler(){ //Need to add joypad option for android
        if(!Gdx.input.isKeyPressed(Input.Keys.W) || !Gdx.input.isKeyPressed(Input.Keys.A) || !Gdx.input.isKeyPressed(Input.Keys.S) || !Gdx.input.isKeyPressed(Input.Keys.D)){
            playerAnimation.setFrameDuration(0.25f);
            playerAnimation.setAction(PlayerAction.IDLE);
            player.moveDynamicObj(new Vector2(0,0));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            playerAnimation.setFrameDuration(0.08f);
            playerAnimation.setAction(PlayerAction.JUMP);
            player.moveDynamicObj(new Vector2(0,10f));
            if(Gdx.input.isKeyPressed(Input.Keys.D)){
                playerAnimation.flipSprite(false);
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.A)){
                playerAnimation.flipSprite(true);
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.W)){
            playerAnimation.setFrameDuration(0.07f);
            playerAnimation.setAction(PlayerAction.JUMP);
            player.moveDynamicObj(new Vector2(0,10f));
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)){
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            playerAnimation.setFrameDuration(0.05f);
            playerAnimation.flipSprite(true);
            playerAnimation.setAction(PlayerAction.RUN);
            player.moveDynamicObj(new Vector2(-10f,0));
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            playerAnimation.setFrameDuration(0.07f);
            playerAnimation.flipSprite(true);
            playerAnimation.setAction(PlayerAction.WALK);
            player.moveDynamicObj(new Vector2(-5f,0));
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            playerAnimation.setFrameDuration(0.05f);
            playerAnimation.flipSprite(false);
            playerAnimation.setAction(PlayerAction.RUN);
            player.moveDynamicObj(new Vector2(10f,0));
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            playerAnimation.setFrameDuration(0.07f);
            playerAnimation.flipSprite(false);
            playerAnimation.setAction(PlayerAction.WALK);
            player.moveDynamicObj(new Vector2(5f,0));
        }
    }

}
