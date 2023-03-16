/*
 * This is a template of SyncQueue.java. Chagne this file name into SyncQueue.java and
 * complete the implementation
 */
public class SyncQueue {
    private QueueNode queue[] = null;
    private final int COND_MAX = 10;
    private final int NO_TID = -1;

    public SyncQueue( ) {
        this.queue = new QueueNode[COND_MAX];
        for (int i = 0; i < COND_MAX; i++) {
            queue[i] = new QueueNode();
        }
    }

    public SyncQueue( int condMax ) {
	    this.queue = new QueueNode[condMax];
        for (int i = 0; i < condMax; i++) {
            queue[i] = new QueueNode();
        }
    }

    int enqueueAndSleep( int condition ) {
        if (condition >= 0 && condition < queue.length) {
            return this.queue[condition].sleep();
        }
        return NO_TID;
    }

    void dequeueAndWakeup( int condition, int tid ) {
        if (condition >= 0 && condition < queue.length) {
            this.queue[condition].wakeup(tid);
        }
    }

    void dequeueAndWakeup( int condition ) {
	    this.dequeueAndWakeup(condition, 0);
    }
}
