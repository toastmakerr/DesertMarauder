package com.toastmakerr.game.controllers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class DynamicGameObject extends BodyDef{
    private Body body;
    private static Vector2 position;
    private static Vector2 velocity;

    public DynamicGameObject(World world, Vector2 pos, Vector2 vel){
        position = pos;
        velocity = vel;
        this.type = BodyType.DynamicBody;
        this.position.set(position);
        body = world.createBody(this);
    }

    public Vector2 getPosition(){
        return position;
    }

    public Vector2 getVelocity(){
        return velocity;
    }

    public void setPosition(Vector2 newPos){
        position = newPos;
    }

    public void setVelocity(Vector2 newVel){
        velocity = newVel;
    }
}
