package com.toastmakerr.game.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.toastmakerr.game.Assets;
import com.toastmakerr.game.AssetsManager;

import java.util.Random;

public class PlayerAnimation {
    private Texture texture;
    private TextureRegion[] spriteFrames;
    private TextureRegion currentFrame;
    private Animation<TextureRegion> animation;
    private static PlayerAction action;
    private AssetsManager assetManager;
    private PlayerAction previousAction;
    private int rows;
    private int cols;
    private static float stateTime;
    private static boolean flip;
    private static float frameDuration;
    private static Vector2 animationPos;
    private static Vector2 animationDimensions;
    public PlayerAnimation(AssetsManager assetsManager){
        this.assetManager = assetsManager;
        flip = false;
        previousAction = null;
        action = PlayerAction.IDLE;
        frameDuration = 0.25f;
        setPos(new Vector2(2,3));
        setDimensions(new Vector2(0,0));
        setAnimation();
    }

    public void draw(SpriteBatch batch){
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animation.getKeyFrame(stateTime,true);
        batch.draw(currentFrame, animationPos.x - 0.78f, animationPos.y, animationDimensions.x * 1.9f / 2,animationDimensions.y * 1.455f / 2, animationDimensions.x * 3f, animationDimensions.y * 1.455f, (flip ? -1 : 1) ,1,0);
    }

    public void flipSprite(boolean bool){
        flip = bool;
    }

    public boolean isAnimFinished(PlayerAction act, float mult){
        if(action == action)
            return animation.isAnimationFinished(stateTime * mult);
        return false;
    }

    public void newAnimation(Texture spriteSheet, int row, int col){
        rows = row;
        cols = col;
        texture = spriteSheet;
        TextureRegion[][] temp = new TextureRegion(texture).split(texture.getWidth() / cols, texture.getHeight() / rows);
        spriteFrames = new TextureRegion[rows * cols];
        int index = 0;
        for(int i = 0; i < rows; i++){;
            for(int j = 0; j < cols; j++){
                spriteFrames[index++] = temp[i][j];
            }
        }
        animation = new Animation<>(frameDuration, spriteFrames);
        stateTime = 0f;
    }

    public void setAction(PlayerAction act){
        action = act;
    }

    public void setRandomAttackAction(){
        Random random = new Random();
        int rand = random.nextInt(2);
        PlayerAction act = PlayerAction.ATTACK_1;
        switch(rand){
            case 0:
                act = PlayerAction.ATTACK_1;
                break;
            case 1:
                act = PlayerAction.ATTACK_2;
                break;
        }
        action = act;
    }

    public boolean isAttackAction(){
        if(action == PlayerAction.ATTACK_1 || action == PlayerAction.ATTACK_2)
            return true;
        return false;
    }

    public void setAnimation(){
        switch(action){
            case IDLE:
                if(previousAction != PlayerAction.IDLE) {
                    newAnimation(assetManager.am.get(Assets.PLAYER_IDLE), 1, 4);
                    previousAction = PlayerAction.IDLE;
                }
                break;
            case WALK:
                if(previousAction != PlayerAction.WALK) {
                    newAnimation(assetManager.am.get(Assets.PLAYER_WALK), 1, 6);
                    previousAction = PlayerAction.WALK;
                }
                break;
            case RUN:
                if(previousAction != PlayerAction.RUN) {
                    newAnimation(assetManager.am.get(Assets.PLAYER_RUN), 1, 6);
                    previousAction = PlayerAction.RUN;
                }
                break;
            case JUMP:
                if(previousAction != PlayerAction.JUMP) {
                    newAnimation(assetManager.am.get(Assets.PLAYER_JUMP), 1, 6);
                    previousAction = PlayerAction.JUMP;
                }
                break;
            case FALL:
                if(previousAction != PlayerAction.FALL) {
                    newAnimation(assetManager.am.get(Assets.PLAYER_FALL), 1, 3);
                    previousAction = PlayerAction.FALL;
                }
                break;
            case ATTACK_1:
                if(previousAction != PlayerAction.ATTACK_1) {
                    newAnimation(assetManager.am.get(Assets.PLAYER_ATTACK_1), 1, 6);
                    previousAction = PlayerAction.ATTACK_1;
                }
                break;
            case ATTACK_2:
                if(previousAction != PlayerAction.ATTACK_2) {
                    newAnimation(assetManager.am.get(Assets.PLAYER_ATTACK_2), 1, 6);
                    previousAction = PlayerAction.ATTACK_2;
                }
                break;
        }
    }

    public void setFrameDuration(float duration){
        frameDuration = duration;
    }

    public void setPos(Vector2 pos){
        animationPos = pos;
    }

    public void setDimensions(Vector2 dim){
        animationDimensions = dim;
    }

}
