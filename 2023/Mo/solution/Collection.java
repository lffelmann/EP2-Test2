import java.util.ArrayList;

public class Collection implements StarCollection {
    private ArrayList<Star> list;

    public Collection() {
        list = new ArrayList<Star>();
    }

    public void add(Star star) {
        list.add(star);
    }

    @Override
    public boolean contains(Star s) {
        for (Star star : list) {
            if (star.equals(s))
                return true;
        }
        return false;
    }

    public void update(BalancedStick stick) {
        list = new ArrayList<Star>();
        for (Star s : stick) {
            list.add(s);
        }
    }
}
