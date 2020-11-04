package com.toastmakerr.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.animations.PlayerAction;
import com.toastmakerr.game.animations.PlayerAnimation;


public class Player extends DynamicGameObject {
    private PlayerAnimation playerAnimation;
    private final static Vector2 HIT_BOX = new Vector2(1.5f, 2.75f);
    private final static Vector2 STARTING_POS = new Vector2(2, 3f);
    private final static Vector2 STARTING_VEL = new Vector2(0, 0);
    private final static float RUNNING_VEL = 0.25f;
    private final static float WALKING_VEL = 0.12f;
    private final static float JUMPING_VEL = 1.5f;

    public Player(AssetsManager assetManager) {
        super(STARTING_POS, HIT_BOX, STARTING_VEL);
        playerAnimation = new PlayerAnimation(assetManager);
    }

    public void update() {
        inputHandler();
        updatePos();
        animationHandler();
        playerAnimation.setPos(this.getPosition());
        playerAnimation.setDimensions(this.getDimensions());
    }


    public void animationHandler() {
        if (!playerAnimation.isAttackAction() || playerAnimation.isAnimFinished(PlayerAction.ATTACK_1, 3)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                playerAnimation.setFrameDuration(0.045f);
                playerAnimation.setAction(PlayerAction.JUMP);
                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    playerAnimation.flipSprite(false);
                } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    playerAnimation.flipSprite(true);
                }
            } else if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && getGrounded()) {
                playerAnimation.setFrameDuration(0.05f);
                playerAnimation.setRandomAttackAction();
            } else if (Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                playerAnimation.setFrameDuration(0.05f);
                playerAnimation.flipSprite(true);
                playerAnimation.setAction(PlayerAction.RUN);
            } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                playerAnimation.setFrameDuration(0.07f);
                playerAnimation.flipSprite(true);
                playerAnimation.setAction(PlayerAction.WALK);
            } else if (Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                playerAnimation.setFrameDuration(0.05f);
                playerAnimation.flipSprite(false);
                playerAnimation.setAction(PlayerAction.RUN);
            } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                playerAnimation.setFrameDuration(0.07f);
                playerAnimation.flipSprite(false);
                playerAnimation.setAction(PlayerAction.WALK);
            } else if (!Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.S) && !Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                playerAnimation.setFrameDuration(0.25f);
                playerAnimation.setAction(PlayerAction.IDLE);
            }
        }
    }

    public void inputHandler() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !playerAnimation.isAttackAction()) {
            if (this.getGrounded()) {
                this.setVelocityY(JUMPING_VEL);
                this.setGrounded(false);
            }
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && getGrounded()) {
            this.setVelocityX(0);
        } else if (!playerAnimation.isAttackAction()) {
            if (Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                this.setVelocityX(-RUNNING_VEL);
            } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                this.setVelocityX(-WALKING_VEL);
            } else if (Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                this.setVelocityX(RUNNING_VEL);
            } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                this.setVelocityX(WALKING_VEL);
            } else if (!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyJustPressed(Input.Keys.A)) {
                if (this.getGrounded()) {
                    this.setVelocityX(0);
                }
            }
        }
    }
}



