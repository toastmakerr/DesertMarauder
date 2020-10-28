package com.toastmakerr.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.animations.PlayerAction;
import com.toastmakerr.game.animations.PlayerAnimation;

public class Player extends DynamicGameObject{
    private PlayerAnimation playerAnimation;
    private final static Vector2 HIT_BOX = new Vector2(1, 1.5f);
    private final static Vector2 STARTING_POS = new Vector2(2, 2.5f);
    private final static Vector2 STARTING_VEL = new Vector2(0, 0);
    private final static float RUNNING_VEL = 0.5f;
    private final static float WALKING_VEL = 0.15f;
    private final static float JUMPING_VEL = 2f;

    public Player(AssetsManager assetManager){
        super(STARTING_POS, HIT_BOX, STARTING_VEL);
        playerAnimation = new PlayerAnimation(assetManager);
    }

    public void update(){
        animationHandler();
        inputHandler();
        updatePos();
        playerAnimation.setPos(this.getPosition());
        System.out.println(this.getPosition());
    }


    public void animationHandler(){
        if(!Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.S) && !Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            playerAnimation.setFrameDuration(0.25f);
            playerAnimation.setAction(PlayerAction.IDLE);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            playerAnimation.setFrameDuration(0.045f);
            playerAnimation.setAction(PlayerAction.JUMP);
            if(Gdx.input.isKeyPressed(Input.Keys.D)){
                playerAnimation.flipSprite(false);
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.A)){
                playerAnimation.flipSprite(true);
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            playerAnimation.setFrameDuration(0.05f);
            playerAnimation.flipSprite(true);
            playerAnimation.setAction(PlayerAction.RUN);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            playerAnimation.setFrameDuration(0.07f);
            playerAnimation.flipSprite(true);
            playerAnimation.setAction(PlayerAction.WALK);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            playerAnimation.setFrameDuration(0.05f);
            playerAnimation.flipSprite(false);
            playerAnimation.setAction(PlayerAction.RUN);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            playerAnimation.setFrameDuration(0.07f);
            playerAnimation.flipSprite(false);
            playerAnimation.setAction(PlayerAction.WALK);
        }
    }

    public void inputHandler(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            if(this.isGrounded()) {
                this.setVelocityY(JUMPING_VEL);
                this.setGrounded(false);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            this.setVelocityX(-RUNNING_VEL);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            this.setVelocityX(-WALKING_VEL);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            this.setVelocityX(RUNNING_VEL);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            this.setVelocityX(WALKING_VEL);
        }
        else if(!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyJustPressed(Input.Keys.A)){
            if(this.isGrounded()){
                this.setVelocityX(0);
            }
        }
    }

}

