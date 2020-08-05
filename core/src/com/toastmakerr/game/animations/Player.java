package com.toastmakerr.game.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.toastmakerr.game.Assets;

public class Player {
    private Texture texture;
    private TextureRegion[] spriteFrames;
    private TextureRegion currentFrame;
    private Animation animation;
    private int rows;
    private int cols;
    private float stateTime;

    public Player(Direction dir, AssetManager manager, Texture spriteSheet, int row, int col){
        rows = row;
        cols = col;
        texture = spriteSheet;
        TextureRegion[][] temp = new TextureRegion(texture).split(texture.getWidth(), texture.getHeight());
        int index = 0;
        spriteFrames = new TextureRegion[rows * cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                spriteFrames[index++] = temp[i][j];
            }
        }
        animation = new Animation(0.06f, spriteFrames);
        stateTime = 0f;
    }

    public void draw(SpriteBatch batch){
        stateTime += Gdx.graphics.getDeltaTime();
        if(stateTime > animation.getAnimationDuration()){
            stateTime -= animation.getAnimationDuration();
        }
        currentFrame = (TextureRegion) animation.getKeyFrame(stateTime);
        batch.draw(currentFrame,50,50);
    }


}
