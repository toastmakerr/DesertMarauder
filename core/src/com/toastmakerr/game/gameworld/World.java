package com.toastmakerr.game.gameworld;

import com.toastmakerr.game.controllers.GameObject;

import java.util.ArrayList;

public class World {
    protected ArrayList<GameObject> floorObjs;
    protected ArrayList<GameObject> wallObjs;
    public World(){
        floorObjs = new ArrayList<>();
        wallObjs = new ArrayList<>();
    }

    public void addFloorObj(GameObject obj){
        floorObjs.add(obj);
    }

    public void addWallObj(GameObject obj){
        wallObjs.add(obj);
    }

    public void removeFloorObj(GameObject obj){
        floorObjs.remove(obj);
    }
}
