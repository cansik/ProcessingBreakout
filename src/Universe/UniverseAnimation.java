package Universe;

import ProcessingExtensions.ProcessingAnimationContext;
import peasy.PeasyCam;
import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * Created by cansik on 26/02/15.
 */
public class UniverseAnimation extends ProcessingAnimationContext {

    //PeasyCam cam;
    float cameraX = 0;

    @Override
    public void setup()
    {
        super.setup();
        //cam = new PeasyCam(this, 0, 0, 0, 50);

        for(int i = 0; i < 200; i ++)
        {
            addAnimationObject(new Star3d());
        }
    }

    @Override
    public void draw()
    {
        super.draw();

        cameraX += 0.5;

        getCanvas().camera(0, height / 2, (height / 2) / tan(PI / 6), cameraX, height / 2, 0, 0, 1, 0);
    }
}
