package com.teamsomething.ggj2016.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.CamManager;
import com.mygdx.game.ScreenManager;

public class CoreGame extends Game {

	@Override
	public void create() {
		Gdx.app.setLogLevel(Logger.DEBUG);
//		CamManager ct = new CamManager();
//		ct.start();
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().show(ScreenManager.Screens.TITLE);
	}

	@Override
	public void dispose() {
		ScreenManager.getInstance().dispose();

	}

	@Override
	public void render() {
		super.render();
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
