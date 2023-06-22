import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

// The class implements 'Polynomial' and represents the form (a + x*p), where 'a' is an Integer
// and 'p' is another polynomial. 'p' must not be 'null' (objects of 'Constant' shall be used to represent constants).
// This class implements 'Polynomial' by using Horner's scheme.
// Example:
// a₀ + a₁*x + a₂*x² + a₃*x³ + a₄*x⁴
// The polynomial above is represented in Horner's scheme as:
// a₀ + x*(a₁ + x*(a₂ + x*(a₃ + x*a₄)))
//
public class HornerScheme implements Polynomial {

    private int a;
    private Polynomial p;

    // Initializes this object.
    // Precondition: 'p' must not be 'null'.
    public HornerScheme(int a, Polynomial p) {
        this.a = a;
        this.p = p;
    }

    // Creates a polynomial from the coefficients specified by the array coeffs = {a₀, a₁, a₂, ... a𝘥}.
    // Precondition: coeffs != null and coeffs has at least one element.
    public static Polynomial create(int[] coeffs) {
        Polynomial buffer = new Constant(coeffs[coeffs.length - 1]);
        for (int i = coeffs.length - 2; i >= 0; i--) {
            buffer = new HornerScheme(coeffs[i], buffer);
        }
        return buffer;
    }

    @Override
    public int degree() {
        int count = 0;
        for (int i : this)
            count++;
        return count;
    }

    @Override
    public List<Integer> coefficients() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : this) {
            list.add(i);
        }
        return list;
    }

    @Override
    public double eval(double x) {
        return a + x * p.eval(x);
    }

    @Override
    public Iterator<Integer> iterator() {
        Iterator<Integer> iter = p.iterator();
        return new Iterator<Integer>() {
            private boolean visited = false;

            @Override
            public boolean hasNext() {
                return !visited || iter.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext())
                    throw new NoSuchElementException("no coefficient!");
                if (!visited) {
                    visited = true;
                    return a;
                }
                return iter.next();
            }
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (getClass() != obj.getClass())
            return false;
        HornerScheme Obj = (HornerScheme) obj;
        return a == Obj.a && p.equals(Obj.p);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "(" + a + " + x*" + p.toString() + ")";
    }
}



