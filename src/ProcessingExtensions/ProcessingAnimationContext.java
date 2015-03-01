package ProcessingExtensions;

import codeanticode.syphon.SyphonServer;
import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.ArrayList;

/**
 * Created by cansik on 28/02/15.
 */
public abstract class ProcessingAnimationContext extends PApplet {
    private int screenWidth = 500;
    private int screenHeight = 500;

    private SyphonServer server;
    private PGraphics canvas;

    private String name = "default";

    private int backgroundColor = 0x000000;

    private ArrayList<ProcessingObject> animationObjects;

    public void setup()
    {
        //initial setup
        size(screenWidth, screenHeight, P3D);
        canvas = createGraphics(screenWidth, screenHeight, P3D);

        background(0);
        smooth();

        animationObjects = new ArrayList<>();

        //setup syphon server
        server = new SyphonServer(this, "PRC_" + name);
    }

    public void draw()
    {
        canvas.beginDraw();
        canvas.background(backgroundColor);

        //draw animationObjects
        for(ProcessingObject obj : animationObjects) {
            //maybe call logic outside of draw
            obj.logic(this);
            obj.draw(canvas);
        }

        canvas.endDraw();

        image(canvas, 0, 0);

        server.sendImage(canvas);
    }

    public void addAnimationObject(ProcessingObject obj)
    {
        obj.setup(this);
        animationObjects.add(obj);
    }

    //getter & setter
    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public SyphonServer getServer() {
        return server;
    }

    public PGraphics getCanvas() {
        return canvas;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
