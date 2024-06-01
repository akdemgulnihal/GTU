public class File extends FileSystemElement{

     /**
     * Constructor do initialization file
     * @param name
     * @param parent
     */
    public File(String name, FileSystemElement parent)
    {
        super(name,parent);
    }

    @Override
      /**
     * Printing the file and directory name
     * @param prefix
     */
    public void print(String prefix)
    {
        System.out.print(prefix + getName());
    }
}