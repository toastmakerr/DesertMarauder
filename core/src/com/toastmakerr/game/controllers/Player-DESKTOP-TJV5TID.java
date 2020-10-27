package com.toastmakerr.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.animations.PlayerAction;
import com.toastmakerr.game.animations.PlayerAnimation;

public class Player {
    private PlayerAnimation playerAnimation;
    private DynamicGameObject player;
    private FixtureDef fixtureDef;
    private PolygonShape hitBox;
    private Fixture fixture;
    private boolean midAir = false;

    public Player(AssetsManager assetManager, World world){
        playerAnimation = new PlayerAnimation(assetManager);
        player = new DynamicGameObject(world, playerAnimation.getPos());
        hitBox = new PolygonShape();
        fixtureDef = new FixtureDef();
        fixtureDef.shape = hitBox;
        fixtureDef.density = 0.985f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0f;
        hitBox.setAsBox(0.75f,1f);
        fixture = player.getBody().createFixture(fixtureDef);
        hitBox.dispose();
    }

    public void update(){
        playerAnimation.setPos(player.getPosition());
        System.out.println(player.getPosition());
    }

    public void inputHandler(){ //Need to add joypad option for android
        if(!Gdx.input.isKeyPressed(Input.Keys.W) || !Gdx.input.isKeyPressed(Input.Keys.A) || !Gdx.input.isKeyPressed(Input.Keys.S) || !Gdx.input.isKeyPressed(Input.Keys.D) && !midAir){
            playerAnimation.setFrameDuration(0.25f);
            playerAnimation.setAction(PlayerAction.IDLE);
            player.moveDynamicObj(new Vector2(0,0));
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            playerAnimation.setFrameDuration(0.08f);
            playerAnimation.setAction(PlayerAction.JUMP);
            player.moveDynamicObj(new Vector2(0,2f));
            if(Gdx.input.isKeyPressed(Input.Keys.D)){
                playerAnimation.flipSprite(false);
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.A)){
                playerAnimation.flipSprite(true);
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.W)){
            playerAnimation.setFrameDuration(0.07f);
            playerAnimation.setAction(PlayerAction.JUMP);
            player.moveDynamicObj(new Vector2(0,2f));
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)){
            player.moveDynamicObj(new Vector2(0,0f));
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            playerAnimation.setFrameDuration(0.05f);
            playerAnimation.flipSprite(true);
            playerAnimation.setAction(PlayerAction.RUN);
            player.moveDynamicObj(new Vector2(-3f,0));
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            playerAnimation.setFrameDuration(0.07f);
            playerAnimation.flipSprite(true);
            playerAnimation.setAction(PlayerAction.WALK);
            player.moveDynamicObj(new Vector2(-2f,0));
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
            playerAnimation.setFrameDuration(0.05f);
            playerAnimation.flipSprite(false);
            playerAnimation.setAction(PlayerAction.RUN);
            player.moveDynamicObj(new Vector2(3f,0));
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            playerAnimation.setFrameDuration(0.07f);
            playerAnimation.flipSprite(false);
            playerAnimation.setAction(PlayerAction.WALK);
            player.moveDynamicObj(new Vector2(2f,0));
        }
    }

}
