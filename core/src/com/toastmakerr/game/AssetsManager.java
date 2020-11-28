package com.toastmakerr.game;

import com.badlogic.gdx.assets.AssetManager;

public class AssetsManager {
    public AssetManager am;

    public AssetsManager(){
        am = new AssetManager();
        am.load(Assets.DESERT_BG);
        am.load(Assets.DESERT_BG_1);
        am.load(Assets.DESERT_BG_2);
        am.load(Assets.TITLE);
        am.load(Assets.END_TITLE);
        am.load(Assets.PLAYER_IDLE);
        am.load(Assets.PLAYER_RUN);
        am.load(Assets.PLAYER_WALK);
        am.load(Assets.PLAYER_JUMP);
        am.load(Assets.PLAYER_FALL);
        am.load(Assets.PLAYER_ATTACK_1);
        am.load(Assets.PLAYER_ATTACK_2);
        am.load(Assets.PLAYER_ATTACK_3);
        am.load(Assets.DESERT_PLATFORM);
        am.load(Assets.HEART);
        am.load(Assets.SCORPION_HURT);
        am.load(Assets.SCORPION_ATTACK);
        am.load(Assets.SCORPION_DEATH);
        am.load(Assets.SCORPION_WALK);
        am.finishLoading();
    }

}
