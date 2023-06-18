import java.util.NoSuchElementException;

// A 'Stick' has a specified stick weight, that can not be changed after
// initialisation. Mobiles can be attached to the stick (recursive structure).
// 'Stick' implements 'Mobile'.
// You can assume that no part of a mobile has the same identity as another part.
//
public class Stick implements Mobile {

    private int stickWeight;
    private Mobile[] attached;

    private Countable countable;

    // Initialises 'this'; throws an 'StickBreaksException' if the sum of the weight of
    // all mobiles in the array 'attached' is greater than 10 times the 'stickWeight'.
    // The detail message of the exception is "Stick breaks!".
    // Precondition: attached.length > 0 and for any two mobiles m1 and m2 being part of
    // 'attached' (including sub-mobiles) it holds that m1.equals(m2) == false, this is,
    // that there are no duplicates anywhere in a mobile.
    public Stick(int stickWeight, Mobile[] attached) throws StickBreaksException {
        int weight = 0;
        for (Mobile m : attached) {
            weight += m.getWeight();
        }
        if (stickWeight * 10 < weight)
            throw new StickBreaksException("Stick breaks!");
        this.stickWeight = stickWeight;
        this.attached = attached;
    }

    // Replaces the mobile equal to 'toReplace' with a new mobile 'replaceWith' if this mobile
    // is directly attached to this stick (no recursive search). In this case 'true' is returned.
    // Otherwise, the call of this method has no effect and 'false' is returned.
    // Throws a 'StickBreaksException' if the replacement would violate the
    // condition that the sum of the weight of all attached mobiles has to be
    // less than or equal to 10 times its stick weight.
    // Precondition: toReplace != null && replaceWith != null
    public boolean replace(Mobile toReplace, Mobile replaceWith) throws StickBreaksException {
        for (int i = 0; i < attached.length; i++) {
            if (attached[i].equals(toReplace)) {
                Mobile temp = attached[i];
                attached[i] = replaceWith;

                int weight = 0;
                for (Mobile m : attached) {
                    weight += m.getWeight();
                }
                if (stickWeight * 10 < weight) {
                    attached[i] = temp;
                    throw new StickBreaksException("Stick breaks!");
                }
                countable = getStickCountable();
                return true;
            }
        }
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
        Stick M = (Stick) m;
        weight[0] += M.stickWeight;
        for (Mobile e : M.attached) {
            weightGetter(e, weight);
        }
    }

    @Override
    // Returns 'true' if 'o' is also of class 'Stick', has an equal stick weight, and has equal mobiles
    // attached, regardless of their order. This means that 'this' and 'o' have the same number of mobiles
    // attached and every mobile attached to 'this' is equal to a mobile attached to 'o' (and vice versa).
    // Otherwise, 'false' is returned.
    public boolean equals(Object o) {
        if (o.getClass() != Stick.class)
            return false;
        Stick O = (Stick) o;
        for (Mobile e : this.attached) {
            boolean found = false;
            for (Mobile x : O.attached) {
                if (e.equals(x)) {
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
    public Countable getStickCountable() {
        int[] count = {0};
        stickCountable(this, count);
        countable = () -> count[0];
        return countable;
    }

    private void stickCountable(Mobile mobile, int[] count) {
        if (mobile.getClass() != Stick.class) {
            return;
        }
        count[0]++;
        Stick mobile1 = (Stick) mobile;
        for (Mobile x : mobile1.attached) {
            stickCountable(x, count);
        }
    }

    @Override
    public StarIterator iterator() {
        StarIterator[] iter = new StarIterator[attached.length];
        for (int i = 0; i < attached.length; i++) {
            iter[i] = attached[i].iterator();
        }
        return new StarIterator() {
            @Override
            public boolean hasNext() {
                for (StarIterator i : iter) {
                    if (i.hasNext())
                        return true;
                }
                return false;
            }

            @Override
            public Star next() {
                for (StarIterator i : iter) {
                    if (i.hasNext())
                        return i.next();
                }
                throw new NoSuchElementException("no star element!");
            }
        };
    }

    @Override
    public String toString() {
        String returner = stickWeight + "[";
        for (Mobile m : attached) {
            returner += m.toString();
        }
        return returner + "]";
    }
}