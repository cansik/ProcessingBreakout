package Universe;

import ProcessingExtensions.ProcessingAnimationContext;
import ProcessingExtensions.ProcessingObject;
import peasy.PeasyCam;
import processing.event.MouseEvent;

import java.util.Iterator;

/**
 * Created by cansik on 26/02/15.
 */
public class UniverseAnimation extends ProcessingAnimationContext {

    PeasyCam cam;
    float cameraX = 0;

    float fieldSize = 200;
    private boolean recording;

    @Override
    public void setup()
    {
        super.setup();

        setClearScreen(true);
        float fieldSize = 2000;
        cam = new PeasyCam(this, 0, 0, 0, 1000);
        cam.lookAt(500,500, 0);

        for(int i = 0; i < 1000; i ++)
        {
            addAnimationObject(new Star3d(fieldSize));
        }

        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
    }

    @Override
    public void draw()
    {
        super.draw();

        /*
        cam.beginHUD();
        text("X: " + cam.getPosition()[0] + "\nY: " + cam.getPosition()[1] + "\nZ: " + cam.getPosition()[2], 50, height - 80);
        cam.endHUD();
        */


        cam.rotateY(-0.001);
        cam.lookAt(0, 0, 0);
        //cam.rotateX(-0.0001);;

        /*
        Iterator<ProcessingObject> it = getAnimationObjects().iterator();
        stroke(0, 255, 0);
        strokeWeight(1f);

        Star3d prev = null;
        while(it.hasNext())
        {
            if(prev == null)
                prev = (Star3d)it.next();
            else {
                Star3d current = (Star3d)it.next();
                line(current.getX(), current.getY(), current.getZ(), prev.getX(), prev.getY(), prev.getZ());
                prev = current;
            }
        }
        */




        /*
        stroke(255, 0, 100);
        for(ProcessingObject obj : getAnimationObjects()) {
            Star3d star = (Star3d) obj;

            Star3d star2 = (Star3d)getAnimationObjects().get((int)random(0, getAnimationObjects().size()));

            if(random(1000) > 999)
                line(star.getX(), star.getY(), star.getZ(), star2.getX(), star2.getY(), star2.getZ());
        }
        */



        /*
        strokeWeight(1f);
        for(ProcessingObject obj : getAnimationObjects())
        {
            Star3d star = (Star3d)obj;


            for(ProcessingObject obj2 : getAnimationObjects()) {
                Star3d star2 = (Star3d) obj2;

                stroke(0, 255, 0);
                line(star.getX(), star.getY(), star.getZ(), star2.getX(), star2.getY(), star2.getZ());
            }
        }
        */


        if (recording) {
            saveFrame("/Users/cansik/Projects/ProcessingTest/output/frames####.png");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        for(ProcessingObject obj : getAnimationObjects())
        {
            Star3d star = (Star3d)obj;
            star.fall = true;
        }
    }

    @Override
    public void keyPressed() {
        // Finish the movie if space bar is pressed!
        if (key == ' ' ) {
            println( "finishing movie" );
            recording = false;
        }

        if(key == 'r')
        {
            println("recording...");
            recording = true;
        }
    }
}
