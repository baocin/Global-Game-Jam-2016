package com.mygdx.game;

import com.badlogic.gdx.Game;

public final class ScreenManager {
	 
    private static ScreenManager instance;
    private Game game;
    
    private ScreenManager() {
    }
 
    public static ScreenManager getInstance() {
        if (null == instance) {
            instance = new ScreenManager();
        }
        return instance;
    }
    
    public void initialize(Game game) {
        this.game = game;
    } 
}