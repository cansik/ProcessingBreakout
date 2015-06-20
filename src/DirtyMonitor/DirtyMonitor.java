package DirtyMonitor;

import ProcessingExtensions.ProcessingAnimationContext;
import ProcessingExtensions.ProcessingObject;
import de.voidplus.leapmotion.*;
import processing.core.PVector;

/**
 * Created by cansik on 12/03/15.
 */
public class DirtyMonitor extends ProcessingAnimationContext {

    LeapMotion leap;

    public DirtyMonitor()
    {
        super();

        setScreenWidth(1280);
        setScreenHeight(720);
    }

    @Override
    public void setup() {
        super.setup();

        leap = new LeapMotion(this);

        setClearScreen(true);
        setName("DirtyMonitor");

        for(int i = 0; i < 20; i++)
            addAnimationObject(new Cube());
    }

    int x = 0;
    int c = 0;

    boolean noHand = false;

    @Override
    public void draw() {
        super.draw();

        int i = 0;


        if(leap.getHands().size() > 0)
        {
            Hand h = leap.getHands().get(0);

            if(h.hasFingers()) {
                Finger f = h.getIndexFinger();
                PVector v = new PVector(f.getPositionOfJointTip().x, f.getPositionOfJointTip().y, f.getPositionOfJointTip().z);

                noHand = false;

//                System.out.println("hand and finger detected...");
            //    System.out.println("Vector: " + v.toString());



                    for (ProcessingObject obj : getAnimationObjects()) {
                        if (obj instanceof Cube) {

                            if(h.getGrabStrength() < 0.5)
                                ((Cube) obj).MoveToPosition(v);
                            else
                                ((Cube) obj).spread(this);
                        }
                    }
            }

        }

        if(leap.getHands().size() == 0 && !noHand) {
            noHand = true;
            for (ProcessingObject obj : getAnimationObjects())
                if (obj instanceof Cube)
                    ((Cube) obj).spread(this);
        }

        /*
        for(Hand hand : leap.getHands()) {
            fill(255, 100, 100 * i++);

            for (Finger f : hand.getFingers()) {

                stroke(255, 255, 255, 50);



                for (int p = 0; p < getScreenWidth(); p++) {
                    if (random(100) > 90)
                        line(p, 0, f.getPositionOfJointTip().x, f.getPositionOfJointTip().y);
                }



                g.pushMatrix();
                Finger z = f;
                g.translate(z.getPositionOfJointTip().x, z.getPositionOfJointTip().y, z.getPositionOfJointTip().z * -5);

                g.box(50);
                g.popMatrix();

            }
        }
        */
    }


    @Override
    public void keyPressed() {
        if (key == ' ' ) {
            println( "spread" );

            for (ProcessingObject obj : getAnimationObjects())
                if (obj instanceof Cube)
                    ((Cube) obj).spread(this);
        }
    }
}
