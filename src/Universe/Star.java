package Universe;

import ProcessingExtensions.ProcessingAnimationContext;
import ProcessingExtensions.ProcessingObject;
import processing.core.PGraphics;

/**
 * Created by cansik on 26/02/15.
 */
public class Star extends ProcessingObject {

    public Star(){
    }

    @Override
    public void setup(ProcessingAnimationContext context) {
        x = 10;
        x = 10;
    }

    @Override
    public void logic(ProcessingAnimationContext context)
    {
        x += 10;

        if(x > context.width) {
            x = 0;
            y += 10;
        }
    }

    @Override
    public void draw(PGraphics g) {
        g.stroke(255);
        g.ellipse(x, y, 10, 10);
    }
}
