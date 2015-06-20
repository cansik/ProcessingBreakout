package LightAttack;

import MusikSehen.MovingCircles;
import ProcessingExtensions.ProcessingAnimationContext;
import de.voidplus.leapmotion.Finger;
import de.voidplus.leapmotion.Hand;
import de.voidplus.leapmotion.LeapMotion;
import processing.core.PVector;

/**
 * Created by cansik on 10/06/15.
 */
public class LightAttack extends ProcessingAnimationContext {

    final int cubeAmount = 10;

    LeapMotion leap;
    PVector[] cubes; //x, y, z

    boolean recording = false;

    public LightAttack()
    {
        super();
        setScreenWidth(1280);
        setScreenHeight(720);

        //leap = new LeapMotion(this);
    }

    @Override
    public void setup() {
        super.setup();

        setClearScreen(true);
        smooth();

        setName("LightAttack");

        cubes = new PVector[cubeAmount];

        for(int i = 0; i < cubes.length; i++)
            cubes[i] = new PVector(random(getScreenWidth()), random(getScreenHeight()), random(360));
    }

    void logic()
    {
        for(int i = 0; i < cubes.length; i++)
            cubes[i].z = (cubes[i].z + 0.05) > 360 ? 0 : cubes[i].z + 0.05f;
    }

    @Override
    public void draw() {
        super.draw();

        logic();

        //draw cubes
        for(int i = 0; i < cubes.length; i++)
        {
            PVector cv = cubes[i];
            g.pushMatrix();
            g.translate(cv.x, cv.y, 0);
            g.fill(51, 153, 255);
            g.stroke(51, 153, 255);
            g.rotateX(cv.z);
            g.rotateZ(cv.z);
            g.box(50);
            g.popMatrix();
        }

        //light


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
            setup();
        }

        if(key == 'r')
        {
            println("recording...");
            recording = true;
        }
    }
}
