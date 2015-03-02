package Universe;

import ProcessingExtensions.ProcessingAnimationContext;
import ProcessingExtensions.ProcessingObject;
import processing.core.PGraphics;

/**
 * Created by cansik on 26/02/15.
 */
public class Star3d extends ProcessingObject {

    float speed;
    float size;
    float opacity;
    float z;

    int r = 250, green = 50, b = 100;
    int dr = 1, dg = 1, db = 1;

    public Star3d(){
    }

    @Override
    public void setup(ProcessingAnimationContext context) {
        speed = context.random(0, 3);
        size = context.random(1, 10);
        opacity = context.random(1);

        x = context.random(context.width);
        y = context.random(context.height);
        z = context.random(100, -1000);
    }

    @Override
    public void logic(ProcessingAnimationContext context)
    {
        r += dr;
        green += dg;
        b += db;

        if(r >= 255 || r <= 0)
            dr *= -1;

        if(green > 255 || green <= 0)
            dg *= -1;

        if(b > 255 || b <= 0)
            db *= -1;
    }

    @Override
    public void draw(ProcessingAnimationContext g) {
        g.pushMatrix();
        g.translate(x, y, z);
        g.noFill();
        g.stroke(r, green, b);
        //g.sphere(size);
        g.box(size);
        g.popMatrix();
    }
}
