import java.util.NoSuchElementException;

// This class represents a single file. The size of a file corresponds
// to the length of its content.
//
public class File implements FileObject {
    private String name;
    private String content;

    // Constructor: initializes this file with a name and its content.
    public File(String name, String content) {
        this.name = name;
        this.content = content;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return content.length();
    }

    @Override
    public String toString() {
        return "" + name + " " + getSize();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != File.class)
            return false;
        File Obj = (File) obj;
        if (name.equals(Obj.getName()))
            return true;
        return false;
    }
}

