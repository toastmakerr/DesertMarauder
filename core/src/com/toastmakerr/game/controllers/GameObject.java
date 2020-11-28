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

    public void setPosition(float x, float y){
        obj.x = x;
        obj.y = y;
    }

    public Vector2 getDimensions(){
        return new Vector2(obj.width, obj.height);
    }

    public void shiftPositionX(float x){
        obj.x += x;
    }

    public boolean touchingGround(GameObject floorSurface){
        if((int)obj.y <= (int)(floorSurface.obj.y + floorSurface.obj.height) && (int)obj.y >= (int)(floorSurface.obj.y + floorSurface.obj.height - 0.0001))
            if((int)(obj.x + obj.width) >= (int)floorSurface.obj.x && (int)obj.x <= (int)(floorSurface.obj.x + floorSurface.obj.width))
                return true;
        return false;
    }

    public boolean touchingCeiling(GameObject ceilingSurface){
        if((int)(obj.y + obj.height) == ceilingSurface.obj.y)
            if(obj.x + obj.width > ceilingSurface.obj.x && obj.x < ceilingSurface.obj.x + ceilingSurface.obj.width)
                return true;
        return false;
    }

    public boolean touchingWall(GameObject wallObject){
        if(obj.y < wallObject.obj.y + wallObject.obj.height)
            if(obj.x < wallObject.obj.x + wallObject.obj.width || obj.x + obj.width > wallObject.obj.x)
                return true;
        return false;
    }

    public boolean objOverlaps(GameObject obj){
        if(this.obj.overlaps(obj.obj))
            return true;
        return false;
    }

    public Rectangle getRectangle(){
        return obj;
    }

}
