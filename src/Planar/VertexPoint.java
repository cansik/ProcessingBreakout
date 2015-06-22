package Planar;

import processing.core.PVector;

/**
 * Created by cansik on 22/06/15.
 */
public class VertexPoint {
    PVector location;
    PVector velocity;

    public VertexPoint(PVector location)
    {
        this.location = location;
        this.velocity = new PVector();
    }

    public void nextStep()
    {

    }
}
