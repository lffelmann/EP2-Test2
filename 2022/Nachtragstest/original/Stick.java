import java.util.*;

// A 'Stick' has a specified weight, that cannot be changed after
// initialisation. Mobiles can be attached to the stick (recursive structure).
// 'Stick' implements 'Mobile'.
// You can assume that no part of a mobile has the same identity as another part.
//
public class Stick implements Mobile // d: uncomment clause.
{

    //TODO: define missing parts of the class.

    // Initialises 'this'; throws an 'StickBreaksException' if the sum of the weight of
    // all mobiles in 'attached' is greater than 10 times the 'stickWeight'. The detail message
    // of the exception is "Stick breaks!".
    // Precondition: attached.length > 0 and for any two mobiles m1 and m2 being part of
    // 'attached' (including sub-mobiles) it holds that m1.equals(m2) == false, this is,
    // that there are no duplicates anywhere in a mobile.
    public Stick(int stickWeight, Mobile... attached) throws StickBreaksException {

        // TODO: implement constructor.

    }

    // Replaces the mobile equal to 'toReplace' with a new mobile 'replaceWith' if this mobile
    // is directly attached to this stick (no recursive search). In this case 'true' is returned.
    // Otherwise, the call of this method has no effect and 'false' is returned.
    // Throws a 'StickBreaksException' if the replacement would violate the
    // condition that the sum of the weight of all attached mobiles has to be
    // less than or equal to 10 times its stick weight.
    // Precondition: toReplace != null && replaceWith != null
    public boolean replace(Mobile toReplace, Mobile replaceWith) throws StickBreaksException {

        // TODO: implement method.

    }

    @Override
    // Returns 'true' if 'o' is also of class 'Stick', has the same stick weight and
    // has equal mobiles attached in the same order as of 'this'.
    public boolean equals(Object o) {

        // TODO: implement method.
    }


}

// TODO: define additional classes if needed (either here or in a separate file).