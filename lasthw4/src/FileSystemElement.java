

import java.sql.Timestamp;

abstract class FileSystemElement {
    private String name;
    private Timestamp dateCreated;
    private FileSystemElement parent;

    public FileSystemElement(String n,FileSystemElement parent){
        this.name = n;
        this.parent = parent;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

    public void changeParent(FileSystemElement newParent){
        this.parent = newParent;
    }
    public abstract  boolean equals(FileSystemElement other);

    public String getName(){
        return name;
    }

    public Timestamp getDateCreated(){
        return dateCreated;
    }

    public FileSystemElement getParent(){
        return parent;
    }

}
