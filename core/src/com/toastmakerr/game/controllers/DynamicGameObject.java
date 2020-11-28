package com.toastmakerr.game.controllers;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class DynamicGameObject extends GameObject{
    private boolean isGrounded;
    private Vector2 vel;
    private static float GRAVITY = 0;
    private int lifePoints;
    private boolean alive;

    public DynamicGameObject(Vector2 pos, Vector2 hitBox, Vector2 vel, int lifePoints){
        super(pos, hitBox);
        this.vel = vel;
        isGrounded = false;
        this.lifePoints = lifePoints;
        alive = true;
    }

    public void updateDetails(){
        vel.y += GRAVITY;
        this.obj.x += vel.x;
        this.obj.y += vel.y;
        die();
    }

    public void setVelocityX(float velX){
        this.vel.x = velX;
    }

    public void setVelocityY(float velY){
        this.vel.y = velY;
    }

    public void collisions(ArrayList<GameObject> groundObjs, ArrayList<GameObject> platformObjs, ArrayList<GameObject> wallObjs){
        for(int i = 0; i < groundObjs.size(); i++) {
            if(objOverlaps(groundObjs.get(i))){
                GRAVITY = 0f;
                vel.y = 0;
                isGrounded = true;
            }
            else {
                GRAVITY = -0.1f;
            }

        }
        for(int i = 0; i < platformObjs.size(); i++) {
            if (objOverlaps(platformObjs.get(i))) {
                obj.y = platformObjs.get(i).obj.y + platformObjs.get(i).obj.height;
                vel.y = 0;
                isGrounded = true;
            }
            if(touchingCeiling(platformObjs.get(i)))
                vel.y = 0;
        }
        for(int i = 0; i < wallObjs.size(); i++){
            if(touchingWall(wallObjs.get(i)))
                obj.x = wallObjs.get(i).obj.x + wallObjs.get(i).obj.width;
        }
    }

    public boolean inAttackRange(DynamicGameObject entity){
        if((Math.abs(this.obj.x - entity.obj.x) < 0.3f) && (this.obj.y == entity.obj.y) ){
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

