import java.util.ArrayList;
import java.util.NoSuchElementException;

// A directory of file system objects. The iterator iterates over all
// direct entries of this directory (no recursive listing of the content of subdirectories).
// The size of a directory corresponds to the sum of the sizes of all entries.
//
public class Directory implements FileObject, FileObjectIterable {
    private String name;
    private ArrayList<FileObject> list;

    public Directory(String name) {
        this.name = name;
        list = new ArrayList<>();
    }

    // Adds 'fso' to this directory, if it does not already have
    // an entry equal to 'fso'. Returns 'true' if 'this' was changed as
    // a result of the call and else otherwise.
    public boolean add(FileObject fso) {
        if (fso.getSize() == 0)
            return false;
        for (FileObject f : list) {
            if (f.equals(fso))
                return false;
        }
        list.add(fso);
        return true;
    }

    // Returns a string with the recursive directory tree, i.e., listing its name and size, each
    // entry with name and size in a separate line and recursively all
    // subdirectories.
    // An indent is used to visualize the tree structure.
    // Example:
    // Documents 217
    //     Description.txt 120
    //     Programs 78
    //         Bomb.sh 13
    //         Hello.java 65
    //     Test.md 21
    public String toString() {
        String returner = name + " " + getSize();
        for (FileObject f: list) {
            String[] lines = f.toString().split("\n");
            for (String line : lines) {
                returner += "\n\t" + line;
            }
        }
        return returner;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int temp = 0;
        for (FileObject f : list)
            temp += f.getSize();
        return temp;
    }


    @Override
    public FileObjectIterator iterator() {
        return new FileObjectIterator() {
            private int length = list.size();
            private int count = 0;
            @Override
            public boolean hasNext() {
                return count < length;
            }

            @Override
            public FileObject next() {
                if (!hasNext())
                    throw new NoSuchElementException("Directory has no more entries!");
                return list.get(count++);
            }
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Directory.class)
            return false;
        Directory Obj = (Directory) obj;
        if (!name.equals(Obj.getName()))
            return false;
        for (FileObject f : list) {
            boolean found = false;
            for (FileObject z : Obj.list) {
                if (z.equals(f)) {
                    found = true;
                    break;
                }
            }
            if (!found)
                return false;
        }
        return true;
    }
}

