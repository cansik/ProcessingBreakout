package MusikSehen;

import ProcessingExtensions.ProcessingAnimationContext;
import ProcessingExtensions.ProcessingObject;
import processing.core.PConstants;
import processing.core.PVector;

/**
 * Created by cansik on 24/03/15.
 */
public class MovingCircles extends ProcessingObject {

    float centerX;
    float centerY;

    PVector location = new PVector(0, 0);
    PVector size;
    PVector speed = new PVector(20f, 20f);

    float sizeSpeed = 1.05f;

    int color;

    public MovingCircles(int color)
    {
        super();
        this.color = color;
    }

    @Override
    public void setup(ProcessingAnimationContext context) {
        centerY = context.getScreenHeight() / 2;
        centerX = context.getScreenWidth() / 2;

        size = new PVector(1, 1);
        location = new PVector(centerX, centerY);
    }

    @Override
    public void logic(ProcessingAnimationContext context) {
        //size.add(speed);
        size.mult(sizeSpeed);

        if((size.x / 2) > centerX) {
            context.removeAnimationObject(this);
        }
    }

    @Override
    public void draw(ProcessingAnimationContext g) {
        g.strokeWeight(25);
        g.stroke(color);
        //g.fill(0);
        g.noFill();

        g.ellipseMode(PConstants.CENTER);
        g.ellipse(location.x, location.y, size.x, size.y);
    }
}
