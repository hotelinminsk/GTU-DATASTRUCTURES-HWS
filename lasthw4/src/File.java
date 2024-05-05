

/**
 * File class which extends the abstract FileSystemElement class
 * Represents a file in the file system
 */
public class File extends  FileSystemElement{

    /**
     * Compares the current file with another FileSystemElement
     * @param other
     * @return true if the two FileSystemElements are equal, false otherwise
     */
    @Override
    public boolean equals(FileSystemElement other){
        if(other instanceof File){
            return this.getName().equals(other.getName()) && this.getDateCreated().equals(other.getDateCreated()) && this.getParent().equals(other.getParent()) && this.getParent().equals(other.getParent());
        }
        return false;
    }
    /**
     * Constructor for File class which initializes the name and parent of the file object by calling its super constructor
     * @param name name of the file
     * @param parent parent of the file
     */
    public File(String name, FileSystemElement parent){
        super(name, parent);
    }
}
