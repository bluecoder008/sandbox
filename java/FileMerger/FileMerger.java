import java.io.File;
import java.io.FileReader;
import java.util.HashSet;

class FileMerger 
{
    FileMerger(String dirName) 
    {
        File dir = new File( dirName );

        String[] children = dir.list();
        for (String file : children ) {
            String fileName = dir + File.separator + file;
            try {
                FileReader reader = new FileReader(new File(fileName));
                readers.add(reader);
            } catch (java.io.FileNotFoundException ex) {
                // This should not happen
                System.err.println("File: " + fileName + " not found");
                System.exit(-1);
            }
        }

        for (FileReader reader : readers) {
            System.out.println("FileReader => " + reader);
        }
    }

    void readAndMerge() 
    {
        do {


        } while ( readers.size() > 0 );
    }

    HashSet<FileReader> readers = new HashSet<FileReader>();

    static public void main(String[] args) 
    {
       if ( args.length != 1 ) {
           System.err.println( "java FileMerger <dir-name>");
           System.exit(-1);
       }

       FileMerger fm = new FileMerger(args[0]);

    }
}
