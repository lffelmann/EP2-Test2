import java.util.*;

// This class implements 'StringOperator' and represents a simple replacement operation on a string.
// It replaces the all occurrences of the specified 'oldChar' with 'newChar'.
// The iterator of this class iterates over only one element and therefore behaves as follows:
// The 'next' method returns 'this', if it is called for the first time.
// 'hasNext' is 'true' only if 'next' has not been called.
//
public class ReplaceOperator implements StringOperator {

    private char oldChar, newChar;

    // Constructor of the 'ReplaceOperator'.
    public ReplaceOperator(char oldChar, char newChar) {
        this.oldChar = oldChar;
        this.newChar = newChar;
    }

    @Override
    public String apply(String operand) {
        return operand.replace(oldChar, newChar);
    }

    @Override
    public StringOperator andThen(StringOperator after) {
        return new ComposedOperator(this, after);
    }

    @Override
    public Iterator<StringOperator> iterator() {
        ReplaceOperator th = this;
        return new Iterator<StringOperator>() {
            private boolean visited = false;
            @Override
            public boolean hasNext() {
                return !visited;
            }

            @Override
            public StringOperator next() {
                if (!hasNext())
                    throw new NoSuchElementException("no operator!");
                visited = true;
                return th;
            }
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != ReplaceOperator.class)
            return false;
        ReplaceOperator Obj = (ReplaceOperator) obj;
        if (newChar == Obj.getNewChar() && oldChar == Obj.getOldChar())
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "replace '" + oldChar + "' with '" + newChar + "'";
    }

    public char getNewChar() {
        return newChar;
    }

    public char getOldChar() {
        return oldChar;
    }
}

