package DirtyMonitor;

import ProcessingExtensions.ProcessingAnimationContext;
import de.voidplus.leapmotion.*;

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

        setName("DirtyMonitor");
    }

    int x = 0;

    @Override
    public void draw() {
        super.draw();

        clear();

        if(leap.getHands().size() > 0) {
            Hand hand = leap.getHands().get(0);

            for(Finger f : hand.getFingers()) {

                stroke(255, 255, 255, 50);

                for (int i = 0; i < getScreenWidth(); i++) {
                    if (random(100) > 90)
                        line(i, 0, f.getPositionOfJointDip().x, f.getPositionOfJointDip().y);
                }

            }

        }
    }
}
