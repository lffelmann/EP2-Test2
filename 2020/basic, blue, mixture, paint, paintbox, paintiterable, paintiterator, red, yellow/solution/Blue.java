import java.util.NoSuchElementException;
import java.util.Objects;

// A purely blue paint. It is one of the basic colors for mixing other paints.
// 'Blue' implements 'Basic'.
//
public class Blue implements Basic {

    // only one instance is needed.
    public static final Blue BLUE = new Blue();

    @Override
    public PaintIterator iterator() {
        Blue th = this;
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
        return new Mixture(Blue.BLUE, paint);
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == Blue.class;
    }

    @Override
    public String toString() {
        return "blue";
    }

    @Override
    public int hashCode() {
        return Objects.hash("blue");
    }
}
