package Strokes;

import ProcessingExtensions.ProcessingAnimationContext;

/**
 * Created by cansik on 28/02/15.
 */
public class StrokesAnimation extends ProcessingAnimationContext {
    float x =0;
    float y=0;

    float x2=0;
    float y2=0;
    float easing = 0.01f;
    float nEasing = 0.05f;

    int r = 250, g = 50, b = 100;
    int dr = 1, dg = 1, db = 1;

    public void setup() {
        size(800, 800);
        stroke(254);
        background(0);
        fill(0);
    }

    public void draw() {

        r += dr;
        g += dg;
        b += db;

        stroke(r,g,b, 150);

        if(r >= 255 || r <= 0)
            dr *= -1;

        if(g > 255 || g <= 0)
            dg *= -1;

        if(b > 255 || b <= 0)
            db *= -1;

        strokeWeight(1.5f);
        x+=(mouseX-x)*easing;
        y+=(mouseY-y)*easing;

        x2+=(mouseX-x2)*nEasing;
        y2+=(mouseY-y2)*nEasing;
        line(x, y, x2, y2);
    }
}
