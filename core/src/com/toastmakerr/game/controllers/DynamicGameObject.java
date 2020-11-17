package com.toastmakerr.game.controllers;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class DynamicGameObject extends GameObject{
    private boolean isGrounded;
    private Vector2 vel;
    private static float GRAVITY = -0.13f;

    public DynamicGameObject(Vector2 pos, Vector2 hitBox, Vector2 vel){
        super(pos, hitBox);
        this.vel = vel;
        isGrounded = false;
    }

    public void updatePos(){
        vel.y += GRAVITY;
        this.obj.x += vel.x;
        this.obj.y += vel.y;
    }

    public void setVelocityX(float velX){
        this.vel.x = velX;
    }

    public void setVelocityY(float velY){
        this.vel.y = velY;
    }

    public void collisions(ArrayList<GameObject> groundObjs, ArrayList<GameObject> platformObjs, ArrayList<GameObject> wallObjs){
        for(int i = 0; i < groundObjs.size(); i++) {
            if (touchingGround(groundObjs.get(i))) {
                obj.y = groundObjs.get(i).obj.y + groundObjs.get(i).obj.height;
                vel.y = 0;
                isGrounded = true;
            }
            if(touchingCeiling(groundObjs.get(i)))
                vel.y = 0;
        }
        for(int i = 0; i < platformObjs.size(); i++) {
            if (touchingGround(platformObjs.get(i))) {
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

    public boolean getGrounded(){
        return isGrounded;
    }

    public void setGrounded(boolean bool){
        isGrounded = bool;
    }
}

