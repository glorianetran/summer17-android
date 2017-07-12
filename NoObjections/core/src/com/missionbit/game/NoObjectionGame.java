package com.missionbit.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.missionbit.game.sprites.Hero;
import com.missionbit.game.states.EndState;
import com.missionbit.game.states.GameStateManager;
import com.missionbit.game.states.MenuState;
import com.missionbit.game.states.PlayState;

public class NoObjectionGame extends ApplicationAdapter {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 480;
    public static final float PPM = 100;
    public static final String TITLE = "Flummox";
    public GameStateManager gsm;
    public static SpriteBatch batch;
    World world;
    public Viewport viewport;
    public Controller controller;
    public Body hero;


    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        gsm.push(new MenuState(gsm));
        Gdx.gl.glClearColor(0, 0, 0, 1);
        controller = new Controller();
        viewport = new FitViewport(WIDTH, HEIGHT);
        world = new World(new Vector2(0, -10), true);

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
        controller.resize(width, height);
    }

    public void handleInput() {
        if (controller.isRightPressed())
            hero.setLinearVelocity(new Vector2(1, hero.getLinearVelocity().y));
        else if (controller.isLeftPressed())
            hero.setLinearVelocity(new Vector2(-1, hero.getLinearVelocity().y));
        else
            hero.setLinearVelocity(new Vector2(0, hero.getLinearVelocity().y));
        if (controller.isUpPressed() && hero.getLinearVelocity().y == 0)
            hero.applyLinearImpulse(new Vector2(0, 5f), hero.getWorldCenter(), true);

    }


    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
        controller.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();

    }


}
