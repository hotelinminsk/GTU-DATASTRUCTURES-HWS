
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    public static int menu(){
        System.out.println("===== File System Management Menu ====");
        System.out.println("1. Change directory");
        System.out.println("2. List contents");
        System.out.println("3. Create file/directory");
        System.out.println("4. Delete file/directory");
        System.out.println("5. Move file/directory");
        System.out.println("6. Search file/directory");
        System.out.println("7. Print directory tree");
        System.out.println("8. Sort contents by date created");
        System.out.println("9. Exit");

        System.out.print("Please select an option  :  ");
        return sc.nextInt();

    }
    public static void main(String[] args) {

        FileSystem fs = new FileSystem(sc);
        int choice = 0;
        do {
            choice = menu();
            switch(choice){
                case 1:
                    String pathQuery = "";
                    System.out.print("Enter the path to change to : ");
                    pathQuery = sc.next();
                    fs.changeDirectory(pathQuery);
                    break;
                case 2:
                    fs.listContents();
                    break;
                case 3:
                    fs.createFileOrDirectory();
                    break;
                case 4:
                    fs.deleteFileOrDirectory();
                    break;
                case 5:
                    fs.moveFile();
                    break;
                case 6:
                    fs.searchForQuery();
                    break;
                case 7:
                    fs.printTree();
                    break;
                case 8:
                    fs.sortContents();
                    break;
                case 9:
                    System.out.println("Exiting...");

                    break;
                default:
                    System.out.println("Invalid option selected.");
                    break;
            }
        }while(choice != 9);

        System.exit(0);

    }
}