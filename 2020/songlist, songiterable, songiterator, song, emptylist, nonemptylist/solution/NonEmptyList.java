import java.util.List;

// A song list with at least one entry. This song list is never empty.
//
public class NonEmptyList implements SongList {

    //TODO: define missing parts of the class.

    private Song song;
    private SongList next;

    // Initialises 'this'.
    // Precondition: song != null.
    public NonEmptyList(Song song) {
        this.song = song;
        next = EmptyList.EMPTY;
    }

    public NonEmptyList(Song song, SongList next) {
        this.song = song;
        this.next = next;
    }

    @Override
    public Song first() {
        return song;
    }

    @Override
    public SongList afterFirst() {
        return next;
    }

    @Override
    public SongList add(Song song) {
        return new NonEmptyList(song, this);
    }

    @Override
    public SongList remove(Song song) {
        SongList list = EmptyList.EMPTY;
        for (Song s : this) {
            if (!s.equals(song))
                list.add(s);
        }
        return list;
    }

    @Override
    public SongIterator iterator() {
        NonEmptyList listStart = this;
        return new SongIterator() {
            private SongList list = listStart;
            @Override
            public boolean hasNext() {
                return list.getClass() == NonEmptyList.class;
            }

            @Override
            public Song next() {
                NonEmptyList temp = (NonEmptyList) list;
                Song returner = temp.first();
                list = temp.afterFirst();
                return returner;
            }
        };
    }

    @Override
    public String toString() {
        String returner = "";
        for (Song s : this) {
            returner += s.getName() + ", " + s.getDuration() + "\n";
        }
        return returner;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != NonEmptyList.class)
            return false;
        SongList Obj = (SongList) obj;
        for (Song s : this) {
            if (!s.equals(Obj.first()))
                return false;
            Obj = Obj.afterFirst();
        }
        return true;
    }
}
