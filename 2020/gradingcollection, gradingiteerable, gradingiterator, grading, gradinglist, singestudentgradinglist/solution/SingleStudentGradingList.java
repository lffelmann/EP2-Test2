import java.util.*;

// This class implements 'GradingCollection' and represents a list of gradings of a single student, i.e.,
// all gradings in this list must have an equal 'matriculationID'.
// The entries are in order in which they have been added.
// (The most recently added element is the last element of the list.)
// Iterator and 'toString' follow that order.
public class SingleStudentGradingList implements GradingCollection {

    private int matriculationID;
    private GradingList list;

    // Initializes this list with the 'matriculationID' of the student.
    public SingleStudentGradingList(int matriculationID) {
        this.matriculationID = matriculationID;
        list = new GradingList();
    }

    // Ensures that this list contains the specified 'grading', if the grading belongs to the student of this list.
    // If this list already contains the specified 'grading' or the grading does not belong to this
    // student, this list is not changed.
    public boolean add(Grading grading) {
        if (matriculationID != grading.getMatriculationID())
            return false;
        for (Grading g : list) {
            if (g.equals(grading))
                return false;
        }
        list.add(grading);
        return true;
    }

    // If the specified 'matriculationID' is the same as that of this list, a copy of this list is returned,
    // otherwise an empty list is returned (with the specified 'matriculationID').
    public SingleStudentGradingList filter(int matriculationID) {
        if (this.matriculationID == matriculationID)
            return this;
        return new SingleStudentGradingList(matriculationID);
    }

    @Override
    // Returns a representation with the matriculationID of the student and all the gradings.
    public String toString() {
        return list.toString();
    }

    @Override
    public GradingIterator iterator() {
        return list.iterator();
    }

    public int getMatriculationID() {
        return matriculationID;
    }
}

