package com.toastmakerr.game.gameworld;

import com.toastmakerr.game.controllers.GameObject;

import java.util.ArrayList;

public class World {
    ArrayList<GameObject> gameObjs;
    public World(){
        gameObjs = new ArrayList<>();
    }

    public void addObj(GameObject obj){
        gameObjs.add(obj);
    }

    public void removeObj(GameObject obj){
        gameObjs.remove(obj);
    }
}
