package com.toastmakerr.game.controllers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class DynamicGameObject extends GameObject{
    private boolean isGrounded;
    private int lifePoints;
    private boolean alive;

    public DynamicGameObject(Vector2 pos, Vector2 bodyDimensions, String userData, int lifePoints){
        super(pos, bodyDimensions, BodyDef.BodyType.DynamicBody, userData);
        isGrounded = true;
        this.lifePoints = lifePoints;
        alive = true;
    }

    public void updateDetails(){
        die();
    }

    public boolean inAttackRange(float attackRange, DynamicGameObject entity){
        if((Math.abs(this.getPosition().x  - entity.getPosition().x) < attackRange) && (Math.abs(this.getPosition().y  - entity.getPosition().y) < 2f)) {
            return true;
        }
        return false;
    }

    public void takeDamage(){
        lifePoints--;
    }

    public boolean getGrounded(){
        return isGrounded;
    }

    public void setGrounded(boolean bool){
        isGrounded = bool;
    }

    public int getLifePoints(){
        return lifePoints;
    }

    public void die(){
        if(lifePoints == 0)
            alive = false;
    }

    public Boolean isAlive(){
        return alive;
    }

}

