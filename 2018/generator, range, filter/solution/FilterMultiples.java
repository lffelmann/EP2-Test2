import java.util.NoSuchElementException;

// Ein 'FilterMultiples'-Objekt erzeugt aus einer Datenquelle 'source' eine gefilterte Zahlenfolge.
// Alle Zahlen, die durch 'div' teilbar sind, sind nicht Teil der gefilterten Folge.
// Alle übrigen Zahlen von 'source' sind Teil der gefilterten Folge.
// hasNext() und next() beziehen sich auf die gefilterte Folge.
class FilterMultiples implements FilteredGenerator {

    private Generator source;
    private int div, storedNum;
    private boolean nextExist;

    public FilterMultiples(Generator source, int div) {
        this.source = source;
        this.div = div;
    }

    @Override
    public boolean pass(int i) {
        return i % div != 0;
    }

    @Override
    public FilteredGenerator copy() {
        return new FilterMultiples(source.copy(), div);
    }

    @Override
    public boolean hasNext() {
        while (source.hasNext() && !nextExist) {
            storedNum = source.next();
            if (pass(storedNum)) {
                nextExist = true;
            }
        }
        return nextExist;
    }

    @Override
    public Integer next() {
        if (!hasNext())
            throw new NoSuchElementException("FilterMultiples has no more numbers!");
        nextExist = false;
        return storedNum;
    }
}