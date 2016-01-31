package com.mygdx.game;

import org.opencv.core.Core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.IntMap;
import com.teamsomething.ggj2016.game.CreditsScreen;
import com.teamsomething.ggj2016.game.GameScreen;
import com.teamsomething.ggj2016.game.PauseScreen;
import com.teamsomething.ggj2016.game.TitleScreen;

import it.polito.teaching.cv.FaceDetection;
import it.polito.teaching.cv.FaceDetectionController;
import javafx.stage.Stage;

public final class CamManager {
    private static CamManager instance;
    public static FaceDetection fd;
    private static FaceDetectionController fdc;
    
    public CamManager() {
    	fd = new FaceDetection();
    	fdc = new FaceDetectionController();
    }
    
 
    public static CamManager getInstance() {
        if (null == instance) {
            instance = new CamManager();
        }
        return instance;
    }
 
    public void start(){
    	fd.start(new Stage());

    }
//    public void initialize(Game game) {
//        this.game = game;
//    }
 
    public void dispose() {
        instance = null;
    } 
}