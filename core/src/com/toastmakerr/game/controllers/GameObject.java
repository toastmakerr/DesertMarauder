package com.toastmakerr.game.controllers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameObject {
    protected Rectangle obj;

    public GameObject(Vector2 pos, Vector2 hitBox){ //Replace rectangle with private members again
        obj = new Rectangle();
        obj.x = pos.x;
        obj.y = pos.y;
        obj.width = hitBox.x;
        obj.height = hitBox.y;
    }

    public Vector2 getPosition(){
        return new Vector2(obj.x, obj.y);
    }

    public Vector2 getDimensions(){
        return new Vector2(obj.width, obj.height);
    }

    public boolean isGrounded(GameObject floorSurface){
        if(obj.y <= floorSurface.obj.y + floorSurface.obj.height && obj.y >= floorSurface.obj.y)
            if(obj.x + obj.width >= floorSurface.obj.x && obj.x <= floorSurface.obj.x + floorSurface.obj.width)
                return true;
        return false;
    }
}
