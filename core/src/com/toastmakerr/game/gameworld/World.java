package com.toastmakerr.game.gameworld;

import com.toastmakerr.game.controllers.GameObject;

import java.util.ArrayList;

public class World {
    private ArrayList<GameObject> groundObjs;
    private ArrayList<GameObject> platformObjs;
    private ArrayList<GameObject> wallObjs;
    private ArrayList<ScorpionManager> scorpions;
    public World(){
        groundObjs = new ArrayList<>();
        platformObjs = new ArrayList<>();
        wallObjs = new ArrayList<>();
        scorpions = new ArrayList<>();
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

    public void addScorpion(ScorpionManager scorpion){
        scorpions.add(scorpion);
    }

    public ArrayList<ScorpionManager> getScorpionsObj(){
        return scorpions;
    }

    public void removeScorpion(int index){
        scorpions.remove(index);
    }

}
