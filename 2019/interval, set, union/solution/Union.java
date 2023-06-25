import java.util.*;

// 'Union' implements 'Set' and represents a union of multiple (at least two) unconnected intervals.
// Hint: It is allowed to use the java Collection-framework for the implementation (e.g., List<Interval>).
public class Union implements Set {

    private ArrayList<Interval> list = new ArrayList<>();

    public Union(Interval a, Interval b) {
        if (a.isConnected(b))
            list.add((Interval) a.union(b));
        list.add(a);
        list.add(b);
    }

    // Helper method: Removes all intervals from this 'Union' which are connected to 'other'
    // (i.e., intervals where the union with 'other' is a single continuous interval).
    // The union of all the removed intervals and 'other' is returned.
    // Precondition: other != null.
    public Interval removeAllConnectedWith(Interval other) {
        ArrayList<Interval> remove = new ArrayList<>();
        int min = other.getLower();
        int max = other.getUpper();
        for (Interval i : list){
            if (other.isConnected(i)) {
                remove.add(i);
                min = Math.min(min, i.getLower());
                max = Math.max(max, i.getUpper());
            }
        }
        for (Interval i : remove)
            list.remove(i);
        return new Interval(min, max);
    }

    @Override
    public boolean isContinuous() {
        return list.size() == 1;
    }

    //add to list
    @Override
    public Set union(Interval other) {
        list.add(removeAllConnectedWith(other));
        if (this.isContinuous())
            return list.get(0);
        return this;
    }

    @Override
    public Iterator<Integer> iterator() {
        ArrayList<Iterator> iter = new ArrayList<>();
        for (Interval i : list){
            iter.add(i.iterator());
        }
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                for (Iterator i : iter) {
                    if (i.hasNext())
                        return true;
                }
                return false;
            }

            @Override
            public Integer next() {
                if (!hasNext())
                    throw new NoSuchElementException("no element!");
                for (Iterator i : iter) {
                    if (i.hasNext())
                        return (Integer) i.next();
                }
                return (Integer) iter.get(iter.size()).next();
            }
        };
    }

    @Override
    public String toString() {
        String buffer = "[";
        for (Interval i : list)
            buffer += i.toString() + ", ";
        return buffer.substring(0, buffer.length() - 2) + "]";
    }
}

