package com.teamsomething.ggj2016.game;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.Game;

public class CoreGame extends Game {
	static int WIDTH;
	static int HEIGHT;
    static CreditScreen creditScreen;
    static GameScreen gameScreen;
    static PauseScreen pauseScreen;
    static TitleScreen titleScreen;
	private OrthographicCamera cam;
    
    @Override
    public void create() {
    	ScreenManager.getInstance().initialize(this);
    	
    	WIDTH = Gdx.graphics.getWidth();
    	HEIGHT = Gdx.graphics.getHeight();
    	
    	cam = new OrthographicCamera(WIDTH, HEIGHT);
    	cam.translate(WIDTH/2, HEIGHT/2);
    	cam.update();
    	
    	creditScreen = new CreditScreen();
    	gameScreen= new GameScreen();
    	pauseScreen = new PauseScreen();
    	titleScreen = new TitleScreen();
    	gameScreen.show();
//    	setScreen(gameScreen);
    	
    }

    @Override
    public void dispose() {
    	creditScreen.dispose();
    	gameScreen.dispose();
    	pauseScreen.dispose();
    	titleScreen.dispose();
    	
    }

    @Override
    public void render() {
    	
        
//        Pool<MoveToAction> pool = new Pool<MoveToAction>(){
//			@Override
//			protected MoveToAction newObject() {
//				return new MoveToAction();
//			}
//        };
//    	if(Gdx.input.isKeyPressed(Input.Keys.UP)){
////    		MoveToAction action = pool.obtain();
////    		action.setPool(pool);
////    		action.setPosition(hallActor.getX(), hallActor.getY()+10);
////    		hallActor.addAction(action);
//    		
//    		sprite.translateY(10.0f);
//        }
//    	if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
//    		sprite.translateY(-10.0f);
//    		
//        }
//    	
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        
////        batch.begin();
////        
////        batch.end();
//    	
//        batch.begin();
////    	stage.act();
//        stage.draw();
////        sprite.draw(batch);
//        
////        hallActor.draw(batch, 1);
//        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
