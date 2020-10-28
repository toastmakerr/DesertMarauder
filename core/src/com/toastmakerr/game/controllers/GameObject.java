package com.toastmakerr.game.controllers;

import com.badlogic.gdx.math.Vector2;

public class GameObject {
    protected Vector2 pos;
    protected Vector2 hitBox;

    public GameObject(Vector2 pos, Vector2 hitBox){
        this.pos = pos;
        this.hitBox = hitBox;
    }

    public Vector2 getPosition(){
        return pos;
    }

    public void setPosition(Vector2 pos){
        this.pos.x = pos.x;
        this.pos.y = pos.y;
    }

    public boolean collisionDetection(GameObject obj){
        if((pos.x + hitBox.x) >= (obj.pos.x - obj.hitBox.x)){
            return true;
        }
        if((pos.x - hitBox.x) <= (obj.pos.x + obj.hitBox.x)){
            return true;
        }
        if((pos.y + hitBox.y) >= (obj.pos.y - obj.hitBox.y)){
            return true;
        }
        if((pos.y - hitBox.y) <= (obj.pos.y + obj.hitBox.y)){
            return true;
        }
        return false;
    }
}
