import java.util.NoSuchElementException;

// Eine Summe mit linkem und rechtem Summanden. Der Iterator iteriert über alle Zahlen des Ausdrucks von links nach rechts.
class Sum implements Expression {

    private Expression left;
    private Expression right;

    // Konstruktor
    public Sum(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Sum add(Expression exp) {
        return new Sum(this, exp);
    }

    public int eval() {
        int sum = 0;
        for (int i : this)
            sum += i;
        return sum;
    }

    public Iterator iterator() {
        Iterator leftIter = left.iterator();
        Iterator rightIter = right.iterator();
        return new Iterator() {
            @Override
            public boolean hasNext() {
                return leftIter.hasNext() || rightIter.hasNext();
            }

            @Override
            public Integer next() {
                if (leftIter.hasNext())
                    return leftIter.next();
                if (!rightIter.hasNext())
                    throw new NoSuchElementException("No more numbers!");
                return rightIter.next();
            }
        };
    }

    public String toString() {
        String rightString = right.toString();
        boolean neg = rightString.charAt(0) == '-';
        return left.toString() + (neg ? rightString : "+" + rightString);
    }
}
