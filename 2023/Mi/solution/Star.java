import java.util.NoSuchElementException;

// Leaf node of a mobile. The actual decoration of a mobile.
// A 'Star' has a specified weight of type 'int', that can not be changed after
// initialisation. 'Star' implements 'Decoration'.
//
public class Star implements Decoration {

    private int weight;

    public Star(int weight) {
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    // Returns a readable representation of 'this' with the
    // symbol '*' followed by the weight of this star.
    public String toString() {
        return "*" + weight;
    }

    @Override
    public Countable getStickCountable() {
        return () -> 0;
    }

    @Override
    public StarIterator iterator() {
        Star s = this;
        return new StarIterator() {
            boolean visited = false;

            @Override
            public boolean hasNext() {
                return !visited;
            }

            @Override
            public Star next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                visited = true;
                return s;
            }
        };
    }
}

// TODO: define additional classes if needed (either here or in a separate file).
