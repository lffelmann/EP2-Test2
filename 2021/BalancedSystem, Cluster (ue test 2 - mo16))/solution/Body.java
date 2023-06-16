import java.awt.*;
import java.util.NoSuchElementException;
import java.util.Objects;

// This class represents celestial bodies like stars, planets, asteroids, etc..
public class Body implements Cluster {

    private String name;
    private double mass;
    private double radius;
    private Vector3 position; // position of the center.
    private Vector3 currentMovement;
    private Color color; // for drawing the body.

    public Body(String name, double mass, double radius, Vector3 position, Vector3 currentMovement, Color color) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.position = position;
        this.currentMovement = currentMovement;
        this.color = color;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Body body = (Body) o;

        double m1 = body.mass;
        double m2 = this.mass;
        double epsilon = 0.0000001; //error tolerance
        boolean massEquality = m1 <= (m2+epsilon) || m1 >= (m2-epsilon);
        return massEquality && name.equals(body.name);
    }

    @Override
    public int hashCode() {
        //mass must be rounded - see above
        return Objects.hash(name, Math.round(mass));
    }

    @Override
    public BodyIterator iterator() {
        Body element = this;
        return new BodyIterator() {
            private boolean visited = false;

            @Override
            public boolean hasNext() {
                return !visited;
            }

            @Override
            public Body next() throws NoSuchElementException {
                if (!hasNext())
                    throw new NoSuchElementException();
                visited = true;
                return element;
            }

            @Override
            public void remove() throws IllegalStateException {

            }
        };
    }


    @Override
    public Cluster add(Body c) throws BalancedSystemIllegalArgumentException {
        return new BalancedSystem(c, this);
    }

    @Override
    public int numberOfBodies() {
        return 1;
    }

    @Override
    public double getMass() {
        return this.mass;
    }
}

