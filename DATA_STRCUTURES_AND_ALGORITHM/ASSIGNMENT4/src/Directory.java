import java.util.LinkedList;
import java.util.List;

public class Directory extends FileSystemElement{

    private LinkedList<FileSystemElement> children;

    /**
     * Constructor do initialization directory
     * @param name
     * @param parent
     */
    public Directory(String name, FileSystemElement parent)
    {
        super(name,parent);
        children = new LinkedList<>();
    }

    /**
     * Adding element to linkedlist
     * 
     * @param element
     */
    public void addElement(FileSystemElement element)
    {
        children.add(element);
    }

    /**
     * Removing element from the Linkedlist
     * 
     * @param element
     */
    public void removeElement(FileSystemElement element)
    {
        children.remove(element);
    }
    /**
     * Getter
     * @return LinkedList<FileSystemElement> 
     */
    public LinkedList<FileSystemElement> getChildren() {
        return children;
    }

    @Override
    /**
     * Printing the prefix and directory name
     * @param prefix
     */
    public void print(String prefix)
    {
        System.out.print(prefix +getName() + "/");
       
    }

    /**
     * This function is not used in anywhere, some code is tried by me, but in the final it is not used
     */
     public void setLinkedList(LinkedList<FileSystemElement> children) {
        this.children = children;
    }

}