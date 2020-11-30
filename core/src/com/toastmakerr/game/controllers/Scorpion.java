package com.toastmakerr.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.DesertMarauderMain;
import com.toastmakerr.game.animations.EnemyAction;
import com.toastmakerr.game.animations.EnemyAnimation;

public class Scorpion extends DynamicGameObject {
    private EnemyAnimation enemyAnimation;
    private final static Vector2 BODY_DIMENSIONS = new Vector2(1.5f, 0.7f);
    private final static Vector2 STARTING_POS = new Vector2(35, 9.15f);
    private final static float WALKING_VEL = 0.1f;
    private final static int SCORPION_LIFE_POINTS = 1;
    private final static float MAX_VELOCITY = 5f;
    private final static float ATTACK_RANGE = 2.5f;
    private boolean move = false;
    private float clock = 0;
    private boolean dead = false;
    public Scorpion(AssetsManager assetManager, Vector2 startPos, boolean move) {
        super(startPos, BODY_DIMENSIONS, "Scorpion", SCORPION_LIFE_POINTS);
        enemyAnimation = new EnemyAnimation(assetManager, startPos);
    }

    public void update(Player player, Camera camera) {
        deathTimer();
        updateDead(camera);
        inputHandler();
        updateDetails();
        animationHandler(player);
        enemyAnimation.setAnimation();
        enemyAnimation.setPos(this.getPosition());
        enemyAnimation.setDimensions(this.getDimensions());
    }

    public void draw(SpriteBatch batch){
            enemyAnimation.draw(batch);
    }

    public void animationHandler(Player player) {
        if (!enemyAnimation.isAttackAction() || enemyAnimation.isAnimFinished(1)) {
           /* if (this.inAttackRange(player) && getGrounded()) {
                enemyAnimation.setFrameDuration(0.05f);
                enemyAnimation.setAction(EnemyAction.ATTACK);
            } else */
           if(!this.isAlive()){
                enemyAnimation.setFrameDuration(0.1f);
                enemyAnimation.setAction(EnemyAction.DEAD);
            }
           else if (enemyAnimation.getAction() != EnemyAction.DEAD) {
                enemyAnimation.setFrameDuration(0.05f);
                enemyAnimation.setAction(EnemyAction.WALK);
            }
        }
    }

    public void inputHandler() {
        if(move)
            if (this.getLinearVel().x >= -MAX_VELOCITY)
                this.applyLinearImp(-2f, 0f, 0f, 0f, true);
    }

    public void deathTimer(){
        if(!this.isAlive() && !dead)
            clock += Gdx.graphics.getDeltaTime();
        if(dead)
            clock = 0;
    }

    public float getDeathTimer(){
        return clock;
    }

    public void updateDead(Camera camera){
        if(this.getDeathTimer() > 0.3f || this.getPosition().x < camera.position.x - DesertMarauderMain.WIDTH / 80 - 5){
            dead = true;
        }
    }

    public boolean getDead(){
        return dead;
    }

}



