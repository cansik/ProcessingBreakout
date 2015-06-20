package SchwarmTest;

import ProcessingExtensions.ProcessingAnimationContext;
import de.voidplus.leapmotion.LeapMotion;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cansik on 16/06/15.
 */
public class SchwarmTest extends ProcessingAnimationContext {

    final int FOX_AMOUNT = 200;

    int frameCount = 0;

    List<BaseBoid> boids;

    LeapMotion leap;
    boolean recording = false;

    public SchwarmTest()
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

        //background(100);

        setName("SchwarmTest");


        boids = new ArrayList<>();

        //add boids
        for(int i = 0; i < FOX_AMOUNT; i++)
            boids.add(new Fox());

        //finally setup all boids
        boids.stream().forEach(b -> b.setup(this));
    }

    void logic()
    {
        boids.stream().forEach(b -> {
            b.logic(this);
            b.nextStep(boids);
        });
    }

    @Override
    public void draw() {
        super.draw();
        logic();
        background(0);

        boids.stream().forEach(b -> b.draw(this));

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
