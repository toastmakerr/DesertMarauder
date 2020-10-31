package com.toastmakerr.game.gameworld;

import com.toastmakerr.game.controllers.GameObject;

import java.util.ArrayList;

public class World {
    protected ArrayList<GameObject> floorObjs;
    public World(){
        floorObjs = new ArrayList<>();
    }

    public void addObj(GameObject obj){
        floorObjs.add(obj);
    }

    public void removeObj(GameObject obj){
        floorObjs.remove(obj);
    }
}
