import java.io.IOException;
import java.util.NoSuchElementException;

public class BalancedSystem implements Cluster {

    private Cluster left;
    private Cluster right;

    //the constructor should throw a BalancedSystemIllegalArgumentException (to be implemented), if one of the arguments is null
    BalancedSystem(Cluster left, Cluster right) throws BalancedSystemIllegalArgumentException {
        if (left == null || right == null)
            throw new BalancedSystemIllegalArgumentException("One element is null.");
        this.left = left;
        this.right = right;
    }

    @Override
    //adds a Body b to the BalancedSystem. If the mass of the left cluster is less than the mass of the right cluster,
    //the body is added to the left cluster, otherwise to the right cluster.
    //Returns this after the operation
    public Cluster add(Body b) throws BalancedSystemIllegalArgumentException {
        if (left.getMass() < right.getMass())
            left = left.add(b);
        else
            right = right.add(b);
        return this;
    }

    @Override
    //returns overall number of bodies in the cluster (and its sub-clusters)
    public int numberOfBodies() {
        int[] count = {0};
        bodyCount(left, count);
        bodyCount(right, count);
        return count[0];
    }

    private void bodyCount(Cluster e, int[] c) {
        if (e.getClass() == Body.class)
            c[0]++;
        else {
            BalancedSystem x = (BalancedSystem) e;
            bodyCount(x.getLeft(), c);
            bodyCount(x.getRight(), c);
        }
    }

    @Override
    //returns the summed mass of all the bodies in the cluster (ans its sub-clusters)
    public double getMass() {
        int[] count = {0};
        massCount(left, count);
        massCount(right, count);
        return count[0];
    }

    private void massCount(Cluster e, int[] c) {
        if (e.getClass() == Body.class)
            c[0] += e.getMass();
        else {
            BalancedSystem x = (BalancedSystem) e;
            bodyCount(x.getLeft(), c);
            bodyCount(x.getRight(), c);
        }
    }

    @Override
    //returns an iterator over all bodies
    public BodyIterator iterator() {
        BodyIterator rightIter = right.iterator();
        BodyIterator leftIter = left.iterator();
        return new BodyIterator() {
            @Override
            public boolean hasNext() {
                return leftIter.hasNext() || rightIter.hasNext();
            }

            @Override
            public Body next() throws NoSuchElementException {
                if (leftIter.hasNext())
                    return leftIter.next();
                if (!rightIter.hasNext())
                    throw new NoSuchElementException();
                return rightIter.next();
            }

            @Override
            public void remove() throws IllegalStateException {

            }
        };
    }

    @Override
    //returns a String indicating the masses of the left and right subsystem
    //E.g., "Left mass: 10.0, right mass: 12.0
    public String toString() {
            return "Left mass: " + left.getMass() + ", Right mass: " + right.getMass();
    }

    private Cluster getLeft() {
        return left;
    }

    public Cluster getRight() {
        return right;
    }
}