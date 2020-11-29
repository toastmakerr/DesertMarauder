package com.toastmakerr.game.controllers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import java.util.ArrayList;

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

    /*public void collisions(ArrayList<GameObject> groundObjs, ArrayList<GameObject> platformObjs, ArrayList<GameObject> wallObjs){
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
    }*/

    /*public boolean inAttackRange(DynamicGameObject entity){
        if((Math.abs(this.obj.x - entity.obj.x) < 0.3f) && (this.obj.y == entity.obj.y) ){
            return true;
        }
        return false;
    }*/

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

