import java.util.NoSuchElementException;

// Leaf node of a mobile. The actual decoration of a mobile.
// A 'Star' has a specified weight, that can not be changed after
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

    @Override
    // Returns a readable representation of 'this' with the
    // symbol '*' followed by the weight of this star.
    public String toString() {
        return "*" + weight;
    }

    @Override
    public StarCollection getStarCollection() {
        Collection c = new Collection();
        c.add(this);
        return c;
    }

    @Override
    public StarIterator iterator() {
        Star s = this;
        return new StarIterator() {
            private boolean visited = false;

            @Override
            public boolean hasNext() {
                return !visited;
            }

            @Override
            public Star next() {
                if (visited)
                    throw new NoSuchElementException("no star element!");
                visited = true;
                return s;
            }
        };
    }
}
