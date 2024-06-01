import java.util.LinkedList;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;


public class FileSystem{

    private Directory root;

    /**
     *  Initializing the file system with a root directory
     */
    public FileSystem(){
       
        this.root = new Directory("root", null);
    }

    /**
     * Creating a file 
     * @param name name of the file
     * @param parent    parent of this file
     */
    public void createFile(String name, Directory parent){
        //Creating a file 
        File file = new File(name, parent);
        //Adding this file to linked list
        parent.addElement(file);

    }
    /**
     * Creating a directory
     * @param name name of the directory
     * @param parent    parent of this file
     */
    public void createDirectory(String name, Directory parent){
        //Creating a directory
        Directory directory = new Directory(name,parent);
        //Adding this directory to linked list
        parent.addElement(directory);
        
    }
    /**
     * Delete a file
     * @param name name of the file which is going to be deleted
     * @param parent current directory
     */
    public void deleteFile(String name, Directory parent)
    {
        //Delete a file from the specified directory
        File ftd = null;

        // Finding the file which is wanted to delete using  getChildren() method
        for (FileSystemElement element : parent.getChildren()) 
        {
            if(element.getName().equals(name))  //When elemnet and file is matched
            {
                ftd = (File) element;   //The element stored in ftd
                break;
            }
            
        }
        //If ftd is null
        if (ftd == null) {
             System.out.println("There is no file in this name ");
        } 
        //If ftd is not null
        else {
            // Removing the file from the parent 
            parent.removeElement(ftd);
        }
        
    }

    /**
     * This method is RECURSIVELY delete directory
     * 
     * @param name name of the directory which is going to be deleted
     * @param parent
     */
    public void deleteDirectory(String name, Directory parent)
    {
        Directory dDelete = null;

        // Finding the direcctory which is wanted to delete using  getChildren() method
        for (FileSystemElement element : parent.getChildren()) 
        {
            if (element.getName().equals(name)) 
            {
                dDelete = (Directory) element;
                break;
            }
        }

        if (dDelete == null) {

            System.out.println("Directory not found: " + name);
            
        
        } else {
            LinkedList<FileSystemElement> tempList = dDelete.getChildren();
            FileSystemElement[] childrenCopy = tempList.toArray(new FileSystemElement[0]);
        
            // Delete all contents of the directory recursively
            for (FileSystemElement element : childrenCopy) {
                if (element instanceof File) {
                     // Deleting the files in directory
                    dDelete.removeElement(element);
                   
                } else {
                    // Recursively deleting the subdirectories
                    deleteDirectory(element.getName(), dDelete);
                }
            }
            // Removig the directory which is wanted to delete recursively
            parent.removeElement(dDelete);
        }

       
    }

    /**
     * This function first of all find the nmae in the linkedlist(index)
     * Then call the changeDirectory to cahnging directory according to targetPath
     * Then removed the elment according to index and returned the removed element
     * Then this removed element added target path
     * 
     * @param name  the name of moving element
     * @param targetPath    target path to move there
     * @param currentDirectory  currentDirectory
     * @param fs
     */
    public void moveElement(String name, String targetPath,Directory currentDirectory,FileSystem fs)
    {
         
       LinkedList<FileSystemElement> children = currentDirectory.getChildren();
        boolean found = false;
        int i=0;int index=-1;
        for (FileSystemElement element : children) {
            i++;
            if (element.getName().equals(name)) {
                // If Found, tehn flag is true 
                found = true;
                index = i;
                break;
            }
        }

        if (!found) {
            System.out.println( name + " is not exist in the current directory.");
           
        }
       
         currentDirectory = changeDirectory(targetPath,currentDirectory,fs);
       

           if(index!=-1){
            FileSystemElement removedElement = children.remove(index-1);
            currentDirectory = changeDirectory(targetPath,currentDirectory,fs);
            currentDirectory.addElement(removedElement);
            }

     
    }

  
    

    /**
     * This method do search RECURSIVELY
     * 
     * @param name searching according to this parameter
     * @param directory this is start point (root) , search starts from there
     * @param fs This parameter is defined in main, and to find currentPath this parameter is used
     * 
     * @return boolean value. If found file or directory returned true, otherwise returning false
     */
    public boolean search(FileSystem fs, String name , Directory directory)
    {
        //Searching for file or directorie according to name
    
        for (FileSystemElement element : directory.getChildren()) 
        {
            //When element and user entry(name) is matched
            if (element.getName().equals(name)) 
            {   
                //Found message will be printed and where is found is printed ,as well
                System.out.println("Found: " + fs.getCurrentPath(directory)  + name);
                return true;    //Return the true
            }
        }

    //When in current directory cannot found then go to subdirectory
    // Recursively searching subdirectories
    for (FileSystemElement element : directory.getChildren()) 
    {   
        //If elemnt type is Directory,then need to search that directory
        if (element instanceof Directory) {
            boolean found = search( fs,name,(Directory) element);   //So subdirectories is call seacrh method
            if (found) {    //If found
                return true;    //Then returned the true
            }
        }
    }
        return false; 
    }

    /**
     * This method is RECURSIVELY working
     * 
     * @param currentDirectory current directory
     * @param theStart tarcking start from the root
     * @param blank parent and cgil directories have differnce number of blank before, this parameter for that reason is used
     */
    public void printDirectoryTree(Directory currentDirectory,Directory theStart,int blank){

    //Blank level is changed for the level of subdirectory    
    int i=0;
    while (i < blank) {
        System.out.print("   "); // printing spaces for each sub directories
         i++;
    }

    //Call the print method in Directory class
    theStart.print("* ");
   
    //IF current directory and theStart is same, then printing Current Directory message
    if (theStart == currentDirectory) 
    {
        System.out.print(" (Current Directory)");
    }

    System.out.println();

    // Recursively print the tree
    for (FileSystemElement element : theStart.getChildren()) {
        //If element is directory type, then recursive function is called, and blank number is increased
        if (element instanceof Directory) {
            printDirectoryTree(currentDirectory,(Directory) element, blank + 1);
        } else {
            // Printing the file 
            int j=0;
            while( j < blank + 1) {
                System.out.print("   "); // spaces before file
                 j++;
            }
            System.out.println("" + element.getName());
        }
    }

    }
    /**
     * Listing the contents in current path
     * 
     * @param dir current directory
     */
    public void listContents(Directory dir){
        //List all contents in current path
        for(FileSystemElement sth: dir.getChildren())
        {   
            //If element is file
            if(sth instanceof File)
            {
                System.out.println(sth.getName());
            }
            //If element is directory
            else{
                System.out.println("* " + sth.getName() + "/ ");
            }
        }
    }

    /**
     * Sorting the contents of a directory according to creation date,time
     * @param fs
     * @param dir 
     */
    public void sortDirectoryByDate(FileSystem fs, Directory dir){
       
        // Getting the children of the directory using getChildren method in directory class
        LinkedList<FileSystemElement> childrenList = dir.getChildren();

        //According to creation time,do sortting using a comparator
        Collections.sort(childrenList, new Comparator<FileSystemElement>() {
            
            public int compare(FileSystemElement comp1, FileSystemElement comp2) 
            {
                //time1 and time2 values stored the time of comp1 and comp2
                Timestamp time1 = comp1.getDateCreated();
                Timestamp time2 = comp2.getDateCreated();
                return time1.compareTo(time2);// Compare the time of the elements when they created
            }
        });

    
        for (FileSystemElement element : childrenList) {
            
            //If element is directory type
            if (element instanceof Directory) {
                element.print("*");//Call print function  in directory
                System.out.println( " (" + element.getDateCreated() + ")");
            } 
            //If element is file type
            else if (element instanceof File) {
                element.print("");//Call print function  in file
                System.out.println( " (" + element.getDateCreated() + ")");
            }
        }
    }

    /**
     * Returning the current path
     * @param dir current directory
     *  @return String retruning the current path from root to current directory 
     */ 
    public String getCurrentPath(Directory dir){
        
        Directory currentDirectory = dir;
        String path = "";
        
        //do while currentDirectory is not null
        while (currentDirectory != null) {
            path = currentDirectory.getName() + "/" + path;
            currentDirectory = (Directory) currentDirectory.getParent();
        }
        return path.toString();
    }

    /**
     * This function aim is to change directory 
     * in move element this function is called
     * 
     * @param path target path
     * @param curentDirectory is stored current path
     * @param fs
     * 
     */
    public Directory changeDirectory(String path,Directory currentDirectory,FileSystem fs){
    
         boolean flag=false;
    
   
        String pathCopy = path;

         if (path.startsWith("root")) {
            currentDirectory = fs.getRoot(); // setting current directory to root
            path = path.substring(5); //If path starts with root/ , then delete this because if we dont do that, it suppose its new directory 
        }

        // Starting from the root directory
        Directory current = fs.getRoot();

        // Splitting the path according to delimeter '/' 
        String[] directoryNames = path.split("/");
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
               
                flag=true;
                break;}
            }
        }

        if(flag == false && i !=0){    
            currentDirectory = current; // Assign current to currentDirectory 
               
        }
        if(i==0 )
        {
           
        }
      
        return currentDirectory;    //returning the currentPath
    }

    /**
     * getter for root
     * @return root Directory type
     */
    public Directory getRoot(){
        return root;
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
