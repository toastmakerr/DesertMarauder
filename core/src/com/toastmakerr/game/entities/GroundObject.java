package com.toastmakerr.game.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class GroundObject {
    private StaticGameObject groundObj;
    PolygonShape groundBox;
    public GroundObject(World world, OrthographicCamera camera){
        groundObj = new StaticGameObject(world);
        groundBox = new PolygonShape();
        groundBox.setAsBox(camera.viewportWidth,2f);
        groundObj.getBody().createFixture(groundBox,0f);
    }

    public void dispose(){
        groundBox.dispose();
    }
}
