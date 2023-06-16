package Gruppe1600;

// This class represents a set that is composed of multiple disjoint intervals.
// (This means these subsets have no intersection.)
// 'NonContinuousSet' is subtype of 'NumberSet' and 'DoubleIterable'.
//
// TODO: Implementation of this class.
//  You can use the Java-Collection framework in your implementation.
//
public class NonContinuousSet {

    // TODO: Define missing parts of the class.
    //  Further methods can be added if necessary (but no setters or
    //  getters that return or set just the value of a variable).

    // Initializes 'this' with its continuous subsets (intervals).
    // Precondition: the intervals specified by 'sets' do not intersect,
    // sets != null && sets.length > 1
    public NonContinuousSet(Interval... sets) {
        // TODO: implement constructor.
    }

    // Returns the hull of this set as an 'Gruppe1600.ContinuousSet' view. The hull is the enclosing
    // continuous set with 'this.min()' as lower bound and 'this.max()' as upper bound.
    // Later changes in 'this' will be reflected in the returned view.
    public ContinuousSet getHull() {
        // TODO: implement method.
        return null;
    }

    // Adds 'interval' to this set.
    // Precondition: interval != null and 'interval' has no intersection with 'this'.
    public void add(Interval interval) {
        // TODO: implement method.
    }


    // Returns an iterator over all lower and upper bounds of the subsets of 'this' an ascending
    // order. For example if the set has three subsets as in [[-1.2, 5.0], [7.33, 12.5], [21.1,
    // 28.7]] then the iteration is over the elements -1.2, 5.0, 7.33, 12.5, 21.1, 28.7.
    public DoubleIterator iterator() {
        // TODO: implement method.
        return null;
    }

    @Override
    // Returns a representation of 'this' with all the bounds of the interval(s) of 'this'
    // sorted ascending. The exact format is shown on the following example representing a set
    // with three intervals:
    // [[-1.2, 5.0], [7.33, 12.5], [21.1, 28.7]]
    public String toString() {
        // TODO: implement method.
        return "";
    }

}

//TODO: Define additional class(es) if needed (either here or in a separate file).