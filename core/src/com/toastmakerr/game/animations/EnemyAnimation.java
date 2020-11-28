package com.toastmakerr.game.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.toastmakerr.game.Assets;
import com.toastmakerr.game.AssetsManager;

public class EnemyAnimation {
    private Texture texture;
    private TextureRegion[] spriteFrames;
    private TextureRegion currentFrame;
    private Animation<TextureRegion> animation;
    private static EnemyAction action;
    private AssetsManager assetManager;
    private EnemyAction previousAction;
    private int rows;
    private int cols;
    private static float stateTime;
    private static boolean flip;
    private static float frameDuration;
    private static Vector2 animationPos;
    private static Vector2 animationDimensions;

    public EnemyAnimation(AssetsManager assetsManager){
        this.assetManager = assetsManager;
        flip = false;
        previousAction = null;
        action = EnemyAction.WALK;
        frameDuration = 0.25f;
        setPos(new Vector2(40, 17f));
        setDimensions(new Vector2(0,0));
        setAnimation();
    }

    public void draw(SpriteBatch batch){
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animation.getKeyFrame(stateTime,true);
        batch.draw(currentFrame, animationPos.x - 2.2f, animationPos.y, 1.5f,2, 4, 4, (flip ? -1 : 1) ,1,0);
    }

    public boolean isAnimFinished(float mult){
        return animation.isAnimationFinished(stateTime * mult);
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

    public void setAction(EnemyAction act){
        action = act;
    }

    public void setAnimation(){
        switch(action){
            case IDLE:
                if(previousAction != EnemyAction.HURT) {
                    newAnimation(assetManager.am.get(Assets.SCORPION_HURT), 1, 2);
                    previousAction = EnemyAction.HURT;
                }
                break;
            case WALK:
                if(previousAction != EnemyAction.WALK) {
                    newAnimation(assetManager.am.get(Assets.SCORPION_WALK), 1, 4);
                    previousAction = EnemyAction.WALK;
                }
                break;
            case DEAD:
                if(previousAction != EnemyAction.DEAD) {
                    newAnimation(assetManager.am.get(Assets.SCORPION_DEATH), 1, 4);
                    previousAction = EnemyAction.DEAD;
                }
                break;
            case ATTACK:
                if(previousAction != EnemyAction.ATTACK) {
                    newAnimation(assetManager.am.get(Assets.SCORPION_ATTACK), 1, 4);
                    previousAction = EnemyAction.ATTACK;
                }
                break;
        }
    }

    public boolean isAttackAction(){
        if(action == EnemyAction.ATTACK)
            return true;
        return false;
    }

    public EnemyAction getAction(){
        return action;
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

    public void dispose(){
        texture.dispose();
    }

}
