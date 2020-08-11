package com.toastmakerr.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class StaticGameObject extends BodyDef{
    private Body body;

    public StaticGameObject(World world){
        this.type = BodyType.StaticBody;
        this.position.set(position);
        body = world.createBody(this);
    }

    public Body getBody(){
        return body;
    }

    public Vector2 getPosition(){
        return body.getPosition();
    }
}

