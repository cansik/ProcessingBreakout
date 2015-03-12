package PixelAttack;

import ProcessingExtensions.ProcessingAnimationContext;

/**
 * Created by cansik on 11/03/15.
 */
public class PixelAttack extends ProcessingAnimationContext {

    final float PIXEL_SIZE = 20;
    final float PADDING = 0;

    float centerX;
    float centerY;

    float pixelCount = 20;
    float pixelDirection = 1.2f;

    @Override
    public void setup() {
        super.setup();

        centerX = getScreenWidth() / 2;
        centerY = getScreenHeight() / 2;
    }


    @Override
    public void draw() {
        super.draw();
        clear();

        //helper grid
        /*
        stroke(255);
        line(centerX, 0, centerX, getScreenHeight());
        line(0, centerY,  getScreenWidth(), centerY);
        */

        stroke(0);
        rectMode(CENTER);
        fill(255, 100, 100, 200);

        /*
        int halfPixels  = Math.round(pixelCount / 2);

        for(int i = (-1 * halfPixels); i < halfPixels; i++)
        {
            float currentWidth = centerX + (i * (PADDING + PIXEL_SIZE)) + ((PADDING + PIXEL_SIZE) / 2);
            float currentHeight = centerY;
            rect(currentWidth, currentHeight, PIXEL_SIZE, PIXEL_SIZE);
        }
        */

        //create rings
        float PaddingAndPixel = PADDING + PIXEL_SIZE;

        /*
        for(int ring = 0; ring <= pixelCount; ring++)
        {
            fill(255, 100, ring * 50, 200);
            for(int orientation = 0; orientation < 4; orientation++)
            {
                float dx = (orientation < 2) ? 1 : -1;
                float dy = (orientation >= 1 && orientation <= 2) ? 1 : -1;

                float currentWidth = centerX + (dx * (ring * PaddingAndPixel));
                float currentHeight = centerY + (dy * (ring * PaddingAndPixel));

                rect(currentWidth, currentHeight, PIXEL_SIZE, PIXEL_SIZE);

            }
        }
        */

        for(int ring = 0; ring < pixelCount; ring++)
        {
            //Color each ring different
            fill(100, 255, ring * 10);


            int borderCount = (ring * 2);

            /*
            for(int orientation = 0; orientation < 4; orientation++)
            {
                float dx = (orientation < 2) ? 1 : -1;
                float dy = (orientation >= 1 && orientation <= 2) ? 1 : -1;

                for (int border = 0; border <= borderCount; border++) {
                    float currentWidth = centerX + dx * (ring * PaddingAndPixel) + (dx > 0 ? 1 : 0) * (border * PaddingAndPixel);
                    float currentHeight = centerY + dx * (ring * PaddingAndPixel)+ (dy < 0 ? 1 : 0) * (border * PaddingAndPixel);

                    rect(currentWidth, currentHeight, PIXEL_SIZE, PIXEL_SIZE);
                }
            }
            */




            //top
            for (int border = 0; border <= borderCount; border++) {
                float currentWidth = centerX - (ring * PaddingAndPixel) + (border * PaddingAndPixel);
                float currentHeight = centerY - (ring * PaddingAndPixel);

                rect(currentWidth, currentHeight, PIXEL_SIZE, PIXEL_SIZE);
            }

            //left
            for (int border = 1; border <= borderCount; border++) {
                float currentWidth = centerX - (ring * PaddingAndPixel);
                float currentHeight = centerY - (ring * PaddingAndPixel) + (border * PaddingAndPixel);

                rect(currentWidth, currentHeight, PIXEL_SIZE, PIXEL_SIZE);
            }

            //bottom
            for (int border = 0; border <= borderCount - 1; border++) {
                float currentWidth = centerX + (ring * PaddingAndPixel)  - (border * PaddingAndPixel);
                float currentHeight = centerY + (ring * PaddingAndPixel);

                rect(currentWidth, currentHeight, PIXEL_SIZE, PIXEL_SIZE);
            }

            //right
            for (int border = 1; border <= borderCount - 1; border++) {
                float currentWidth = centerX + (ring * PaddingAndPixel);
                float currentHeight = centerY + (ring * PaddingAndPixel) - (border * PaddingAndPixel);

                rect(currentWidth, currentHeight, PIXEL_SIZE, PIXEL_SIZE);
            }
        }

        if(pixelCount > 20 || pixelCount <= 0)
            pixelDirection *= -1;

        pixelCount += pixelDirection;


    }

    @Override
    public void keyPressed() {
        // Finish the movie if space bar is pressed!
        if (key == ' ' ) {
           pixelCount += 1;
        }

        if(key == 'd')
        {
            pixelCount -= 1;
        }
    }
}
