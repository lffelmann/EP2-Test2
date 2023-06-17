import java.util.NoSuchElementException;

// A 'BalancedStick' has a specified stick weight, that can not be changed after
// initialisation. On the left and right end of the stick mobiles
// are attached (recursive structure). 'BalancedStick' implements 'Mobile'.
// You can assume that no part of a mobile has the same identity as another part.
//
public class BalancedStick implements Mobile {

    private int stickWeight;
    private Mobile left;
    private Mobile right;

    private Collection collection;

    // Initialises 'this'; throws an 'UnbalancedException' if the resulting mobile
    // would not be balanced, i.e. if left.getWeight() != right.getWeight(). The detail message
    // of the exception contains information about the difference between left and right weight,
    // for example "Stick unbalanced (left 7 - right 16)" (see example in 'PraxisTest2.java').
    // Preconditions: stickWeight > 0, left != null, right != null, left != right,
    // no part of a mobile has the same identity as another part.
    public BalancedStick(int stickWeight, Mobile left, Mobile right) throws UnbalancedException {
        if (right.getWeight() != left.getWeight())
            throw new UnbalancedException("Stick unbalanced (left " + left.getWeight() + " - right " + right.getWeight() + ")");
        this.stickWeight = stickWeight;
        this.left = left;
        this.right = right;
        collection = new Collection();
        collection.update(this);
    }

    // Replaces the mobile equal to 'toReplace' with a new mobile 'replaceWith' and
    // returns 'true' if such a mobile is contained as part of this mobile, i.e., attached to this
    // stick or below (recursive search). Otherwise, the call of this method has no effect and
    // 'false' is returned.
    // Throws an 'UnbalancedException' if the replacement would violate the
    // conditions that all sticks need to be balanced. The detail message
    // of the exception contains information about the difference between left and right weight.
    // Precondition: toReplace != null && replaceWith != null
    public boolean replace(Mobile toReplace, Mobile replaceWith) throws UnbalancedException {
        if (toReplace.getWeight() != replaceWith.getWeight())
            return false;
        if (this.equals(toReplace)) {
            BalancedStick ToReplace = (BalancedStick) toReplace;
            stickWeight = ToReplace.getStickWeight();
            left = ToReplace.getLeft();
            right = ToReplace.getRight();
            collection.update(this);
            return true;
        }
        return replacer(toReplace, replaceWith, this);
    }

    private boolean replacer(Mobile toReplace, Mobile replaceWidth, BalancedStick mobile) throws UnbalancedException {
        if (mobile.getLeft().equals(toReplace)) {
            Mobile temp = mobile.getLeft();
            mobile.setLeft(replaceWidth);
            if (this.left.getWeight() != this.right.getWeight()) {
                int leftW = this.left.getWeight();
                int rightW = this.right.getWeight();
                mobile.setLeft(temp);
                throw new UnbalancedException("Stick unbalanced (left " + leftW + " - right " + rightW + ")");
            }
            collection.update(this);
            return true;
        }
        if (mobile.getLeft().getClass() != Star.class)
            replacer(toReplace, replaceWidth, (BalancedStick) mobile.getLeft());
        if (mobile.getRight().equals(toReplace)) {
            Mobile temp = mobile.getRight();
            mobile.setRight(replaceWidth);
            if (this.left.getWeight() != this.right.getWeight()) {
                int leftW = this.left.getWeight();
                int rightW = this.right.getWeight();
                mobile.setRight(temp);
                throw new UnbalancedException("Stick unbalanced (left " + leftW + " - right " + rightW + ")");
            }
            collection.update(this);
            return true;
        }
        if (mobile.getRight().getClass() != Star.class)
            replacer(toReplace, replaceWidth, (BalancedStick) mobile.getRight());
        return false;
    }

    @Override
    public int getWeight() {
        int[] weight = {0};
        weightGetter(this, weight);
        return weight[0];
    }

    private void weightGetter(Mobile m, int[] weight) {
        if (m.getClass() == Star.class) {
            weight[0] += m.getWeight();
            return;
        }
        BalancedStick M = (BalancedStick) m;
        weight[0] += M.stickWeight;
        weightGetter(M.getLeft(), weight);
        weightGetter(M.getRight(), weight);
    }

    @Override
    // Two sticks are equal if
    // 1.) they have the same stick weight and
    // 2.) if the left part of 'this' equals the left part of 'o' and the right part of 'this'
    //       equals the right part of 'o'
    //     or
    //     if the right part of 'this' equals the left part of 'o' and the left part of 'this'
    //       equals the right part of 'o' (i.e., exchanging left and right part does not
    //       change the value returned by 'equals').
    //
    // For example, all three of the following mobiles are equal (provided that corresponding
    // objects of Star are equal):
    //
    //          |                      |                |
    //      +---2---+              +---2---+        +---2---+
    //      |       |              |       |        |       |
    //   +--2--+    *           +--2--+    *        *    +--2--+
    //   |     |    16          |     |    16       16   |     |
    //   *  +--1--+          +--1--+  *               +--1--+  *
    //   7  |     |          |     |  7               |     |  7
    //      *     *          *     *                  *     *
    //      3     3          3     3                  3     3
    //
    public boolean equals(Object o) {
        if (o.getClass() != BalancedStick.class)
            return false;
        BalancedStick O = (BalancedStick) o;
        if (this.stickWeight != O.getStickWeight())
            return false;
        return (O.getRight().equals(this.right) && O.getLeft().equals(this.left)) || (O.getLeft().equals(this.right) && O.getRight().equals(this.left));
    }

    @Override
    public StarCollection getStarCollection() {
        return collection;
    }

    @Override
    public StarIterator iterator() {
        StarIterator leftIter = left.iterator();
        StarIterator rightIter = right.iterator();
        return new StarIterator() {
            @Override
            public boolean hasNext() {
                return leftIter.hasNext() || rightIter.hasNext();
            }

            @Override
            public Star next() {
                if (leftIter.hasNext())
                    return leftIter.next();
                if (!rightIter.hasNext())
                    throw new NoSuchElementException("no star element!");
                return rightIter.next();
            }
        };
    }

    public Mobile getLeft() {
        return left;
    }

    public Mobile getRight() {
        return right;
    }

    public void setLeft(Mobile left) {
        this.left = left;
    }

    public void setRight(Mobile right) {
        this.right = right;
    }

    public int getStickWeight() {
        return stickWeight;
    }

    @Override
    public String toString() {
        return "(" + this.getLeft().toString() + ")-" + this.getStickWeight() + "-(" + this.getRight().toString() + ")";
    }
}