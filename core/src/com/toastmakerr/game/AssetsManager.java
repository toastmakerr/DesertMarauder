package com.toastmakerr.game;

import com.badlogic.gdx.assets.AssetManager;

public class AssetsManager {
    public AssetManager am;

    public AssetsManager(){
        am = new AssetManager();
        am.load(Assets.DESERT_BG);
        am.load(Assets.DESERT_BG_1);
        am.load(Assets.DESERT_BG_2);
        am.load(Assets.DESERT_BG_3);
        am.load(Assets.DESERT_BG_4);
        am.load(Assets.DESERT_BG_5);
        am.load(Assets.TITLE);
        am.load(Assets.PLAYER_IDLE);
        am.load(Assets.PLAYER_RUN);
        am.load(Assets.PLAYER_WALK);
        am.load(Assets.PLAYER_JUMP);
        am.load(Assets.PLAYER_FALL);
        am.finishLoading();
    }

}
