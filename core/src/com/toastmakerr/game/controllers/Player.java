package com.toastmakerr.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.animations.PlayerAction;
import com.toastmakerr.game.animations.PlayerAnimation;

public class Player extends DynamicGameObject {
    private PlayerAnimation playerAnimation;
    private final static Vector2 BODY_DIMENSIONS = new Vector2(0.9f, 1.4f);
    private final static Vector2 STARTING_POS = new Vector2(2, 5f);
    private final static float RUNNING_VEL = 12f;
    private final static float RUNNING_ATTACK_VEL = 10;
    private final static float WALKING_VEL = 8;
    private final static float JUMPING_VEL = 85f;
    private final static int PLAYER_LIFE_POINTS = 3;
    private final static float MAX_WALK_VELOCITY = 10f;
    private final static float MAX_RUN_VELOCITY = 20f;
    private final static float ATTACK_RANGE = 3.5f;
    private static boolean MOVED = false;
    private static int contactAmount;

    public Player(AssetsManager assetManager) {
        super(STARTING_POS, BODY_DIMENSIONS,"Player" ,PLAYER_LIFE_POINTS);
        playerAnimation = new PlayerAnimation(assetManager);
        contactAmount = 0;
    }

    public void update() {
        inputHandler();
        updateDetails();
        animationHandler();
        playerAnimation.setPos(this.getPosition());
        playerAnimation.setDimensions(this.getDimensions());
    }

    public void draw(SpriteBatch batch){
        playerAnimation.draw(batch);
    }


    public void animationHandler() {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            playerAnimation.flipSprite(false);
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            playerAnimation.flipSprite(true);
        }
        if (!playerAnimation.actionsToFinish() || (playerAnimation.actionsToFinish() && playerAnimation.isAnimFinished(1))) {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && contactAmount == 0) {
                playerAnimation.setFrameDuration(0.12f);
                playerAnimation.setAction(PlayerAction.JUMP);
            } else if (Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                playerAnimation.setFrameDuration(0.06f);
                playerAnimation.setAction(PlayerAction.ATTACK_3);
            } else if (Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                playerAnimation.setFrameDuration(0.06f);
                playerAnimation.setAction(PlayerAction.ATTACK_3);
            } else if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && contactAmount > 0) {
                playerAnimation.setFrameDuration(0.05f);
                playerAnimation.setRandomAttackAction();
            }else if (Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                playerAnimation.setFrameDuration(0.05f);
                playerAnimation.setAction(PlayerAction.RUN);
            } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                playerAnimation.setFrameDuration(0.07f);
                playerAnimation.setAction(PlayerAction.WALK);
            } else if (Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                playerAnimation.setFrameDuration(0.05f);
                playerAnimation.setAction(PlayerAction.RUN);
            } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                playerAnimation.setFrameDuration(0.07f);
                playerAnimation.setAction(PlayerAction.WALK);
            } else if (!Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.S) && !Gdx.input.isKeyPressed(Input.Keys.D)|| Gdx.input.isKeyPressed(Input.Keys.S)) {
                playerAnimation.setFrameDuration(0.25f);
                playerAnimation.setAction(PlayerAction.IDLE);
            }
        }
        playerAnimation.setAnimation();
    }

    public void inputHandler() {
        if((Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.W)) && contactAmount > 0){
            this.applyLinearImp(0f, JUMPING_VEL, 0f, 0f, true);
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && contactAmount > 0 && playerAnimation.getAction() != PlayerAction.ATTACK_3) {
            this.setLinearVel(0, this.getLinearVel().y);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                    if (this.getLinearVel().x <= MAX_RUN_VELOCITY)
                        this.applyLinearImp(RUNNING_ATTACK_VEL, 0f, 0f, 0f, true);
                } else{
                    if (this.getLinearVel().x <= MAX_RUN_VELOCITY)
                        this.applyLinearImp(RUNNING_VEL, 0f, 0f, 0f, true);
                }
            }
            else {
                if (this.getLinearVel().x <= MAX_WALK_VELOCITY)
                    this.applyLinearImp(WALKING_VEL, 0f, 0f, 0f, true);
            }
            setMove(true);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                    if (this.getLinearVel().x >= -MAX_RUN_VELOCITY)
                        this.applyLinearImp(-RUNNING_ATTACK_VEL, 0f, 0f, 0f, true);
                } else{
                    if (this.getLinearVel().x >= -MAX_RUN_VELOCITY)
                        this.applyLinearImp(-RUNNING_VEL, 0f, 0f, 0f, true);
                }
            }
            else {
                if (this.getLinearVel().x >= -MAX_WALK_VELOCITY)
                    this.applyLinearImp(-WALKING_VEL, 0f, 0f, 0f, true);
            }
            setMove(true);
        }
        else{
            this.setLinearVel(0, this.getLinearVel().y);
        }
    }

    public static void incrementContacts(){
        contactAmount++;
    }

    public static void decrementContacts(){
        contactAmount--;
    }

    public static int getContactAmount(){
        return contactAmount;
    }

    public void dealDamage(DynamicGameObject entity){
        if(inAttackRange(ATTACK_RANGE, entity)){
            entity.takeDamage();
        }
    }

    public void pushPlayer(){
        if(playerAnimation.getAction() != PlayerAction.ATTACK_3)
            this.applyLinearImp(-100, 100, 0,0, true);
    }

    public static void setMove(boolean tof){
        MOVED = tof;
    }

    public static boolean getMove(){
        return MOVED;
    }

    public void playerTakeDmg(){
        if(playerAnimation.getAction() != PlayerAction.ATTACK_3)
            this.takeDamage();
    }

}



