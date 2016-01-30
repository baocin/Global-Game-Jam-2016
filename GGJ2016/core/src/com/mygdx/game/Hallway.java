package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Hallway extends Actor {

	public Hallway() {
        region = new TextureRegion(...);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
            getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
    
}
