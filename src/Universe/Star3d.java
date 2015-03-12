package Universe;

import ProcessingExtensions.ProcessingAnimationContext;
import ProcessingExtensions.ProcessingObject;

/**
 * Created by cansik on 26/02/15.
 */
public class Star3d extends ProcessingObject {

    float speed;
    float size;
    float opacity;
    float fieldSize;

    float initx, inity, initz, initr;

    float theta;
    float roh;
    float r;

    float red, green, b;

    public boolean fall;


    public Star3d(float fieldSize){
        this.fieldSize = fieldSize;
    }

    @Override
    public void setup(ProcessingAnimationContext context) {
        speed = context.random(0, 3);
        size = context.random(1, 10);
        opacity = context.random(1);

        /*
        x = context.random(fieldSize);
        y = context.random(fieldSize);
        z = context.random(fieldSize);
        */

        r = fieldSize; //context.random(fieldSize);
        x = context.random(-1 * r, r);
        y = context.random(-1 * r, r);
        z = (float)Math.sqrt(Math.pow(r, 2D) -  (Math.pow(x, 2D) + Math.pow(y, 2D)));

        if((int)x%2 == 0)
        {
            z *= -1;
            green = 255;
            red = 100;
        }
        else
        {
            red = 255;
            b = 100;
        }


        initx = x;
        inity = y;
        initz = z;
        initr = r;

        theta = (float)Math.asin(z / r);
        roh = (float)Math.atan2(y, x);
    }

    @Override
    public void logic(ProcessingAnimationContext context)
    {

    }

    @Override
    public void draw(ProcessingAnimationContext g) {
        if(fall)
        {
            if(Math.abs(r)  <= initr) {
                r -= g.random(0, 20);

                x = (float) (r * Math.cos(theta) * Math.cos(roh));
                y = (float) (r * Math.cos(theta) * Math.sin(roh));
                z = (float) (r * Math.sin(theta));
            }
        }

        g.pushMatrix();
        g.translate(x, y, z);


        g.noFill();
        //g.stroke(red, green, b);
        //g.fill(red, green, b);

        if(g.random(1000) > 500) {
            g.stroke(0, 255, 0);
            g.fill(0, 255, 0);
        }
        else
        {
            g.stroke(255);
            g.fill(255);
        }

        g.fill(0);
        g.stroke(0);

        //g.sphere(size);
        g.box(size);

        g.popMatrix();
    }
}
