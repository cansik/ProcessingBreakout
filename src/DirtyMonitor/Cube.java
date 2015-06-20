package DirtyMonitor;

import ProcessingExtensions.ProcessingAnimationContext;
import ProcessingExtensions.ProcessingObject;
import processing.core.PVector;

/**
 * Created by cansik on 30/03/15.
 */
public class Cube extends ProcessingObject {
    private PVector location;
    private PVector speed;
    private PVector target;

    float easing = 0.05f;

    @Override
    public void setup(ProcessingAnimationContext context) {
        location = new PVector(500, 500, 20);
        speed = new PVector(0, 0, 0);
        target = new PVector();

        spread(context);
    }

    @Override
    public void logic(ProcessingAnimationContext context) {
        /*
        location = new PVector((target.x-location.x)*easing,
                (target.y-location.y)*easing,
                (target.z-location.z)*easing);
        */

        location.x+=(target.x-location.x)*easing;
        location.y+=(target.y-location.y)*easing;
        location.z+=(target.z-location.z)*easing;
    }

    public void MoveToPosition(PVector pos)
    {
        target = pos;
    }

    public void spread(ProcessingAnimationContext context)
    {
        target = new PVector(context.random(0, 1280),
                context.random(0, 720),
                context.random(0, 20));
    }

    @Override
    public void draw(ProcessingAnimationContext g) {
        g.pushMatrix();
        g.translate(location.x, location.y, location.z);
        g.fill(51, 153, 255);
        g.stroke(51, 153, 255);
        g.sphereDetail(20, 20);
        g.box(50);
        g.popMatrix();
    }
}
