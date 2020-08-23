package com.toastmakerr.game.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class GroundObject {
    private StaticGameObject groundObj;
    private PolygonShape groundBox;

    public GroundObject(World world, OrthographicCamera camera){
        groundObj = new StaticGameObject(world, new Vector2(0,0));
        groundBox = new PolygonShape();
        groundBox.setAsBox(camera.viewportWidth,0.55f);
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