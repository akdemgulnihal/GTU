import java.sql.Timestamp;

public abstract class FileSystemElement{
    protected String name;

    protected Timestamp dataCreated;

    protected FileSystemElement parent;

    /**
     * Constrıctor do initialization and current time creatin also stored
     * @param name
     * @param parent
     */
    public FileSystemElement(String name,FileSystemElement parent)
    {
        this.name = name;
        this.parent = parent;
        this.dataCreated = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Getter for directory/file name
     * 
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter is used in comparator to compare creation time of directories/files
     * @return TimeStamp
     */
    public Timestamp getDateCreated()
    {
        return dataCreated;
    }

    /**
     * Getter returning the parent of current path
     * 
     * @return parent
     */
    public FileSystemElement getParent()
    {
        return parent;
    }


    /**
     * 
     * Setter for parent
     * @param parent
     */
    public void setParent(FileSystemElement parent){
        this.parent = parent;
    }
    public abstract void print(String prefix); //To help in printing the directory
}