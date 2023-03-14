   public class FileTable { 
 
      private Vector table;         // the actual entity of this file table 
      private Directory dir;        // the root directory  
 
      public FileTable( Directory directory ) { // constructor 
         table = new Vector( );     // instantiate a file (structure) table 
         dir = directory;           // receive a reference to the Director 
      }                             // from the file system 
 
      // major public methods 
      public synchronized FileTableEntry falloc( String filename, String mode ) { 
        short iNumber = -1;
        Inode inode = null;
         // allocate a new file (structure) table entry for this file name 
         while(true) {       
            // allocate/retrieve and register the corresponding inode using dir 
            iNumber = (filename.equals("/") ? 0 : dir.namei(filename);
            inode = new Inode(iNumber);
            if(mode.compareTo("r") == 0) {
                break;
            } else if(mode.compareTo("w") == 0 || mode.compareTo("w+") == 0) {
                try {
                    wait()
                } catch(InterruptedException e) {} // possibly incorrect implementation
            } else if(mode.compareTo("a") == 0) {
                iNumber = -1; // no more open
                return null;
            }
         // increment this inode's count 
         inode.count++;
         // immediately write back this inode to the disk 
         inode.toDisk(iNumber);
         // return a reference to this file (structure) table entry 
         FileTableEntry e = new FileTableEntry(inode, iNumber, mode);
         table.addElement(e);
         return e;
      } 
 
      // TODO
      public synchronized boolean ffree( FileTableEntry e ) { 
         // receive a file table entry reference 
         // save the corresponding inode to the disk 
         // free this file table entry. 
         // return true if this file table entry found in my table 
      } 
 
      public synchronized boolean fempty( ) { 
         return table.isEmpty( );  // return if table is empty  
      }                            // should be called before starting a format 
   }