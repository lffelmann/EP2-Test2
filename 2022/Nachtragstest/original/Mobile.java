// A mobile is a hanging room decoration consisting of sticks with a specified weight on which
// further mobiles are attached (recursive structure). The actual decorations (e.g. stars with
// a specified weight) are the leaf nodes.
//
//                     |
//              +------5---+----+
//              |          |    |
//        +-----2--+--+    *    *
//        |        |  |    12   5
//        |        |  *
//        *        |  2
//        7        |
//              +--1--+
//              |     |
//              *     *
//              3     3
//
// Readable representation as string: 5[2[*7, 1[*3, *3], *2], *12, *5]
// Please, do not change this interface definition!
//
public interface Mobile extends StarIterable {

    // Returns the total weight of this mobile (including all sticks and stars).
    int getWeight();

    // Returns a readable representation of the mobile, showing its complete
    // structure with all weights using an expression with brackets.
    // Example (compare with the graphical representation above):
    // 5[2[*7, 1[*3, *3], *2], *12, *5]
    String toString();

    // Returns 'true' if 'this' and 'o' are equal.
    boolean equals(Object o);

    // Returns the hash code of 'this'.
    int hashCode();

    // Returns a 'StarCollection' view of 'this', containing all stars of this mobile.
    // Later changes in 'this' will be reflected in the returned view.
    StarCollection getStarCollection();

    boolean replace(Mobile toReplace, Mobile replaceWith)throws StickBreaksException;

}

