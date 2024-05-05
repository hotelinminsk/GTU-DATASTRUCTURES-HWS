
import jdk.jshell.spi.ExecutionControl;

import java.util.*;

/**
 * FileSystem class which represents the file system
 * it has these methods :
 * - changeDirectory : changes the current directory
 * - listContents : lists the contents of the current directory
 * - createFileOrDirectory : creates a file or a directory
 * - deleteFileOrDirectory : deletes a file or a directory
 * - moveFile : moves a file or a directory to a new path
 * - searchForQuery : searches the system with given name query, if found prints the path to the searched file/directory
 * - printTree : prints the directory tree starting from root ending on the current directory
 * - sortContents : sorts the contents of the current directory by date created
 * - currentPath : returns the current path of the file system
 * - FileSystem : constructor for the FileSystem class
 *
 * ----PRIVATE METHODS
 * - tokenizePath : private method to tokenize a given path by '/' characters
 * - pathvalid : private method to check if a given path is valid
 * - moveHelper : private method to move a file or directory to a new path
 * - helperDirDeleter : private method to delete a directory and all its children recursively
 *
 *
 *
 */
public class FileSystem {
    /**
     * private instance variables for the FileSystem class
     */
    private String currentPath;
    private Directory currentDirectory;

    private Directory root;
    private Scanner sc;

    /**
     * public method to return the current path of the file system
     *
     * @return String returns the current path of the file system
     */
    public String currentPath(){
        return currentPath;
    }

    /**
     * private methood to tokenize a given path by '/' characters
     *
     * @param path path to tokenize
     * @return retuns a String array of the tokens
     */
    private String[] tokenizePath(String path){

        try{
            if(path.charAt(0) == '/'){
                path = path.substring(1);
            }
            if(path.charAt(path.length()-1) == '/'){
                path = path.substring(0, path.length()-1);
            }
            return path.split("/");
        }catch(Exception e){
            throw new IllegalArgumentException("Invalid path");
        }

    }

    /**
     * Constructor for FileSystem class which initializes the root directory and the current directory
     */
    public FileSystem(Scanner sk){
        sc = sk;
        root = new Directory("root", null);
        currentDirectory = root;
        currentPath = "root/";
    }

    /**
     * Method to change the current directory to given path if the path is valid
     * @param pathQuery String path parameter starting from root directory
     * @return boolean returns true if the path is valid and the directory is changed successfully, false otherwise
     */
    public boolean changeDirectory(String pathQuery){
        String[] tokens = null;
        if(pathvalid(pathQuery)) {
            tokens = tokenizePath(pathQuery);
            currentDirectory = root;
            currentPath = "root/";
        }else{
            System.err.println("Invalid path.");
            return false;
        }




        for(var token : tokens){
            if(token.equals("root")){
                continue;
            }
            if(token.equals("..")){
                if(currentDirectory.getParent() != null){
                    currentDirectory = (Directory) currentDirectory.getParent();
                    int lastslash = currentPath.lastIndexOf("/");
                    if(lastslash > 0){
                        currentPath = currentPath.substring(0, lastslash+1);
                    }else{
                        System.out.println("You are already at the root directory.");
                        return false;
                    }
                }else{
                    System.err.println("Cannot go up from root directory");
                    return false;
                }
            }else{
                FileSystemElement child = currentDirectory.search(token);
                if(child instanceof Directory){
                    currentDirectory = (Directory)child;
                    currentPath += token + "/";
                }else{
                    System.out.println(token + " is not a directory.");
                    return false;
                }
            }
        }

        System.out.println("Changed directory to : "+ currentPath);
        return true;
    }
    /**
     * Method to list the contents of the current directory
     */
    public void listContents(){
        System.out.println("Listing contents of " + currentPath);
        for(FileSystemElement child : currentDirectory.getChildren()){
            if(child instanceof Directory){
                System.out.println("* "+child.getName() + "/");
            }else{
                System.out.println("-" + child.getName());
            }

        }
    }
    /**
     * Method to create a file or a directory in the current directory
     */
    public void createFileOrDirectory(){
        System.out.println("Current directory : " + currentPath);
        char choice = ' ';
        boolean invalid = true;

        do {
            System.out.println("Do you want to create a file or a directory? (f/d) : ");
            choice = sc.next().charAt(0);
            if (choice == 'f' || choice == 'd') {
                invalid = false;
            } else {
                System.out.println("Invalid choice. Please enter f or d.");
                invalid = true;
            }
        }while(invalid);

        FileSystemElement newElement = null;

        if(choice == 'f'){
            System.out.println("Enter the name of the new file : ");
            String name = sc.next();
            newElement = new File(name, currentDirectory);
            currentDirectory.add(newElement);
        }else{
            System.out.println("Enter the name of the new directory : ");
            String name = sc.next();
            newElement = new Directory(name, currentDirectory);
            currentDirectory.add(newElement);
        }
        String result = choice == 'f' ? ((File)newElement).getName() : ((Directory)newElement).getName() + "/";
        System.out.println(result + " created.");
    }
    /**
     * A private helper  method to delete a directory and all its contents recursively
     */
    private boolean helperDirDeleter(Directory dir){
        for(FileSystemElement child : dir.getChildren()){
            if(child instanceof Directory){
                if(!helperDirDeleter((Directory)child)){
                    return false;
                }
            }
            dir.remove(child);
        }
        return true;
    }

    /**
     * Method to delete a file or a directory(with helperDirDeleter method) from the current directory
     */
    public void deleteFileOrDirectory(){
        System.out.println("Current directory : " + currentPath);
        listContents();
        System.out.println("Enter the name of the file or directory to delete : ");

        String name = sc.next();

        for(FileSystemElement child : currentDirectory.getChildren()){
            if(child.getName().equals(name)){
                if(child instanceof Directory){
                    boolean isDeleted = helperDirDeleter((Directory)child);
                    if(isDeleted){
                        currentDirectory.remove(child);
                        System.out.println(name + " deleted.");
                    }else{
                        System.err.println("Error deleting " + name);
                    }

                }else{
                    currentDirectory.remove(child);
                    System.out.println(name + " deleted.");
                }
            }
        }
    }

    /**
     * Private pathvalid method to check if a given path is valid or not, returns true if the path is valid, false otherwise
     * @param path String path to check
     * @return boolean returns true if the path is valid, false otherwise
     */
    private boolean pathvalid(String path ){
        String[] tokens = tokenizePath(path);
        Directory temp = root;

        for(String token : tokens){
            if(token.equals("root")){
                continue;
            }
            FileSystemElement child = temp.search(token);
            if(child == null){
                return false;
            }
            if(child instanceof Directory){
                temp = (Directory)child;
            }else if(child instanceof File) {
                return false;
            }
            else{
                return false;
            }
        }

        return true;
    }

    /**
     * Private moveHelper method to move a file or a directory to a new path
     * @param path Path to destination directory
     * @return Directory returns the destination directorys reference if path is valid and the directory is found, null otherwise
     */
    private Directory moveHelper(String path){

        String[] tokens = tokenizePath(path);
        Directory temp = root;

        for(var token: tokens){
            if(token.equals("root")){
                continue;
            }
            if(temp.search(token) instanceof Directory){
                temp = (Directory)temp.search(token);
            }else{
                return null;
            }

        }


        return temp;
    }
    /**
     * Method to move a file or a directory to a new path
     */
    public void moveFile(){
        System.out.println("Current directory : " + currentPath);
        listContents();
        System.out.println("Enter the name of the file or directory to move : ");
        String name = sc.next();
        FileSystemElement fileToMove = currentDirectory.search(name);

        if(fileToMove == null){
            System.err.println("File or directory not found.");
            return;
        }else{
            System.out.println("Enter the path to move to: ");
            String path = sc.next();
            if(pathvalid(path)){
                Directory destination = moveHelper(path);
                currentDirectory.remove(fileToMove);
                destination.add(fileToMove);
                fileToMove.changeParent(destination);
                System.out.println(name + " moved to " + path + " successfully.");
            }else{
                System.err.println("Invalid path.");
            }
        }

    }
    /**
     * Private helperSearch method to search the file system starting from root with given string query
     * if searched query is found, returns true to its caller which is searchForQuery method false otherwise
     * @param dir Directory to start the search from
     * @param query String query to search for
     * @param path LinkedList to store the path to the searched file/directory
     * @return boolean returns true if the query is found, false otherwise
     */
    private boolean helperSearch(Directory dir, String query, LinkedList<String> path){
        for(FileSystemElement child : dir.getChildren()){
            if(child.getName().equals(query)){
                //path.add(child.getName());
                return true;
            }else if(child instanceof Directory){
                path.add(child.getName());
                if(helperSearch((Directory)child, query, path)){
                    return true;
                }else{
                    path.removeLast();
                }
            }
        }
        return false;
    }

    /**
     * Method to search the file system(with the use of private helperSearch method) starting from  root with given string query
     * if searched query is found, prints the path to the searched file/directory
     * Sends a linkedlist to the helperSearch method to store the path to the searched file/directory
     */
    public void searchForQuery(){
        boolean invalid = true;
        String query = "";
        do{
            System.out.print("Search query: ");
            try{
                query = sc.next();
                invalid = false;
            }catch (Exception e){
                System.err.println("Invalid query. Try again.");
                invalid=true;
            }
        }while(invalid);

        System.out.println("Searching from root with query: " + query);
        LinkedList<String> pathofSearched = new LinkedList<String>();
        helperSearch(root,query,pathofSearched);
        String newPath = "root/";
        while(!pathofSearched.isEmpty()){
            newPath += pathofSearched.removeFirst() + "/";
        }
        System.out.println("Searched query : " + query + "found at : "+ newPath);

    }

    /**
     * Private helper method to print the directory tree starting from root and ending on the current directory recursively
     * Gets called from public printTree method
     * @param dir Directory to start the tree printing from (root)
     * @param spaces int spaces to print before each directory/file (on root it is 0)
     * @param current Directory current directory to stop the tree printing
     */
    //BURDA KALDIM
    private void helperTree(){
        String[] tokens = tokenizePath(currentPath);

        Directory temp = root;
        int i = 1;

        int spaces = 0;
        while(temp != currentDirectory && i < tokens.length){
            System.out.println(" ".repeat(spaces) + " * " + temp.getName() + "/");
            temp = (Directory) temp.search(tokens[i]);
            i++;
            spaces += 2;
        }

        System.out.println(" ".repeat(spaces) + " * " + temp.getName() + "/ (Current directory)");
        spaces += 2; // Increase spaces for the contents of the current directory
        for(var child : temp.getChildren()){
            if(child instanceof Directory){
                System.out.println(" ".repeat(spaces) + " * " + child.getName() + "/");
            }else{
                System.out.println(" ".repeat(spaces) + " - " + child.getName());
            }
        }

    }

    /**
     * Method to print the directory tree with the use of the private helperTree method, starting from root and ending on the current directory
     */
    public void printTree(){
        helperTree();
    }

    /**
     * Method to sort the contents of the current directory by date created
     */
    private void printSorted(){
        System.out.println("Listing contents of : " + currentPath);
        for(FileSystemElement child : currentDirectory.getChildren()){
            if(child instanceof Directory){
                System.out.println("* " + child.getName() + "/ (" +  child.getDateCreated() + ")");
            }else{
                System.out.println("-" + child.getName() + " (" +child.getDateCreated() + ")");
            }
        }
    }
    public void sortContents(){
        Comparator<FileSystemElement> compare = (FileSystemElement a, FileSystemElement b) ->{
            return a.getDateCreated().compareTo(b.getDateCreated());
        };
        Collections.sort(currentDirectory.getChildren(),compare);
        System.out.println("Contents sorted by date created.");

        printSorted();
    }

}
