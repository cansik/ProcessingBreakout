package SchwarmTest;

import ProcessingExtensions.ProcessingAnimationContext;
import processing.core.PVector;

import java.util.List;

/**
 * Created by cansik on 20/06/15.
 */
class Fox extends BaseBoid
{
    private final float DESIRED_SEPARATION = 25f;
    private final float NEIGHBOUR_RADIUS = 50f;
    private final float MAX_FORCE = 0.5f;
    private final float MAX_SPEED = 2f;

    //size
    float r = 1.0f;

    private ProcessingAnimationContext context;

    private PVector acceleration;

    public Fox()
    {
    }

    @Override
    public void setup(ProcessingAnimationContext context) {
        this.context = context;

        velocity = PVector.random2D();
        location = new PVector(context.random(context.width), context.random(context.height));

        float angle = context.random(context.TWO_PI);
        velocity = new PVector(context.cos(angle), context.sin(angle));
        acceleration = new PVector();
    }

    @Override
    public void logic(ProcessingAnimationContext context) {

    }

    @Override
    public void draw(ProcessingAnimationContext g) {
        float theta = velocity.heading() + context.radians(90);
        g.stroke(255);

        //Draw Triangle
        g.pushMatrix();
        g.translate(location.x, location.y);
        g.rotate(theta);
        g.beginShape(g.TRIANGLES);
        g.vertex(0, -r * 2);
        g.vertex(-r, r * 2);
        g.vertex(r, r * 2);
        g.endShape();
        g.popMatrix();
    }

    @Override
    public void nextStep(List<BaseBoid> boids) {
        PVector cohesion = getCohesion(boids);
        PVector alignment = getAlignment(boids);
        PVector separation = getSepartation(boids);

        applyForce(cohesion);
        applyForce(alignment);
        applyForce(separation);

        velocity.add(acceleration);
        velocity.limit(MAX_SPEED);

        location.add(velocity);

        //reset acceleration
        acceleration.mult(0);

        //check for borders
        borders();

        //ugly drawin
        drawLinesWithNeighbours(boids);
    }


    private void drawLinesWithNeighbours(List<BaseBoid> boids)
    {
        ProcessingAnimationContext g = context;
        List<Tuple<BaseBoid, Float>> neighbours = getNearBoids(boids, NEIGHBOUR_RADIUS);

        neighbours.stream().forEach(n -> {
            BaseBoid b = n.getFirst();
            //g.stroke(255, 255, 200, 100);
            g.stroke(255 * (n.getSecond() / NEIGHBOUR_RADIUS));
            g.strokeWeight(1 * (n.getSecond() / NEIGHBOUR_RADIUS));
            g.line(location.x, location.y, b.location.x, b.location.y);
        });
    }

    private void applyForce(PVector force)
    {
        acceleration.add(force);
    }

    /**
     * Get Vector to steer to a target with easing
     * @param target
     * @return
     */
    private PVector steerTo(PVector target)
    {
        PVector desired = PVector.sub(target, location);
        float distance = location.dist(target);

        desired.normalize();
        desired.mult(MAX_SPEED * (distance / 100));

        PVector steer = PVector.sub(desired, velocity);
        steer.limit(MAX_FORCE);

        return steer;
    }

    /**
     * Calculates the average location of the nearest boids.
     * @param boids List of all boids.
     * @return Average location of the nearest boids.
     */
    private PVector getCohesion(List<BaseBoid> boids)
    {
        List<Tuple<BaseBoid, Float>> neighbours = getNearBoids(boids, NEIGHBOUR_RADIUS);

        if(neighbours.size() == 0)
            return new PVector();

        PVector sum = new PVector();

        //warning! not parallized stream (no sum function for vectors in java streams);
        neighbours.stream().map(n -> n.getFirst().location).forEach(v -> sum.add(v));

        sum.div(neighbours.size());

        return steerTo(sum);
    }

    /**
     * Calculates the alignment adjustment of the nearest boids.
     * @param boids
     * @return
     */
    private PVector getAlignment(List<BaseBoid> boids)
    {
        List<Tuple<BaseBoid, Float>> neighbours = getNearBoids(boids, NEIGHBOUR_RADIUS);

        if(neighbours.size() == 0)
            return new PVector();

        PVector steer = new PVector();

        //warning! not parallized stream (no sum function for vectors in java streams);
        neighbours.stream().map(n -> n.getFirst().velocity).forEach(v -> steer.add(v));

        steer.div(neighbours.size());
        steer.limit(MAX_FORCE);
        return steer;
    }

    private PVector getSepartation(List<BaseBoid> boids)
    {
        List<Tuple<BaseBoid, Float>> neighbours = getNearBoids(boids, DESIRED_SEPARATION);

        if(neighbours.size() == 0)
            return new PVector();

        PVector steer = new PVector();

        //warning! not parallized stream (no sum function for vectors in java streams);
        neighbours.stream().forEach(n -> {
            PVector diff = PVector.sub(location, n.getFirst().location);
            diff.normalize();
            diff.div(n.getSecond());
            steer.add(diff);
        });

        steer.div(neighbours.size());

        steer.normalize();
        steer.mult(MAX_SPEED);
        steer.sub(velocity);
        steer.limit(MAX_FORCE);
        return steer;
    }

    private void borders() {
        if (location.x < -r) location.x = context.width+r;
        if (location.y < -r) location.y = context.height+r;
        if (location.x > context.width+r) location.x = -r;
        if (location.y > context.height+r) location.y = -r;
    }
}
