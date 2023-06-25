import java.util.*;

// This class implements 'Set' and represents an interval of Integer numbers, specified by the lower and
// upper number of the interval.
public class Interval implements Set {

    private int lower, upper;

    // Initializes this 'Interval' with the bounds of the interval.
    public Interval(int lower, int upper) {
        this.lower = Math.min(lower, upper);
        this.upper = Math.max(lower, upper);
    }

    @Override
    public boolean isContinuous() {
        return true;
    }

    // Returns the union of 'this' and 'other'. If the result can be represented by a single
    // interval (i.e., 'this' and 'other' are connected), the result
    // is of type 'Interval', otherwise it is a 'Union'.
    // Examples:
    // The union of 0-5 and 3-8 is 0-8  (type 'Interval'),
    // the union of 0-5 and 6-7 is 0-7  (type 'Interval'),
    // the union of 0-5 and 7-8 is [0-5, 7-8] (type 'Union').
    @Override
    public Set union(Interval other) {
        if (isConnected(other))
            return new Interval(Math.min(lower, other.lower), Math.max(upper, other.upper));
        return new Union(this, other);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int count = lower;
            @Override
            public boolean hasNext() {
                return count <= upper;
            }

            @Override
            public Integer next() {
                return count++;
            }
        };
    }

    @Override
    public String toString() {
        return lower + "-" + upper;
    }

    public boolean isConnected(Interval other) {
        if ((other.lower <= upper && other.lower >= lower) || (other.upper <= upper && other.upper >= lower))
            return true;
        return false;
    }

    public int getLower() {
        return lower;
    }

    public int getUpper() {
        return upper;
    }
}

