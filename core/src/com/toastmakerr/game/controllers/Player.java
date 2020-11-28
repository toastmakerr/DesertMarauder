package com.toastmakerr.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.animations.PlayerAction;
import com.toastmakerr.game.animations.PlayerAnimation;
import com.toastmakerr.game.gameworld.WorldManager;


public class Player extends DynamicGameObject {
    private PlayerAnimation playerAnimation;
    private final static Vector2 HIT_BOX = new Vector2(0.5f, 2.75f);
    private final static Vector2 STARTING_POS = new Vector2(2, 3f);
    private final static Vector2 STARTING_VEL = new Vector2(0, 0);
    private final static float RUNNING_VEL = 0.25f;
    private final static float RUNNING_ATTACK_VEL = 0.18f;
    private final static float WALKING_VEL = 0.12f;
    private final static float JUMPING_VEL = 1.5f;
    private final static int PLAYER_LIFE_POINTS = 3;
    private final static float MAX_WALK_VELOCITY = 10f;
    private final static float MAX_RUN_VELOCITY = 20f;
    private static boolean MOVED = false;
    private static boolean stopMovement = false;
    private static boolean grounded = false;

    public BodyDef bodyDef;
    public Body body;


    public Player(AssetsManager assetManager) {
        super(STARTING_POS, HIT_BOX, STARTING_VEL, PLAYER_LIFE_POINTS);
        playerAnimation = new PlayerAnimation(assetManager);
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(3, 5f);
        bodyDef.fixedRotation = true;
        body = WorldManager.world.createBody(bodyDef);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(1,2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 0.4f;

        Fixture fixture = body.createFixture(fixtureDef);
        body.setUserData(this);
        polygonShape.dispose();
    }

    public void update() {
        inputHandler();
        updateDetails();
        animationHandler();
        playerAnimation.setPos(body.getPosition());
        playerAnimation.setDimensions(this.getDimensions());
    }

    public void draw(SpriteBatch batch){
        playerAnimation.draw(batch);
    }


    public void animationHandler() {
        if (!playerAnimation.isAttackAction() || (playerAnimation.isAttackAction() && playerAnimation.isAnimFinished(1))) {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !this.getGrounded()) {
                playerAnimation.setFrameDuration(0.045f);
                playerAnimation.setAction(PlayerAction.JUMP);
                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    playerAnimation.flipSprite(false);
                } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    playerAnimation.flipSprite(true);
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                playerAnimation.setFrameDuration(0.06f);
                playerAnimation.setAction(PlayerAction.ATTACK_3);
                playerAnimation.flipSprite(true);
            } else if (Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                playerAnimation.setFrameDuration(0.06f);
                playerAnimation.setAction(PlayerAction.ATTACK_3);
                playerAnimation.flipSprite(false);
            } else if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && getGrounded()) {
                playerAnimation.setFrameDuration(0.05f);
                playerAnimation.setRandomAttackAction();
            }else if (Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
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
            } else if (!Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.S) && !Gdx.input.isKeyPressed(Input.Keys.D)|| Gdx.input.isKeyPressed(Input.Keys.S)) {
                playerAnimation.setFrameDuration(0.25f);
                playerAnimation.setAction(PlayerAction.IDLE);
            }
        }
        playerAnimation.setAnimation();
    }

    public void inputHandler() {
        if((Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.W)) && grounded){
            body.applyLinearImpulse(0f, 100f, 0f, 0f, true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                if (body.getLinearVelocity().x <= MAX_RUN_VELOCITY)
                    body.applyLinearImpulse(12f, 0f, 0f, 0f, true);
            }
            else {
                if (body.getLinearVelocity().x <= MAX_WALK_VELOCITY)
                    body.applyLinearImpulse(8f, 0f, 0f, 0f, true);
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                if (body.getLinearVelocity().x >= -MAX_RUN_VELOCITY)
                    body.applyLinearImpulse(-12f, 0f, 0f, 0f, true);
            }
            else {
                if (body.getLinearVelocity().x >= -MAX_WALK_VELOCITY)
                    body.applyLinearImpulse(-8f, 0f, 0f, 0f, true);
            }
        }
        else{
            body.setLinearVelocity(0, body.getLinearVelocity().y);
        }


      /*  if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !playerAnimation.isAttackAction() && !stopMovement) {
            if (this.getGrounded()) {
                this.setVelocityY(JUMPING_VEL);
                this.setGrounded(false);
            }
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && getGrounded() && playerAnimation.getAction() != PlayerAction.ATTACK_3) {
            this.setVelocityX(0);
        } else if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && getGrounded() && playerAnimation.getAction() == PlayerAction.ATTACK_3 && Gdx.input.isKeyPressed(Input.Keys.A)){
            this.setVelocityX(-RUNNING_ATTACK_VEL);
        } else if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && getGrounded() && playerAnimation.getAction() == PlayerAction.ATTACK_3 && Gdx.input.isKeyPressed(Input.Keys.D)){
            this.setVelocityX(RUNNING_ATTACK_VEL);
        }else if (!playerAnimation.isAttackAction()) {
            if (Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                Player.setMove(true);
                this.setVelocityX(-RUNNING_VEL);
            } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                this.setVelocityX(-WALKING_VEL);
                Player.setMove(true);
            } else if (Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                Player.setMove(true);
                this.setVelocityX(RUNNING_VEL);
            } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                this.setVelocityX(WALKING_VEL);
                Player.setMove(true);
            } else if (!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyJustPressed(Input.Keys.A)) {
                if (this.getGrounded()) {
                    this.setVelocityX(0);
                }
            }
        }*/
    }

    public void dealDamage(DynamicGameObject entity){
        if(inAttackRange(entity) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            entity.takeDamage();
        }
    }

    public void pushPlayer(){
        this.setVelocityX(-1f);
    }

    public static void setMove(boolean tof){
        MOVED = tof;
    }

    public static boolean getMove(){
        return MOVED;
    }

    public static void setPlayerGrounded(boolean g){
        grounded = g;
    }

    public Object getUserData(){
        return body.getUserData();
    }

}



