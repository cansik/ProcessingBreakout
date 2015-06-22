package Planar;

import ProcessingExtensions.ProcessingAnimationContext;
import de.voidplus.leapmotion.LeapMotion;
import peasy.PeasyCam;
import processing.core.PVector;

/**
 * Created by cansik on 22/06/15.
 */
public class Planar2 extends ProcessingAnimationContext {
    final int ROW_COUNT = 30;
    final int COLUMN_COUNT = 30;

    final int BOX_SIZE = 10;

    final int QUAD_SIZE = 50;

    LeapMotion leap;
    PeasyCam cam;

    PVector[][] points;

    boolean recording = false;

    public Planar2()
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

        points = new PVector[ROW_COUNT][COLUMN_COUNT];

        //create grid
        for(int x = 0; x < points.length; x++)
        {
            for(int y = 0; y < points[x].length; y++)
            {
                PVector point = new PVector();

                point.x = x * QUAD_SIZE;
                point.y = y * QUAD_SIZE;
                point.z = random(QUAD_SIZE);

                points[x][y] = point;
            }
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

        /*
        for(int x = 0; x < points.length; x++)
        {
            for(int y = 0; y < points[x].length; y++) {
                PVector p = points[x][y];

                pushMatrix();
                translate(p.x, p.y, p.z);
                fill(255);
                box(BOX_SIZE, BOX_SIZE, BOX_SIZE);

                popMatrix();
            }
        }
        */

        //draw shape
        for(int x = 0; x < points.length - 1; x++) {
            for (int y = 0; y < points[x].length - 1; y++) {
                PVector p1 =  points[x][y];
                PVector p2 =  points[x][y+1];
                PVector p3 =  points[x+1][y+1];
                PVector p4 =  points[x+1][y];

                beginShape();
                vertex(p1.x, p1.y, p1.z);
                vertex(p2.x, p2.y, p2.z);
                vertex(p3.x, p3.y, p3.z);
                vertex(p4.x, p4.y, p4.z);
                endShape();
            }
        }

        ambient(200);

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
