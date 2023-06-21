import java.util.NoSuchElementException;
import java.util.Objects;

// A purely yellow paint. It is one of the basic colors for mixing other paints.
// 'Yellow' implements 'Basic'.
public class Yellow implements Basic {
    // only one instance is needed.
    public static final Yellow YELLOW = new Yellow();

    @Override
    public PaintIterator iterator() {
        Yellow th = this;
        return new PaintIterator() {
            private boolean visited = false;
            @Override
            public boolean hasNext() {
                return !visited;
            }

            @Override
            public Basic next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                visited = true;
                return th;
            }
        };
    }

    @Override
    public Paint mixWith(Paint paint) {
        return new Mixture(Red.RED, paint);
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == Yellow.class;
    }

    @Override
    public String toString() {
        return "yellow";
    }

    @Override
    public int hashCode() {
        return Objects.hashCode("yellow");
    }
}