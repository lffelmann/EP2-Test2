import java.util.ArrayList;
import java.util.NoSuchElementException;

// A painting color that is a mixture of at least two colors.
// 'Mixture' implements 'Paint'.
//
public class Mixture implements Paint {

    private Paint p1;
    private Paint p2;

    // Initializes this object.
    public Mixture(Paint p1, Paint p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public PaintIterator iterator() {
        PaintIterator p1Iter = p1.iterator();
        PaintIterator p2Iter = p2.iterator();
        return new PaintIterator() {
            @Override
            public boolean hasNext() {
                return p1Iter.hasNext() || p2Iter.hasNext();
            }

            @Override
            public Basic next() {
                if (p1Iter.hasNext())
                    return p1Iter.next();
                if (!p2Iter.hasNext())
                    throw new NoSuchElementException();
                return p2Iter.next();
            }
        };
    }

    @Override
    public Paint mixWith(Paint paint) {
        return new Mixture(this, paint);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Mixture.class)
            return false;
        Mixture Obj = (Mixture) obj;
        for (Paint i : this) {
            boolean found = false;
            for (Paint z : Obj) {
                if (i.equals(z)) {
                    found = true;
                    break;
                }
            }
            if (!found)
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String temp = "";
        for (Paint p : this)
            temp += p.toString() + ", ";
        return temp.substring(0, temp.length() - 2);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (Paint p : this) {
            hash += p.hashCode();
        }
        return hash;
    }
}