package ProcessingExtensions;

import processing.core.PGraphics;

/**
 * Created by cansik on 26/02/15.
 */
public abstract class ProcessingObject {
    protected float x;
    protected float y;

    public ProcessingObject(){}

    public abstract void setup(ProcessingAnimationContext context);

    public abstract void logic(ProcessingAnimationContext context);

    public abstract void draw(ProcessingAnimationContext g);
}
