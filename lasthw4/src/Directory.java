

import java.util.LinkedList;

/**
 * Our Directory which extends FileSystemElement
 * it has these methods :
 *  - equals (Overriden) checks for two Directory element if they are equal or not
 *  - remove removes a child from the Directory
 *  - add adds a child file to the Directory
 *  - search(String name) searches the current directory for a file with given name
 *  - getChildren() returns the inner linkedlist of directory which has files and directories as contents
 *
 */
public class Directory extends FileSystemElement{
    private LinkedList<FileSystemElement> children;

    /**
     * Constructor for Directory class which intializes the name and parent of the directory
     * @param name name of the directory
     * @param parent parent of the directory
     */
    public Directory(String name, FileSystemElement parent){
        super(name, parent);
        children = new LinkedList<FileSystemElement>();
    }

    /**
     * Overriden equals method which checks if two directories are equal or not
     * @param other the other FileSystemelement to compare
     * @return true if they are equal otherwise false
     */
    public boolean equals(FileSystemElement other){
        if(other instanceof Directory){
            return this.getName().equals(other.getName()) && this.getDateCreated().equals(other.getDateCreated());
        }
        return false;
    }
    /**
     * Prints the contents of the directory
     */
    public void printContents(){
        System.out.println("Listing contents of : "+ getName());
        for(FileSystemElement child : children){
            if(child instanceof Directory){
                System.out.println("* "+child.getName() + "/");
            }else{
                System.out.println(child.getName());
            }
        }
    }

    /**
     * Removes the child from the current directory
     * @param child FileSystemElement object to delete from linkedlist
     */
    public void remove(FileSystemElement child){
        try{
            children.remove(child);
        }catch(Exception e){
            System.err.println("Error when trying to remove object : " + child.getName() + " error = " + e.getMessage());
        }
    }

    /**
     * Adds the child to the current directory
     * @param child FileSystemElement object to add to linkedlist
     */
    public void add(FileSystemElement child){
        children.add(child);
    }

    /**
     * public search method which searches the current directory for given String name
     * @param query name of the file to search
     * @return FileSystemElement object if found otherwise null
     *
     */
    public FileSystemElement search(String query){
        for(FileSystemElement child : children){

            if(child.getName().equals(query)){
                return child;
            }
        }

        return null;
    }

    /**
     * Returns the inner linkedlist of directory object
     * @return LinkedList<FileSystemElement> children of the directory
     */
    public LinkedList<FileSystemElement> getChildren(){
        return children;
    }


}
