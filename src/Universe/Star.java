package Universe;

import ProcessingExtensions.ProcessingAnimationContext;
import ProcessingExtensions.ProcessingObject;
import processing.core.PGraphics;

/**
 * Created by cansik on 26/02/15.
 */
public class Star extends ProcessingObject {

    float speed;
    float size;
    float opacity;

    public Star(){
    }

    @Override
    public void setup(ProcessingAnimationContext context) {
        speed = context.random(0, 3);
        size = context.random(1, 10);
        opacity = context.random(1);
        y = context.random(context.height);
    }

    @Override
    public void logic(ProcessingAnimationContext context)
    {
        x += speed;

        if(x > context.width)
            context.removeAnimationObject(this);
    }

    @Override
    public void draw(ProcessingAnimationContext g) {
       g.stroke(255, opacity);
       g.ellipse(x, y, size, size);
    }
}
