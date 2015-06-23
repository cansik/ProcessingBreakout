package Planar;

import ProcessingExtensions.ProcessingAnimationContext;
import de.voidplus.leapmotion.LeapMotion;
import peasy.PeasyCam;
import processing.core.PVector;

/**
 * Created by cansik on 22/06/15.
 */
public class Planar2 extends ProcessingAnimationContext {
    final int ROW_COUNT = 20;
    final int COLUMN_COUNT = 13;

    final int BOX_SIZE = 50;

    final int PLANAR_LENGTH = 100;

    final float MAX_RADIUS = 40f;
    final float MAX_DEPTH = 200f;
    final float MAX_SPEED = 2f;
    final float MAX_FORCE = 0.5f;

    final boolean RENDER_POINTS = false;
    final boolean RENDER_SHAPES = false;
    final boolean RENDE_TRIANGLES = true;

    LeapMotion leap;
    PeasyCam cam;

    VertexPoint[][] points;

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

        cam = new PeasyCam(this, 0, 0, 0, 800);
        cam.lookAt(0, 0, 0);
        cam.setActive(false);

        points = new VertexPoint[ROW_COUNT][COLUMN_COUNT];

        float widthSectorSize = width / ROW_COUNT;
        float heightSectorSize = height / COLUMN_COUNT;

        float startX = -(ROW_COUNT * PLANAR_LENGTH) / 2;
        float startY = -(COLUMN_COUNT * PLANAR_LENGTH) / 2;

        //create grid
        for(int x = 0; x < points.length; x++)
        {
            for(int y = 0; y < points[x].length; y++)
            {
                PVector point = new PVector();

                point.x = x * PLANAR_LENGTH + startX;
                point.y = y * PLANAR_LENGTH + startY;
                point.z = 0; //random(PLANAR_LENGTH);

                points[x][y] = new VertexPoint(this, point, MAX_RADIUS, MAX_SPEED, MAX_FORCE, MAX_DEPTH);
            }
        }
    }

    void logic()
    {
        for(int x = 0; x < points.length; x++)
        {
            for(int y = 0; y < points[x].length; y++) {
                points[x][y].nextStep();
            }
        }
    }

    @Override
    public void draw() {
        super.draw();
        logic();
        lights();

        if(RENDER_POINTS)
        {
            for(int x = 0; x < points.length; x++)
            {
                for(int y = 0; y < points[x].length; y++) {
                    PVector p = points[x][y].location;

                    pushMatrix();
                    translate(p.x, p.y, p.z);
                    fill(255);
                    box(BOX_SIZE, BOX_SIZE, BOX_SIZE);

                    popMatrix();
                }
            }
        }


        if(RENDER_SHAPES) {
            //draw shape
            noStroke();
            fill(200, 200);

            for (int x = 0; x < points.length - 1; x++) {
                for (int y = 0; y < points[x].length - 1; y++) {
                    PVector p1 = points[x][y].location;
                    PVector p2 = points[x][y + 1].location;
                    PVector p3 = points[x + 1][y + 1].location;
                    PVector p4 = points[x + 1][y].location;

                    beginShape();
                    vertex(p1.x, p1.y, p1.z);
                    vertex(p2.x, p2.y, p2.z);
                    vertex(p3.x, p3.y, p3.z);
                    vertex(p4.x, p4.y, p4.z);
                    endShape();
                }
            }
        }

        if(RENDE_TRIANGLES) {
            //draw shape
            noStroke();
            fill(200, 200);

            for (int x = 0; x < points.length - 1; x++) {
                for (int y = 0; y < points[x].length - 1; y++) {
                    PVector p1 = points[x][y].location;
                    PVector p2 = points[x][y + 1].location;
                    PVector p3 = points[x + 1][y + 1].location;
                    PVector p4 = points[x + 1][y].location;

                    //triangle one
                    beginShape();
                    vertex(p1.x, p1.y, p1.z);
                    vertex(p2.x, p2.y, p2.z);
                    vertex(p4.x, p4.y, p4.z);
                    endShape();

                    //triangle two
                    beginShape();
                    vertex(p2.x, p2.y, p2.z);
                    vertex(p4.x, p4.y, p4.z);
                    vertex(p3.x, p3.y, p3.z);
                    endShape();
                }
            }
        }

        ambient(255);
        //ambientLight(0, 0, 200, 0, -1000, 20);
        //directionalLight(0, 0, 255, 0, 0, 20);

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

        if(key == 'c')
        {
            cam.setActive(!cam.isActive());
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
