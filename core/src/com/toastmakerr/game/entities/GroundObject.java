package com.toastmakerr.game.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class GroundObject {
    private StaticGameObject groundObj;
    private ChainShape groundBox;
    private Vector2[] vertices;

    public GroundObject(World world, OrthographicCamera camera){
        groundObj = new StaticGameObject(world, new Vector2(0,0));
        groundBox = new ChainShape();
        vertices = new Vector2[6];
        vertices[0] = new Vector2(-2f, 0.1f);
        vertices[1] = new Vector2(1.55f, 0.0f);
        vertices[2] = new Vector2(1.85f, 0.01f);
        vertices[3] = new Vector2(2.64f, 0.15f);
        vertices[4] = new Vector2(4.29f,0.15f);
        vertices[5] = new Vector2(15f,0.3f);
        groundBox.createChain(vertices);
        groundObj.getBody().createFixture(groundBox,0.0f);
        groundBox.dispose();
    }

    public void update(){
        System.out.println(groundObj.getPosition());
    }

    public void dispose(){
        groundBox.dispose();
    }
}