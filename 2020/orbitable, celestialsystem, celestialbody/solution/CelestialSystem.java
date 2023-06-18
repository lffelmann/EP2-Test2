import java.util.ArrayList;

// A celestial system implements 'Orbitable' and has at least one object in orbit around its
// central celestial body. Further objects can be added.
//
public class CelestialSystem implements Orbitable {

    //TODO: Define variables.
    //TODO: define missing parts of the class.

    private CelestialBody centralBody;
    private ArrayList<Orbitable> inOrbit;

    // Initialises this system with its central body.
    public CelestialSystem(CelestialBody centralBody, Orbitable inOrbit) {
        this.centralBody = centralBody;
        this.inOrbit = new ArrayList<>();
        this.inOrbit.add(inOrbit);
    }


    @Override
    public OrbitIterator iterator() {
        return new OrbitIterator() {
            private int len = inOrbit.size();
            private int count = 0;

            @Override
            public boolean hasNext() {
                if (count < len)
                    return true;
                return false;
            }

            @Override
            public Orbitable next() {
                return inOrbit.get(count++);
            }
        };
    }

    @Override
    public String getName() {
        return centralBody.getName();
    }

    @Override
    public Orbitable add(Orbitable inOrbit) {
        this.inOrbit.add(inOrbit);
        return this;
    }

    @Override
    public String toString() {
        String returner = centralBody.toString();
        for (Orbitable o : inOrbit) {
            String[] lines = o.toString().split("\n");
            for (String line : lines) {
                returner += "\n\t" + line;
            }
        }
        return returner;
    }
}

