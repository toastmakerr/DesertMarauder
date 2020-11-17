package com.toastmakerr.game.gameworld;

import com.toastmakerr.game.controllers.GameObject;

import java.util.ArrayList;

public class World {
    private ArrayList<GameObject> groundObjs;
    private ArrayList<GameObject> platformObjs;
    private ArrayList<GameObject> wallObjs;
    public World(){
        groundObjs = new ArrayList<>();
        platformObjs = new ArrayList<>();
        wallObjs = new ArrayList<>();
    }

    public void addGroundObj(GameObject obj){
        groundObjs.add(obj);
    }

    public ArrayList<GameObject> getGroundObj(){
        return groundObjs;
    }

    public void removeGroundObj(int index){
        groundObjs.remove(index);
    }

    public void addPlatformObj(GameObject obj){
        platformObjs.add(obj);
    }

    public ArrayList<GameObject> getPlatformObj(){
        return platformObjs;
    }

    public void removePlatformObj(int index){
        platformObjs.remove(index);
    }

    public void addWallObj(GameObject obj){
        wallObjs.add(obj);
    }

    public ArrayList<GameObject> getWallObj(){
        return wallObjs;
    }

    public void removeWallObj(int index){
        wallObjs.remove(index);
    }

}
