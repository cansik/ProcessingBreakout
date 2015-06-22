package Planar;

import ProcessingExtensions.ProcessingAnimationContext;
import de.voidplus.leapmotion.LeapMotion;
import peasy.PeasyCam;
import processing.core.PVector;

/**
 * Created by cansik on 22/06/15.
 */
public class Planar extends ProcessingAnimationContext {
    final int POINT_AMOUNT = 10;
    final int BOX_SIZE = 10;

    LeapMotion leap;
    PeasyCam cam;

    PVector[] points;

    boolean recording = false;

    public Planar()
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

        setName("Planar");

        cam = new PeasyCam(this, 0, 0, 0, 1000);
        cam.lookAt(500,500, 0);

        points = new PVector[POINT_AMOUNT];

        float sectorCount = points.length;
        float sectorWidth = TWO_PI / sectorCount;
        PVector center = new PVector(width/2, (height)/2, 0);

        for(int i = 0; i < points.length; i++)
        {
            float sectorStart = i * sectorWidth;
            float sectorEnd = sectorStart + sectorWidth;

            float angle = random(sectorStart, sectorEnd);

            PVector v = new PVector(cos(angle),sin(angle), 0);
            v.mult(random(40, 400));
            v.z = random(200);
            v.add(center);

            points[i] = v;
        }
    }

    void logic()
    {

    }

    @Override
    public void draw() {
        super.draw();
        logic();
        lights();

        for(PVector p : points)
        {
            pushMatrix();
            translate(p.x, p.y, p.z);
            fill(255);
            box(BOX_SIZE, BOX_SIZE, BOX_SIZE);

            popMatrix();
        }

        beginShape();
        for(PVector p : points)
        {
            vertex(p.x, p.y, p.z);
        }
        endShape();

        //lighgt
        spotLight(255, 0, 0, width/2, height/2, mouseX, mouseY, 0, -1, PI/4, 2);


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
