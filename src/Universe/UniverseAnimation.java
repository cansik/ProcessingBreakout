package Universe;

import ProcessingExtensions.ProcessingAnimationContext;
import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * Created by cansik on 26/02/15.
 */
public class UniverseAnimation extends ProcessingAnimationContext {

    @Override
    public void setup()
    {
        super.setup();

        for(int i = 0; i < 100; i ++)
        {
            addAnimationObject(new Star());
        }
    }
}
