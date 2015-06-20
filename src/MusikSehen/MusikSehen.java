package MusikSehen;

import ProcessingExtensions.ProcessingAnimationContext;
import de.voidplus.leapmotion.Finger;
import de.voidplus.leapmotion.Hand;
import de.voidplus.leapmotion.LeapMotion;
import processing.core.PVector;

/**
 * Created by cansik on 24/03/15.
 */
public class MusikSehen extends ProcessingAnimationContext {

    int color = 255;

    int barWidth = 100;
    int lastBar = -1;

    int stepCounter = 0;

    int spawnSpeed = 50;

    LeapMotion leap;

    int green = 200;


    boolean recording = false;

    public MusikSehen()
    {
        super();
        setScreenWidth(1280);
        setScreenHeight(720);

        leap = new LeapMotion(this);
    }

    @Override
    public void setup() {
        super.setup();

        setClearScreen(true);
        smooth();

        setName("MusikSehen");
    }

    void logic()
    {
        if(stepCounter > spawnSpeed)
        {
            addAnimationObject(new MovingCircles(color(0, green, 0)));
            stepCounter = 0;
        }


        if(leap.getHands().size() > 0)
        {
            Hand h = leap.getHands().get(0);

            green = (int)(h.getGrabStrength() * 255);

            PVector handPosition = h.getPosition();
            int tempSpawnSpeed = (int)(handPosition.y - 600) * - 1;

            spawnSpeed = Math.abs(tempSpawnSpeed > 80 ? 50 : tempSpawnSpeed);

            System.out.println(spawnSpeed);

            if(h.hasFingers()) {
                Finger f = h.getIndexFinger();
                PVector v = new PVector(f.getPositionOfJointTip().x, f.getPositionOfJointTip().y, f.getPositionOfJointTip().z);
            }
        }

        stepCounter++;
    }

    @Override
    public void draw() {
        super.draw();

        logic();

        if (recording) {
            saveFrame("/Users/cansik/Projects/ProcessingTest/output/frames####.png");
        }

    }

    @Override
    public void keyPressed() {
        // Finish the movie if space bar is pressed!
        if (key == 's' ) {
            println( "finishing movie" );
            recording = false;
        }

        if(key == ' ')
        {
            spawnSpeed = 50;
        }

        if(key == 'r')
        {
            println("recording...");
            recording = true;
        }
    }
}
