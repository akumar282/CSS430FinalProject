class SuperBlock { 
      public int totalBlocks; // the number of disk blocks 
      public int totalInodes; // the number of inodes 
      public int freeList;    // the block number of the free list's head 
   } 
 
//While you have only three data members, you need to implement the following methods:  
   
    public SuperBlock( int diskSize ) { 
        // read the superblock from disk.
        byte[] superBlock = new byte[Disk.blockSize];   
        SysLib.rawread(0, superBlock);
        totalBlocks = SysLib.bytes2int(superBlock, 0);
        totalInodes = SysLib.bytes2int(superBlock, 4);
        freeList = SysLib.bytes2int(superBlock, 8);
        // check disk contents are valid. 
        if(totalBlocks == diskSize && totalInodes > 0 && freeList >= 2) {
            // disk contents valid
            return;
        } else {
        // if invalid, call format( ).                                                                                       
            totalBlocks = diskSize;
            format(defaultInodeBlocks);
        } 
    } 
    void sync( ) { 
        // write back in-memory superblock to disk: SysLib.rawwrite( 0, superblock ); 
        byte[] superBlock = new byte[Disk.blockSize];
        SysLib.int2bytes( totalBlocks, superBlock, 0 );
        SysLib.int2bytes( inodeBlocks, superBlock, 4 );
        SysLib.int2bytes( freeList, superBlock, 8 );
        SysLib.rawwrite( 0, superBlock );
        SysLib.cerr( "Superblock synchronized\n" ); 
    } 
 
    void format( int files ) { 
        // initialize the superblock  
        // initialize each inode and immediately write it back to disk 
        // initialize free blocks 
        // superblock: starts from #0
        // inode:      starts from #1
        // freelist:   starts from at least #2
        // initialize each free block
        for ( int i = freeList; i < totalBlocks; i++ ) {
           // zero initialization
           // let it point to the next blk
           // write it back to disk
        }
        sync( );
    } 
    
    public int getFreeBlock( ) { 
        // get a new free block from the freelist                                                                                 
        return freeBlockNumber; 
    } 
 
    public boolean returnBlock( int oldBlockNumber ) { 
        // return this old block to the free list. The list can be a stack.                                                                                                 
        return true or false; 
    }