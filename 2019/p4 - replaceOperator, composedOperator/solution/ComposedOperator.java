import java.util.*;

// This class implements 'StringOperator' and represents a composition of two 'StringOperator' objects that
// are applied in succession (both can be 'ComposedOperator' objects themselves).
// Calling this object's 'apply' method returns the result of applying the second 'StringOperator'
// on the result of applying the first one on the specified string.
// 'ComposedOperator' objects can be build using the 'andThen' method specified in the interface 'StringOperator'.
//
public class ComposedOperator implements StringOperator {

    private StringOperator operator1, operator2;
    public ComposedOperator(StringOperator operator1, StringOperator operator2) {
        this.operator1 = operator1;
        this.operator2 = operator2;
    }

    @Override
    public String apply(String operand) {
        return operator2.apply(operator1.apply(operand));
    }

    @Override
    public StringOperator andThen(StringOperator after) {
        return new ComposedOperator(this, after);
    }

    @Override
    public Iterator<StringOperator> iterator() {
        Iterator<StringOperator> oper1Iter = operator1.iterator();
        Iterator<StringOperator> oper2Iter = operator2.iterator();

        return new Iterator<StringOperator>() {
            @Override
            public boolean hasNext() {
                return oper1Iter.hasNext() || oper2Iter.hasNext();
            }

            @Override
            public StringOperator next() {
                if (oper1Iter.hasNext())
                    return oper1Iter.next();
                if (!oper2Iter.hasNext())
                    throw new NoSuchElementException("no operator!");
                return oper2Iter.next();
            }
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != ComposedOperator.class)
            return false;
        ComposedOperator Obj = (ComposedOperator) obj;
        if (operator1.equals(Obj.getOperator1()) && operator2.equals(Obj.getOperator2()))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        String buffer = "";
        boolean check = false;
        for (StringOperator s : this) {
            if (!check)
                buffer += s.toString();
            check = true;
            String[] lines = s.toString().split("\n");
            for (String line : lines) {
                buffer += " and then " + line;
            }
        }
        return buffer;
    }

    public StringOperator getOperator1() {
        return operator1;
    }

    public StringOperator getOperator2() {
        return operator2;
    }
}

