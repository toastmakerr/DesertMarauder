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
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.toastmakerr.game.Assets;
import com.toastmakerr.game.AssetsManager;
import com.toastmakerr.game.controllers.GameObject;
import com.toastmakerr.game.DesertMarauderMain;
import com.toastmakerr.game.controllers.Player;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Body;


import java.util.ArrayList;
import java.util.Random;

public class WorldManager extends World {
    private PlayerManager playerManager;
    private final static float GROUND_WIDTH = DesertMarauderMain.WIDTH / 40;
    private final static float GROUND_HEIGHT = 1.5f;
    private final static float PLATFORM_WIDTH = 5f;
    private final static float PLATFORM_HEIGHT = 0.45f;
    private final static float LERP = 0.1f;
    private final static float SCROLLING_SPEED = 0.6f;
    private GameObject ground;
    private GameObject platform, platform2, platform3, platform4, platform5, platform6, platform7, platform8;
    private Texture BG1, BG1_2, BG2, BG2_2, PLATFORM;
    private float bg1PosX, bg2PosX;
    private static final float STEP_TIME = 1f / 60f;
    private static final int VELOCITY_ITERATIONS = 6;
    private static final int POSITION_ITERATIONS = 2;
    private float accumulator = 0;
    private static ArrayList<String> groundContactObjs;

    public static com.badlogic.gdx.physics.box2d.World world;
    Box2DDebugRenderer debugRenderer;

    public WorldManager(AssetsManager assetManager){
        Box2D.init();
        debugRenderer = new Box2DDebugRenderer();
        world = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, -100), true);

        playerManager = new PlayerManager(assetManager);
        addScorpion(new ScorpionManager(assetManager));

        ground = new GameObject(new Vector2(0,0), new Vector2(GROUND_WIDTH, GROUND_HEIGHT), BodyDef.BodyType.KinematicBody, "Ground");
        platform = new GameObject(new Vector2(20f,8f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT), BodyDef.BodyType.KinematicBody, "Platform1");
        platform2 = new GameObject(new Vector2(35f,8f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT), BodyDef.BodyType.KinematicBody, "Platform2");
        platform3 = new GameObject(new Vector2(55f,8f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT), BodyDef.BodyType.KinematicBody, "Platform3");
        platform4 = new GameObject(new Vector2(60f,15f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT), BodyDef.BodyType.KinematicBody, "Platform4");
        platform5 = new GameObject(new Vector2(70f,22f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT), BodyDef.BodyType.KinematicBody, "Platform5");
        platform6 = new GameObject(new Vector2(80f,8f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT), BodyDef.BodyType.KinematicBody, "Platform6");
        platform7 = new GameObject(new Vector2(85f,15f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT), BodyDef.BodyType.KinematicBody, "Platform7");
        platform8 = new GameObject(new Vector2(90f,22f), new Vector2(PLATFORM_WIDTH, PLATFORM_HEIGHT), BodyDef.BodyType.KinematicBody, "Platform8");

        groundContactObjs = new ArrayList<>();
        groundContactObjs.add("Ground");
        groundContactObjs.add("Platform1");
        groundContactObjs.add("Platform2");
        groundContactObjs.add("Platform3");
        groundContactObjs.add("Platform4");
        groundContactObjs.add("Platform5");
        groundContactObjs.add("Platform6");
        groundContactObjs.add("Platform7");
        groundContactObjs.add("Platform8");

        bg1PosX = 0;
        bg2PosX = 0;

        this.addGroundObj(ground);
        this.addPlatformObj(platform);
        this.addPlatformObj(platform2);
        this.addPlatformObj(platform3);
        this.addPlatformObj(platform4);
        this.addPlatformObj(platform5);
        this.addPlatformObj(platform6);
        this.addPlatformObj(platform7);
        this.addPlatformObj(platform8);

        BG1 = assetManager.am.get(Assets.DESERT_BG_1);
        BG1_2 = assetManager.am.get(Assets.DESERT_BG_1);
        BG2 = assetManager.am.get(Assets.DESERT_BG_2);
        BG2_2 = assetManager.am.get(Assets.DESERT_BG_2);
        PLATFORM = assetManager.am.get(Assets.DESERT_PLATFORM);

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, 0f));
        Body groundBody = world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(500, 3f);

        FixtureDef fixture = new FixtureDef();
        fixture.shape = groundBox;
        groundBody.createFixture(fixture);
        createCollisionListener();
        groundBox.dispose();
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
                    //playerManager.getPlayer().setGrounded(true);
                    Player.incrementContacts();
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
            scorpionManager.draw(batch, camera);
        batch.draw(PLATFORM, platform.getPosition().x - PLATFORM_WIDTH, platform.getPosition().y - PLATFORM_HEIGHT, DesertMarauderMain.WIDTH / 40f,DesertMarauderMain.HEIGHT / 40f);
        batch.draw(PLATFORM, platform2.getPosition().x - PLATFORM_WIDTH, platform2.getPosition().y - PLATFORM_HEIGHT, DesertMarauderMain.WIDTH / 40f,DesertMarauderMain.HEIGHT / 40f);
        batch.draw(PLATFORM, platform3.getPosition().x - PLATFORM_WIDTH, platform3.getPosition().y - PLATFORM_HEIGHT, DesertMarauderMain.WIDTH / 40f,DesertMarauderMain.HEIGHT / 40f);
        batch.draw(PLATFORM, platform4.getPosition().x - PLATFORM_WIDTH, platform4.getPosition().y - PLATFORM_HEIGHT, DesertMarauderMain.WIDTH / 40f,DesertMarauderMain.HEIGHT / 40f);
        batch.draw(PLATFORM, platform5.getPosition().x - PLATFORM_WIDTH, platform5.getPosition().y - PLATFORM_HEIGHT, DesertMarauderMain.WIDTH / 40f,DesertMarauderMain.HEIGHT / 40f);
        batch.draw(PLATFORM, platform6.getPosition().x - PLATFORM_WIDTH, platform6.getPosition().y - PLATFORM_HEIGHT, DesertMarauderMain.WIDTH / 40f,DesertMarauderMain.HEIGHT / 40f);
        batch.draw(PLATFORM, platform7.getPosition().x - PLATFORM_WIDTH, platform7.getPosition().y - PLATFORM_HEIGHT, DesertMarauderMain.WIDTH / 40f,DesertMarauderMain.HEIGHT / 40f);
        batch.draw(PLATFORM, platform8.getPosition().x - PLATFORM_WIDTH, platform8.getPosition().y - PLATFORM_HEIGHT, DesertMarauderMain.WIDTH / 40f,DesertMarauderMain.HEIGHT / 40f);
        batch.end();
    }

    public void update(Camera camera){
        stepWorld();
        playerManager.update(getGroundObj(), getPlatformObj(), getWallObj(), getScorpionsObj());
        if( Player.getMove() ) {
            for (ScorpionManager scorpionManager : getScorpionsObj())
                scorpionManager.update(playerManager.getPlayer(), getGroundObj(), getPlatformObj(), getWallObj());
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

    public void platformHandler(Camera camera){
        Random randX = new Random();
        randX.nextFloat();
        Random randY = new Random();
        float x = 5 * (randX.nextInt(2) + 1);
        float y = 7 * (randY.nextInt(3) + 1) + 1;
        for(int i = 0; i < getPlatformObj().size(); i++) {
            System.out.println(getPlatformObj().get(0).getPosition());
            System.out.println(camera.position.x - DesertMarauderMain.WIDTH / 80);
            if(getPlatformObj().get(i).getPosition().x + PLATFORM_WIDTH < camera.position.x - DesertMarauderMain.WIDTH / 80){
                if(i > 0){
                    if(getPlatformObj().get(i-1).getPosition().y == 8){
                        getRandomYWithExclusion(22);
                    }
                    else{
                        getRandomYWithExclusion(-1);
                    }
                    if(getPlatformObj().get(i-1).getPosition().y < y){
                        getRandomXWithExclusion(0);
                    }
                    else if(getPlatformObj().get(i-1).getPosition().y == y){
                        x = 10;
                    }
                    else{
                        getRandomXWithExclusion(-1);
                    }
                }
                else if(i == 0){
                    if(getPlatformObj().get(getPlatformObj().size() - 1).getPosition().y == 8){
                        getRandomYWithExclusion(22);
                    }
                    else{
                        getRandomYWithExclusion(-1);
                    }
                    if(getPlatformObj().get(getPlatformObj().size() - 1).getPosition().y < getPlatformObj().get(i).getPosition().y){
                        getRandomXWithExclusion(0);
                    }
                    else{
                        getRandomXWithExclusion(-1);
                    }
                }
                getPlatformObj().get(i).setPosition(x + camera.position.x + DesertMarauderMain.WIDTH / 80 + 10, y);
            }
        }
    }

    public int getRandomYWithExclusion(int exclusion){
        Random randY = new Random();
        int y = 7 * (randY.nextInt(3) + 1) + 1;
        while(y == exclusion){
            y = 7 * (randY.nextInt(3) + 1) + 1;
        }
        return y;
    }

    public int getRandomXWithExclusion(int exclusion){
        Random randX = new Random();
        int x = 5 * (randX.nextInt(3));
        while(x == exclusion){
            x = 5 * (randX.nextInt(3));        }
        return x;
    }
}
