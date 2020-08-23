package com.toastmakerr.game.controllers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class DynamicGameObject extends BodyDef{
    private Body body;

    public DynamicGameObject(World world, Vector2 pos){
        this.type = BodyType.DynamicBody;
        this.position.set(pos);
        body = world.createBody(this);
    }

    public Body getBody(){
        return body;
    }
    public Vector2 getPosition(){
        return body.getPosition();
    }

    public Vector2 getVelocity(){
        return body.getLinearVelocity();
    }

    public void moveDynamicObj(Vector2 vel){
        body.setLinearVelocity(vel);
    }
}
