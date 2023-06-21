import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

// A purely red paint. It is one of the basic colors for mixing other paints.
//
public class Red implements Basic {

    // only one instance is needed.
    public static final Red RED = new Red();

    @Override
    public PaintIterator iterator() {
        Red th = this;
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
        return obj.getClass() == Red.class;
    }

    @Override
    public String toString() {
        return "red";
    }

    @Override
    public int hashCode() {
        return Objects.hashCode("red");
    }
}
