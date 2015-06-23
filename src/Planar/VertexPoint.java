package Planar;

import ProcessingExtensions.ProcessingAnimationContext;
import processing.core.PVector;

/**
 * Created by cansik on 22/06/15.
 */
public class VertexPoint {
    final float MAX_RADIUS;
    final float MAX_SPEED;
    final float MAX_FORCE;
    final float MAX_DEPTH;

    PVector location;
    PVector velocity;

    private PVector center;
    private PVector target;

    private ProcessingAnimationContext context;

    public VertexPoint(ProcessingAnimationContext context, PVector location, float max_radius, float max_speed, float max_force, float max_depth)
    {
        this.context = context;
        this.location = location;
        this.velocity = new PVector();

        center = new PVector();
        center.add(location);

        MAX_RADIUS = max_radius;
        MAX_SPEED = max_speed;
        MAX_FORCE = max_force;
        MAX_DEPTH = max_depth;
    }

    public void nextStep()
    {
        PVector acceleration = new PVector();

        PVector sep = separationFromCenter();
        PVector coh = cohesionFromCenter();
        PVector ran = PVector.random3D();

        sep.mult(1f);
        coh.mult(1.5f);
        ran.mult(0.5f);

        acceleration.add(sep);
        acceleration.add(coh);
        acceleration.add(ran);

        velocity.add(acceleration);
        velocity.limit(MAX_SPEED);

        location.add(velocity);
    }

    private PVector separationFromCenter()
    {
        float dist = center.dist(location);
        PVector speed = new PVector();

        //dist < 5% of max
        float innerRadius = (MAX_RADIUS * 0.05f);
        if(dist < innerRadius)
        {
            //find random target
            if(target == null)
                target = new PVector(context.random(innerRadius, MAX_RADIUS),
                        context.random(innerRadius, MAX_RADIUS),
                        context.random(innerRadius, MAX_DEPTH));

            //calculate anti-force
            speed.add(steerTo(target));
        }
        else
        {
            target = null;
        }

        return speed;
    }

    private PVector cohesionFromCenter()
    {
        float dist2d = center.dist(new PVector(location.x, location.y));
        float dist3d = center.dist(location);
        PVector speed = new PVector();

        //dist > 80% of max
        float innerRadius = (MAX_RADIUS * 0.8f);

        if(dist2d > innerRadius)
        {
            //calculate anti-force
            PVector t2d = steerTo(center);
            speed.add(new PVector(t2d.x, t2d.y));
        }

        if(dist3d > MAX_DEPTH)
        {
            PVector t3d = steerTo(center);
            speed.add(new PVector(0, 0, t3d.z));
        }

        return speed;
    }

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
}
