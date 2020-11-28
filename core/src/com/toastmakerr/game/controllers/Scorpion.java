package com.toastmakerr.game.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.animations.EnemyAction;
import com.toastmakerr.game.animations.EnemyAnimation;

public class Scorpion extends DynamicGameObject {
    private EnemyAnimation enemyAnimation;
    private final static Vector2 HIT_BOX = new Vector2(0.75f, 0.5F);
    private final static Vector2 STARTING_POS = new Vector2(40, 17f);
    private final static Vector2 STARTING_VEL = new Vector2(0, 0);
    private final static float WALKING_VEL = 0.1f;
    private final static int SCORPION_LIFE_POINTS = 1;

    public Scorpion(AssetsManager assetManager) {
        super(STARTING_POS, HIT_BOX, STARTING_VEL, SCORPION_LIFE_POINTS);
        enemyAnimation= new EnemyAnimation(assetManager);
    }

    public void update(Player player) {
        inputHandler();
        updateDetails();
        animationHandler(player);
        enemyAnimation.setPos(this.getPosition());
        enemyAnimation.setAnimation();
        enemyAnimation.setDimensions(this.getDimensions());
    }

    public void draw(SpriteBatch batch){
        if(isAlive())
            enemyAnimation.draw(batch);
    }

    public void animationHandler(Player player) {
        if (!enemyAnimation.isAttackAction() || enemyAnimation.isAnimFinished(3)) {
            if (this.inAttackRange(player) && getGrounded()) {
                enemyAnimation.setFrameDuration(0.05f);
                enemyAnimation.setAction(EnemyAction.ATTACK);
            } else if (enemyAnimation.getAction() != EnemyAction.DEAD) {
                enemyAnimation.setFrameDuration(0.05f);
                enemyAnimation.setAction(EnemyAction.WALK);
            }
        }
    }

    public void inputHandler() {
        if ( enemyAnimation.getAction() == EnemyAction.ATTACK && getGrounded()) {
            this.setVelocityX(0);
        } else if (!enemyAnimation.isAttackAction()) {
            this.setVelocityX(-WALKING_VEL);
        }
    }

    public void dealDamage(Player player){
        if(inAttackRange(player) && enemyAnimation.getAction() == EnemyAction.ATTACK){
            player.takeDamage();
        }
    }

}



