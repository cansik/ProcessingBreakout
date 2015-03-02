package Universe;

import ProcessingExtensions.ProcessingAnimationContext;
import peasy.PeasyCam;
import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * Created by cansik on 26/02/15.
 */
public class UniverseAnimation extends ProcessingAnimationContext {

    PeasyCam cam;
    float cameraX = 0;

    @Override
    public void setup()
    {
        super.setup();
        cam = new PeasyCam(this, 0, 0, 0, 1000);
        cam.lookAt(width/2,height/2, 500);

        /*
        for(int i = 0; i < 200; i ++)
        {
            addAnimationObject(new Star3d());
        }
        */
    }

    @Override
    public void draw()
    {
        super.draw();

        if(random(1) > 0.9)
            addAnimationObject(new Star3d());

        cam.rotateY(-0.001);
        cameraX += 0.5;
    }
}
