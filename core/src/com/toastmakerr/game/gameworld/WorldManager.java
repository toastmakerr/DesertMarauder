package com.toastmakerr.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.toastmakerr.game.Assets;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.controllers.GameObject;
import com.toastmakerr.game.DesertMarauderMain;
import com.toastmakerr.game.controllers.Player;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.Random;

public class WorldManager extends World {
    private PlayerManager playerManager;
    private final static float GROUND_WIDTH = 500;
    private final static float GROUND_HEIGHT = 3f;
    private final static float LERP = 0.1f;
    private final static float SCROLLING_SPEED = 0.6f;
    private GameObject ground;
    private Texture BG1, BG1_2, BG2, BG2_2;
    private float bg1PosX, bg2PosX;
    private static final float STEP_TIME = 1f / 60f;
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;
    private float accumulator = 0;
    private AssetsManager assetManager;
    private final static int MAX_ENEMIES = 15;
    private static int scorpionCount = 8;
    public static com.badlogic.gdx.physics.box2d.World world;
    Box2DDebugRenderer debugRenderer;

    public WorldManager(AssetsManager assetManager){
        Box2D.init();
        debugRenderer = new Box2DDebugRenderer();
        world = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, -100), true);
        this.assetManager = assetManager;
        playerManager = new PlayerManager(assetManager);

        ground = new GameObject(new Vector2(0,0), new Vector2(GROUND_WIDTH, GROUND_HEIGHT), BodyDef.BodyType.KinematicBody, "Ground");

        bg1PosX = 0;
        bg2PosX = 0;

        this.addScorpion(new ScorpionManager(assetManager, new Vector2(22, 9.15f), false));
        this.addScorpion(new ScorpionManager(assetManager, new Vector2(37, 9.15f), false));
        this.addScorpion(new ScorpionManager(assetManager, new Vector2(57, 9.15f), false));
        this.addScorpion(new ScorpionManager(assetManager, new Vector2(63, 16.15f), false));
        this.addScorpion(new ScorpionManager(assetManager, new Vector2(73, 23.15f), false));
        this.addScorpion(new ScorpionManager(assetManager, new Vector2(83, 9.15f), false));
        this.addScorpion(new ScorpionManager(assetManager, new Vector2(88, 16.15f), false));
        this.addScorpion(new ScorpionManager(assetManager, new Vector2(93, 23.15f), false));

        this.addGroundObj(ground);
        this.addPlatformObj(new PlatformManager(assetManager, new Vector2(20,8)));
        this.addPlatformObj(new PlatformManager(assetManager, new Vector2(35,8)));
        this.addPlatformObj(new PlatformManager(assetManager, new Vector2(55,8)));
        this.addPlatformObj(new PlatformManager(assetManager, new Vector2(60,15)));
        this.addPlatformObj(new PlatformManager(assetManager, new Vector2(70,22)));
        this.addPlatformObj(new PlatformManager(assetManager, new Vector2(80,8)));
        this.addPlatformObj(new PlatformManager(assetManager, new Vector2(85,15)));
        this.addPlatformObj(new PlatformManager(assetManager, new Vector2(90,22)));


        BG1 = assetManager.am.get(Assets.DESERT_BG_1);
        BG1_2 = assetManager.am.get(Assets.DESERT_BG_1);
        BG2 = assetManager.am.get(Assets.DESERT_BG_2);
        BG2_2 = assetManager.am.get(Assets.DESERT_BG_2);
    }

    private void stepWorld() {
        float delta = Gdx.graphics.getDeltaTime();

        accumulator += Math.min(delta, 0.25f);

        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;

            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }
    }

    public void createCollisionListener(){
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Body a = contact.getFixtureA().getBody();
                Body b = contact.getFixtureB().getBody();
                a.getUserData();
                if(a.getUserData().equals("Player")){
                    Player.incrementContacts();
                    if(a.getUserData().equals("Player") && b.getUserData().equals("Scorpion")){
                        playerManager.playerTakeDmg();
                        playerManager.pushPlayer();
                        PlayerManager.setTookDmg(true);
                    }
                }
                for(int i = 0; i < getPlatformObj().size(); i++){
                    String temp = "Platform" + i;
                    if(a.getUserData().equals("Scorpion") && b.getUserData().equals(temp)){
                        getPlatformObj().get(i).setHasEnemy(true);
                    }
                }
            }

            @Override
            public void endContact(Contact contact) {
                Body a = contact.getFixtureA().getBody();
                Body b = contact.getFixtureB().getBody();
                a.getUserData();
                if (a.getUserData().equals("Player")) {
                    //playerManager.getPlayer().setGrounded(false);
                    Player.decrementContacts();
                }
                for(int i = 0; i < getPlatformObj().size(); i++){
                    String temp = "Platform" + i;
                    if(a.getUserData().equals("Scorpion") && b.getUserData().equals(temp)){
                        getPlatformObj().get(i).setHasEnemy(false);
                    }
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
            }
        });
    }

    public void draw(SpriteBatch batch, Camera camera){
        int numContacts = world.getContactCount();
        if (numContacts > 0) {
            //Gdx.app.log("contact", "start of contact list");
            for (Contact contact : world.getContactList()) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                //Gdx.app.log("contact", "between " + fixtureA.toString() + " and " + fixtureB.toString());
            }
            //Gdx.app.log("contact", "end of contact list");
        }
        batch.begin();
        if(bg1PosX + DesertMarauderMain.WIDTH / 40f < camera.position.x - DesertMarauderMain.WIDTH / 80f){
           bg1PosX += DesertMarauderMain.WIDTH / 40f;
        }
        if(bg2PosX + DesertMarauderMain.WIDTH / 40f < camera.position.x - DesertMarauderMain.WIDTH / 80f){
            bg2PosX += DesertMarauderMain.WIDTH / 40f;
        }
        batch.draw(BG1, bg1PosX,0, DesertMarauderMain.WIDTH / 40f,DesertMarauderMain.HEIGHT / 40f);
        batch.draw(BG1_2, bg1PosX + DesertMarauderMain.WIDTH / 40f,0, DesertMarauderMain.WIDTH / 40f,DesertMarauderMain.HEIGHT / 40f);
        batch.draw(BG2, bg2PosX,0, DesertMarauderMain.WIDTH / 40f,DesertMarauderMain.HEIGHT / 40f);
        batch.draw(BG2_2, bg2PosX + DesertMarauderMain.WIDTH / 40f,0, DesertMarauderMain.WIDTH / 40f,DesertMarauderMain.HEIGHT / 40f);
        playerManager.draw(batch, camera);
        for(ScorpionManager scorpionManager : getScorpionsObj())
            scorpionManager.draw(batch);
        for(PlatformManager platformManager : getPlatformObj())
            platformManager.draw(batch);
        batch.end();
    }

    public void update(Camera camera){
        stepWorld();
        spawnNewEnemy(camera);
        destroyDeadScorpion();
        playerManager.update(getScorpionsObj(), camera);
        if( Player.getMove() ) {
            for (ScorpionManager scorpionManager : getScorpionsObj())
                scorpionManager.update(playerManager.getPlayer(), camera);
            screenScroller(camera);
            platformHandler(camera);
        }
        debugRenderer.render(world, camera.combined);
    }

    public void dispose(Camera camera){

    }

    public void screenScroller(Camera camera){
        if( Player.getMove() ) {
            float scrollingDist = SCROLLING_SPEED * LERP;
            camera.position.x += scrollingDist;
            ground.setLinearVel(-scrollingDist, 0);
            bg1PosX -= scrollingDist * 0.25f;
            bg2PosX -= scrollingDist * 0.2f;
        }
    }

    public void platformHandler(Camera camera) {
        for (int i = 0; i < getPlatformObj().size(); i++) {
            if (i > 0) {
                getPlatformObj().get(i).updatePlatform(camera, getPlatformObj().get(i - 1));
            } else if (i == 0) {
                getPlatformObj().get(i).updatePlatform(camera, getPlatformObj().get(getPlatformObj().size() - 1));
            }
        }
    }

    public int nextPlatformToRespawnEnemy(Camera camera){
        for(int i = 0; i < getPlatformObj().size(); i++){
            if(!getPlatformObj().get(i).getHasEnemy() && getPlatformObj().get(i).getPlatform().getPosition().x > camera.position.x + DesertMarauderMain.WIDTH / 80){
                return i;
            }
        }
        return -1;
    }

    public void spawnNewEnemy(Camera camera){
        if(scorpionCount <= MAX_ENEMIES) {
            //System.out.println(scorpionCount);
            Random randX = new Random();
            int x = 2 * (randX.nextInt(10));
            for (int i = 0; i < getScorpionsObj().size(); i++) {
                int nextPlatform = nextPlatformToRespawnEnemy(camera);
                System.out.println(nextPlatform);
                if (nextPlatform != -1) {
                    Vector2 platformPos = getPlatformObj().get(nextPlatform).getPlatform().getPosition();
                    getPlatformObj().get(nextPlatform).setHasEnemy(true);
                    getScorpionsObj().add(new ScorpionManager(assetManager, new Vector2(platformPos.x + 2, platformPos.y + 1.15f), false));
                    scorpionCount++;
                } else if(nextPlatform > 0 && nextPlatform < getPlatformObj().size()){
                    getScorpionsObj().add(new ScorpionManager(assetManager, new Vector2(camera.position.x + DesertMarauderMain.WIDTH / 80 + x, 5), true));
                }
            }
        }
    }

    public void destroyDeadScorpion(){
        for(int i = 0; i < getScorpionsObj().size(); i++) {
            if (getScorpionsObj().get(i).getScorpion().getDead()) {
                world.destroyBody(getScorpionsObj().get(i).getScorpion().getBody());
                getScorpionsObj().remove(i);
                scorpionCount--;
            }
        }
    }


    public static int getScorpionCount(){
        return scorpionCount;
    }
}
