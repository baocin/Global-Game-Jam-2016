package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.IntMap;
import com.teamsomething.ggj2016.game.CreditsScreen;
import com.teamsomething.ggj2016.game.GameScreen;
import com.teamsomething.ggj2016.game.PauseScreen;
import com.teamsomething.ggj2016.game.TitleScreen;

public final class ScreenManager {
	 
    private static ScreenManager instance;
    private Game game;
    
    public enum Screens {
    	 
        TITLE {
            @Override
            protected com.badlogic.gdx.Screen getScreenInstance() {
                return new TitleScreen();
            }
        },
     
        GAME {
            @Override
            protected com.badlogic.gdx.Screen getScreenInstance() {
                 return new GameScreen();
            }
        },
     
        PAUSE {
            @Override
            protected com.badlogic.gdx.Screen getScreenInstance() {
                 return new PauseScreen();
            }
        },
        
        CREDITS {
            @Override
            protected com.badlogic.gdx.Screen getScreenInstance() {
                 return new CreditsScreen();
            }
        };
     
        protected abstract com.badlogic.gdx.Screen getScreenInstance();
     
    }
    
    private IntMap<com.badlogic.gdx.Screen> screens;
    
    private ScreenManager() {
        screens = new IntMap<com.badlogic.gdx.Screen>();
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
 
    public void show(Screens screen) {
        if (null == game) return;
        if (!screens.containsKey(screen.ordinal())) {
            screens.put(screen.ordinal(), screen.getScreenInstance());
        }
        game.setScreen(screens.get(screen.ordinal()));
    }
 
    public void dispose(Screens screen) {
        if (!screens.containsKey(screen.ordinal())) return;
        screens.remove(screen.ordinal()).dispose();
    }
 
    public void dispose() {
        for (com.badlogic.gdx.Screen screen : screens.values()) {
            screen.dispose();
        }
        screens.clear();
        instance = null;
    } 
}