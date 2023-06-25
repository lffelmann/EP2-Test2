import java.util.NoSuchElementException;

// 'NonEmpty' repräsentiert eine aufsteigend iterierbare Menge von aufeinander folgenden ganzen Zahlen,
// die mindestens eine Zahl enthält. 'NonEmpty' wird durch Untergrenze 'lower' und Obergenze 'upper'
// des Intervalls bestimmt.
class NonEmpty implements Interval {

    private int lower, upper;

    public NonEmpty(Integer lower, Integer upper) {
        if (upper < lower)
            throw new IllegalArgumentException("lower (" + lower + ") must not be greater than upper (" + upper + ")!");
        this.lower = lower;
        this.upper = upper;
    }

    public String toString() {
        return "[" + lower + ", " + upper + "]";
    }

    @Override
    public boolean contains(Integer n) {
        if (n <= upper && n >= lower)
            return true;
        return false;
    }

    @Override
    public boolean containsAll(Interval iv) {
        if (iv.isEmpty())
            return true;
        NonEmpty Iv = (NonEmpty) iv;
        if (lower <= Iv.lower && upper >= Iv.upper)
            return true;
        return false;
    }

    @Override
    public Interval intersect(Interval iv) {
        if (iv.isEmpty())
            return Empty.EMPTY;
        NonEmpty Iv = (NonEmpty) iv;
        if (lower > Iv.upper || upper < Iv.lower)
            return Empty.EMPTY;
        return new NonEmpty(Math.max(lower, Iv.lower), Math.min(upper, Iv.upper));
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj.getClass() != NonEmpty.class)
            return false;
        NonEmpty Obj = (NonEmpty) obj;
        return lower == Obj.lower && upper == Obj.upper;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int count = lower;
            @Override
            public boolean hasNext() {
                return count <= upper;
            }

            @Override
            public Integer next() {
                if (!hasNext())
                    throw new NoSuchElementException("No more elements.");
                return count++;
            }
        };
    }

}
