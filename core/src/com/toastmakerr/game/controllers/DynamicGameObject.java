package com.toastmakerr.game.controllers;

import com.badlogic.gdx.math.Vector2;

public class DynamicGameObject extends GameObject{
    private boolean isGrounded;
    private Vector2 vel;
    private final static float GRAVITY = -0.15f;

    public DynamicGameObject(Vector2 pos, Vector2 hitBox, Vector2 vel){
        super(pos, hitBox);
        this.vel = vel;
        isGrounded = false;
    }

    public void updatePos(){
        vel.y += GRAVITY;
        pos.x += vel.x;
        pos.y += vel.y;
        hitBox.x += vel.x;
        hitBox.y += vel.y;
        onGround();
    }

    public void setVelocityX(float velX){
        this.vel.x = velX;
    }

    public void setVelocityY(float velY){
        this.vel.y = velY;
    }

    public void onGround(){
        if(pos.y <= 2.5){
            pos.y = 2.5f;
            vel.y = 0;
            isGrounded = true;
        }
    }

    public boolean isGrounded(){
        return isGrounded;
    }

    public void setGrounded(boolean bool){
        isGrounded = bool;
    }
}
