package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Hallway extends Actor {
	TextureRegion region;
	private ShapeRenderer renderer = new ShapeRenderer();
	private Texture texture;
    Sprite sprite;
    
	public Hallway() {
        region = new TextureRegion();
        System.out.println("Hello from Hallway");
        
        texture = new Texture(Gdx.files.internal("perspective.png"));
        sprite = new Sprite(texture);
        
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
    	batch.end();
    	
    	batch.begin();
//    	sprite.draw(batch);
    	
    	
        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        renderer.setTransformMatrix(batch.getTransformMatrix());
        renderer.translate(getX(), getY(), 0);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.BLUE);
        renderer.rect(0, 0, getWidth(), getHeight());
        renderer.end();

        batch.end();
        batch.begin();
    	 
    	//Draw the hallway frame
//    	renderer.begin(ShapeRenderer.ShapeType.Line);
//    	renderer.rect(getWidth()/2, getHeight()/2, 0, 0, getWidth()/3, getHeight()/3, 1, 1, 1);
//    	renderer.end();
//    	batch.draw(region, 0, 0, 0, 0, getWidth(), getHeight(), 1, 1, 0);
//        Color color = getColor();
//        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
//        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
//            getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
    
}
