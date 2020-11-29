package com.toastmakerr.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.animations.EnemyAction;
import com.toastmakerr.game.animations.EnemyAnimation;

public class Scorpion extends DynamicGameObject {
    private EnemyAnimation enemyAnimation;
    private final static Vector2 BODY_DIMENSIONS = new Vector2(1.5f, 0.7f);
    private final static Vector2 STARTING_POS = new Vector2(35, 9.15f);
    private final static float WALKING_VEL = 0.1f;
    private final static int SCORPION_LIFE_POINTS = 1;
    private final static float MAX_VELOCITY = 5f;
    public Scorpion(AssetsManager assetManager) {
        super(STARTING_POS, BODY_DIMENSIONS, "Scorpion", SCORPION_LIFE_POINTS);
        enemyAnimation= new EnemyAnimation(assetManager);
    }

    public void update(Player player) {
        inputHandler();
        //updateDetails();
        animationHandler(player);
        enemyAnimation.setPos(this.getPosition());
        //enemyAnimation.setAnimation();
        enemyAnimation.setDimensions(this.getDimensions());
    }

    public void draw(SpriteBatch batch){
        if(isAlive())
            enemyAnimation.draw(batch);
    }

    public void animationHandler(Player player) {
        if (!enemyAnimation.isAttackAction() || enemyAnimation.isAnimFinished(3)) {
           /* if (this.inAttackRange(player) && getGrounded()) {
                enemyAnimation.setFrameDuration(0.05f);
                enemyAnimation.setAction(EnemyAction.ATTACK);
            } else */if (enemyAnimation.getAction() != EnemyAction.DEAD) {
                enemyAnimation.setFrameDuration(0.05f);
                enemyAnimation.setAction(EnemyAction.WALK);
            }
        }
    }

    public void inputHandler() {
            if (this.getLinearVel().x >= -MAX_VELOCITY)
                this.applyLinearImp(-2f, 0f, 0f, 0f, true);
    }

    /*public void dealDamage(Player player){
        if(inAttackRange(player) && enemyAnimation.getAction() == EnemyAction.ATTACK){
            player.takeDamage();
        }
    }*/

}



