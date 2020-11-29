package com.toastmakerr.game.controllers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.toastmakerr.game.gameworld.WorldManager;

public class GameObject {
    private BodyDef bodyDef;
    private Body body;
    private Vector2 bodyDimensions;

    public GameObject(Vector2 pos, Vector2 bodyDimensions, BodyDef.BodyType bodyType, String userData){
        bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(pos); //Players (3,5)
        bodyDef.fixedRotation = true;
        body = WorldManager.world.createBody(bodyDef);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(bodyDimensions.x, bodyDimensions.y);
        this.bodyDimensions = bodyDimensions;

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 0.4f;

        Fixture fixture = body.createFixture(fixtureDef);
        body.setUserData(userData);
        polygonShape.dispose();
    }

    public Vector2 getPosition(){
        return body.getPosition();
    }

    public void setPosition(float x, float y){
        body.setTransform(x, y, body.getAngle());
    }

    public Vector2 getDimensions(){
        return bodyDimensions;
    }

    public Object getUserData(){
        return body.getUserData();
    }

    public void setLinearVel(float x, float y){
        body.setLinearVelocity(x, y);
    }

    public Vector2 getLinearVel(){
        return body.getLinearVelocity();
    }

    public void applyLinearImp(float impX, float impY, float pX, float pY, boolean wake){
        body.applyLinearImpulse(impX, impY, pX, pY, wake);
    }

    public Body getBody(){
        return body;
    }


    //public void setPosition(float x, float y){
    //}

    //public void shiftPositionX(float x){
     //   obj.x += x;
    //}

   /* public boolean touchingGround(GameObject floorSurface){
        if((int)obj.y <= (int)(floorSurface.obj.y + floorSurface.obj.height) && (int)obj.y >= (int)(floorSurface.obj.y + floorSurface.obj.height - 0.0001))
            if((int)(obj.x + obj.width) >= (int)floorSurface.obj.x && (int)obj.x <= (int)(floorSurface.obj.x + floorSurface.obj.width))
                return true;
        return false;*/
    /*}

    public boolean touchingCeiling(GameObject ceilingSurface){
        if((int)(obj.y + obj.height) == ceilingSurface.obj.y)
            if(obj.x + obj.width > ceilingSurface.obj.x && obj.x < ceilingSurface.obj.x + ceilingSurface.obj.width)
                return true;
        return false;
    }*/

/*    public boolean touchingWall(GameObject wallObject){
        if(obj.y < wallObject.obj.y + wallObject.obj.height)
            if(obj.x < wallObject.obj.x + wallObject.obj.width || obj.x + obj.width > wallObject.obj.x)
                return true;
        return false;
    }*/

/*    public boolean objOverlaps(GameObject obj){
        if(this.obj.overlaps(obj.obj))
            return true;
        return false;
    }*/

}
