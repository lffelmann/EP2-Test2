// A Song with a name (String) and duration in seconds (int).
//
public class Song {
    private String name;
    private int duration;

    public Song(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Song.class)
            return false;
        Song Obj = (Song) obj;
        if (name.equals(Obj.getName()) && duration == Obj.getDuration())
            return true;
        return false;
    }
}
