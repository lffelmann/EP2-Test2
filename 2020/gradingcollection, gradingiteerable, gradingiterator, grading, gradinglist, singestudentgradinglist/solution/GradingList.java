import java.util.ArrayList;
import java.util.NoSuchElementException;

// This class implements the interface 'GradingCollection' and represents a list of gradings
// in order in which they have been added. (The most recently added element is the last element of the list.)
// Iterator and 'toString' follow that order.
// There are no duplicate entries.
public class GradingList implements GradingCollection {

    private ArrayList<Grading> gradings;

    public GradingList() {
        gradings = new ArrayList<>();
    }

    @Override
    public boolean add(Grading grading) {
        for (Grading g : gradings) {
            if (g.equals(grading))
                return false;
        }
        gradings.add(grading);
        return true;
    }

    @Override
    public SingleStudentGradingList filter(int matriculationID) {
        SingleStudentGradingList list = new SingleStudentGradingList(matriculationID);
        for (Grading g : gradings) {
            if (g.getMatriculationID() == matriculationID)
                list.add(g);
        }
        return list;
    }

    @Override
    public GradingIterator iterator() {
        return new GradingIterator() {
            private int length = gradings.size();
            private int count = 0;

            @Override
            public Grading next() {
                if (!hasNext())
                    throw new NoSuchElementException("Kein Zeugnis verfügbar!");
                return gradings.get(count++);
            }

            @Override
            public boolean hasNext() {
                return count < length;
            }
        };
    }

    @Override
    public String toString() {
        String temp = "";
        for (Grading g : gradings) {
            temp += g.toString() + "\n";
        }
        return temp.substring(0, temp.length() - 1);
    }
}

