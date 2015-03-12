package ProcessingExtensions;

import codeanticode.syphon.SyphonServer;
import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by cansik on 28/02/15.
 */
public abstract class ProcessingAnimationContext extends PApplet {
    private int screenWidth = 600;
    private int screenHeight = 600;

    private SyphonServer server;
    private PGraphics canvas;

    private String name = "default";

    private int backgroundColor = 0x000000;

    private ArrayList<ProcessingObject> animationObjects;
    private ArrayList<ProcessingObject> deadObjects;

    public void setup() {
        //initial setup
        size(screenWidth, screenHeight, P3D);
        canvas = createGraphics(screenWidth, screenHeight, P3D);

        smooth();

        animationObjects = new ArrayList<>();
        deadObjects = new ArrayList<>();

        background(backgroundColor);

        //setup syphon server
        server = new SyphonServer(this, "PRC_" + name);
    }

    public void draw()
    {
        //draw animationObjects
        for(ProcessingObject obj : animationObjects) {
            //maybe call logic outside of draw
            obj.logic(this);
            obj.draw(this);
        }

        sendImageToSyphon();

        //delete dead objects
        animationObjects.removeAll(deadObjects);
        deadObjects.clear();
    }

    public void addAnimationObject(ProcessingObject obj)
    {
        obj.setup(this);
        animationObjects.add(obj);
    }

    public void removeAnimationObject(ProcessingObject obj)
    {
        deadObjects.add(obj);
    }

    private void sendImageToSyphon()
    {
         server.sendImage(g);
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

    public ArrayList<ProcessingObject> getAnimationObjects() {
        return animationObjects;
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
