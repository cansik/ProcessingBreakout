package SchwarmTest;

import ProcessingExtensions.ProcessingObject;
import processing.core.PVector;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cansik on 20/06/15.
 */
public abstract class BaseBoid extends ProcessingObject {
    protected PVector location;
    protected PVector velocity;

    public abstract void nextStep(List<BaseBoid> boids);

    /**
     * Returns the boids in the near
     * @param boids list of all boids in the flock
     * @param maximalDistance Maximum Distnace boids <= will be returned
     * @return List of tupel with boid and distance!
     */
    public List<Tuple<BaseBoid, Float>> getNearBoids(List<BaseBoid> boids, float maximalDistance)
    {
        return boids.stream()
                .filter(b -> !b.equals(this))
                .map(b -> new Tuple<BaseBoid, Float>(b, b.getDistanceTo(this)))
                .filter(t -> t.getSecond() <= maximalDistance)
                .collect(Collectors.toList());
    }

    /**
     * Returns the distance to the other boid
     * @param boid To measure the distance
     * @return Distnace to the other boid
     */
    public float getDistanceTo(BaseBoid boid)
    {
        PVector myPosition = getPositionByBoid(boid);
        PVector boidPosition = boid.getPositionByBoid(this);

        return myPosition.dist(boidPosition);
    }

    /**
     * Returns the current location with the boid's current location
     * @param boid Boid to take into calculation
     * @return NearestPoint to the other boid
     */
    public PVector getPositionByBoid(BaseBoid boid)
    {
        return this.location;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof BaseBoid))
            return false;

        BaseBoid boid = (BaseBoid)obj;

        return boid.location.equals(this.location) && boid.velocity.equals(this.velocity);
    }
}
