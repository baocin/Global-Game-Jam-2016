package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.CoreGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Dungeon Crawler 2.0";
//		config.useGL30 = true;
		config.width = 1024;
		config.height = 800;
		
		new LwjglApplication(new CoreGame(), config);
	}
	
	
}
