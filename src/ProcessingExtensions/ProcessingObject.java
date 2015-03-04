package ProcessingExtensions;

import processing.core.PGraphics;

/**
 * Created by cansik on 26/02/15.
 */
public abstract class ProcessingObject {
    protected float x;
    protected float y;
    protected float z;

    public ProcessingObject(){}

    public abstract void setup(ProcessingAnimationContext context);

    public abstract void logic(ProcessingAnimationContext context);

    public abstract void draw(ProcessingAnimationContext g);

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
