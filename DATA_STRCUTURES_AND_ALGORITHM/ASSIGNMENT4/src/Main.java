import java.util.Scanner;

public class Main{

    private static FileSystem fs = new FileSystem();

    private static Scanner scanner = new Scanner(System.in);

    private static Directory currentDirectory;

    /**
     * MAIN function
     */
    public static void main(String[] args){
        currentDirectory = fs.getRoot(); //Assume there is a getRoot method to fetch

        while(true){
          // System.out.println("Current Path: " + fs.getCurrentPath(currentDirectory)); // Show current path
            //Menu
            System.out.println();
            System.out.println("===== File Sytem Management Menu =====");
            System.out.println("1. Change Directory");
            System.out.println("2. List directory contents");
            System.out.println("3. Create file/directory");
            System.out.println("4. Delete file/directory ");
            System.out.println("5. Move file/directory");
            System.out.println("6. Search file/directory");
            System.out.println("7. Print directory tree");
            System.out.println("8. Sort contents by date");
            System.out.println("9. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();//consume the newLine left by nextInt

            switch(choice){
                case 1:
                    changeDirectory();
                    break;
                case 2:
                    listContents();
                    break;
                case 3:
                    createFileOrDirectory();
                    break;
                case 4:
                    deleteFileOrDirectory();
                    break;
                case 5:
                    moveElement();
                    break;
                case 6:
                    search();
                    break;
                case 7:
                    printDirectoryTree();
                    break;
                case 8:
                    sortDirectoryByDate();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit( 0);
                    break;
                default:
                    System.out.println("Wrong option.Please try again.");
            }

        }
    }

    /**
     * This function Change directory like cd command
     */
    private static void changeDirectory()
    {
        System.out.println("-----------------------------------------------------------");
        boolean flag=false;
        System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
        System.out.print("Enter new directory path: ");
        String newPath = scanner.nextLine();
        String pathCopy = newPath;

        if (newPath.startsWith("root")) {
            currentDirectory = fs.getRoot(); // setting current directory to root
            newPath = newPath.substring(5); //If newPath starts with root/ , then delete this because if we dont do that, it suppose its new directory 
        }

        // Starting from the root directory
        Directory current = fs.getRoot();

        // Splitting the path according to delimeter '/' 
        String[] directoryNames = newPath.split("/");
        int i=0;
        // each directory in the new path
        for (String directoryName : directoryNames) 
        {
            // Finding the directory which taken from the user
            Directory newDirectory = helpFunctionFindDirectory(current,directoryName); //current root ile başlıyori directoryName'i alıp help functionda varsa eri döndürüyor
                                                                                    //Sonraki if'de null mı diye kontrol ediyor. Null değilde current'a newDirectory atanıyor
            //If directory is existed
            if (newDirectory != null) 
            {
                current = newDirectory; // newDirectory assign to current
                i++; 
            } 
            else 
            {   
                if(pathCopy.equals("root/"))
                {}
                else{
                System.out.println("There is no directory with  " + directoryName + " name.");
                flag=true;
                break;}
            }
        }

        if(flag == false && i !=0){    
            currentDirectory = current; // Assign current to currentDirectory 
            System.out.println("Directory changed to: " + fs.getCurrentPath(currentDirectory)); //Printing the changed message        
        }
        if(i==0 )
        {
             System.out.println("Directory changed to: root/");
        }
      

        System.out.println("-----------------------------------------------------------");
    }

    /**
     * This funciton aim is to list the contents(files/directories) in current path, calling the  fs.listContents(currentDirectory);
     */
    private static void listContents(){
        System.out.println("-----------------------------------------------------------");
        System.out.println("Listing contents of " + fs.getCurrentPath(currentDirectory) + " :");
        fs.listContents(currentDirectory);
        System.out.println("-----------------------------------------------------------");
    }

    /**
     * This funciton aim is to create file or directory
     */
    private static void createFileOrDirectory(){
        System.out.println("-----------------------------------------------------------");
        System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
        System.out.print("Create file or directory (f/d): ");
        String fileOrDirectory = scanner.nextLine();

        if(fileOrDirectory.equals("f")){
            System.out.print("Enter name for new file: ");
            String name = scanner.nextLine();
            fs.createFile(name,currentDirectory);
            System.out.println("File created: " + name);
        }
        else if(fileOrDirectory.equals("d"))
        {
            System.out.print("Enter name for new directory: ");
            String name = scanner.nextLine();
            fs.createDirectory(name,currentDirectory);
            System.out.println("Directory created: " + name + "/");
        }
        System.out.println("-----------------------------------------------------------");   
    }


    /**
     * This funciton aim is to delete file or directory 
     */
    private static void deleteFileOrDirectory(){
        System.out.println("-----------------------------------------------------------");
        System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
        System.out.print("Enter name of file/directory to delete: ");
        String name = scanner.nextLine();

        for (FileSystemElement element : currentDirectory.getChildren()) {
        //When user entry is found, then find the type (file or directory)
        if (element.getName().equals(name)) {
            //If user want to delte a file
            if (element instanceof File) {
                //Call the deleteFile Function
                fs.deleteFile(name,currentDirectory);
                //Printing the which file is deleted
                System.out.println("File deleted: " + name);
            }
            //If user want to delete a directory and its contents
            else{
                //Call the deleteDirectory Function
                fs.deleteDirectory(name,currentDirectory);
                //Printing the which directory and its contents is deleted
                System.out.println("Directory deleted: " + name + "/");
            }
            break; //If file or directory find, then the loop can ve stopped
        }
    }

        System.out.println("-----------------------------------------------------------");
    }
    
    /**
     * This funciton aim is to move elment
     */
    private static void moveElement(){
        System.out.println("-----------------------------------------------------------");
        System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
        System.out.print("Enter the name of file/directory to move: ");
        String name = scanner.nextLine();
       
        System.out.print("Enter new directory path: ");
        String dPath = scanner.nextLine();
        
        fs.moveElement(name,dPath,currentDirectory,fs);

        System.out.println("File moved: " + name + " to " + dPath);
        System.out.println("-----------------------------------------------------------");
    }

    /**
     * This funciton aim is to search file or directory
     */
    private static void search(){
        System.out.println("-----------------------------------------------------------");
        System.out.print("Search query: ");
        String query = scanner.nextLine();
        
        System.out.print("Search from root...\n");
        boolean found = fs.search(fs,query,fs.getRoot());
        System.out.println("-----------------------------------------------------------");  
    }

    /**
     * This funciton aim is to print the directory tree
     */
    private static void printDirectoryTree(){
        System.out.println("-----------------------------------------------------------");
        System.out.println("Path to current directory from root: ");
        fs.printDirectoryTree(currentDirectory,fs.getRoot(), 0);

        // Printing the path to the current directory from root
        
       
         System.out.println("-----------------------------------------------------------");
    }

    /**
     * This function aim is to sorting the files and directories in current path
     */
    private static void sortDirectoryByDate(){
        System.out.println("-----------------------------------------------------------");
        System.out.println("Sorted contents of " + fs.getCurrentPath(currentDirectory) + " by date created: ");
        fs.sortDirectoryByDate(fs,currentDirectory);
        System.out.println("-----------------------------------------------------------");
    }

    /**
     * This funciton aim is to helper function to find directory with name, If finds returned this object otherwise return null value
     * @return Directory
     */
    private static Directory helpFunctionFindDirectory( Directory parentDirectory,String name) {
        for (FileSystemElement element : parentDirectory.getChildren()) {
            if (element instanceof Directory ) {
                if(element.getName().equals(name)){
                    return (Directory) element;
                }
            }
        }
        return null; // If directory is not found, then returning the null value
    }

}