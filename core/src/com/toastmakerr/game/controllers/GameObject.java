package com.toastmakerr.game.controllers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameObject {
    protected Rectangle obj;

    public GameObject(Vector2 pos, Vector2 hitBox){
        obj = new Rectangle();
        obj.x = pos.x;
        obj.y = pos.y;
        obj.width = hitBox.x;
        obj.height = hitBox.y;
    }

    public Vector2 getPosition(){
        return new Vector2(obj.x, obj.y);
    }

    public boolean isGrounded(GameObject floorSurface){
        if(obj.y <= floorSurface.obj.y + floorSurface.obj.height)
            return true;
        return false;
    }
}
