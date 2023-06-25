import java.util.NoSuchElementException;

// Diese Klasse repräsentiert eine ganze Zahl. Ihr Iterator liefert nur eine Zahl und ist danach verbraucht.
class Number implements Expression {

    private int num;

    public Number(int num) {
        this.num = num;
    }

    public int eval() {
        return num;
    }

    public Sum add(Expression exp) {
        return new Sum(this, exp);
    }

    public String toString() {
        return "" + num;
    }

    public Iterator iterator() {
        Number th = this;
        return new Iterator() {
            private boolean visited = false;
            @Override
            public boolean hasNext() {
                return !visited;
            }

            @Override
            public Integer next() {
                if (!hasNext())
                    throw new NoSuchElementException("No more numbers!");
                visited = true;
                return th.num;
            }
        };
    }
}
