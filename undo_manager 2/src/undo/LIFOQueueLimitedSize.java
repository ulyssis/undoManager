package undo;
 
import java.util.LinkedList;
 
/**
 * @author Crunchify.com Feel free to use this in your Enterprise Java Project
 */
 
public class LIFOQueueLimitedSize<E> extends LinkedList<E> {
 
	/**
	 * generated serial number
	 */
	private static final long serialVersionUID = -7772085623838075506L;
 
	// Size of the queue
	private int size;
 
	// Constructor
	public LIFOQueueLimitedSize(int crunchifySize) {
 
		// Creates an ArrayBlockingQueue with the given (fixed) capacity and default access policy
		this.size = crunchifySize;
	}
 
	// If queue is full, it will remove oldest/first element from queue like FIFO
	// Do we need this add() method synchronize? What do you think?
	@Override
	synchronized public boolean add(E e) {
 
		// Check if queue full already?
		if (super.size() == this.size) {
			// remove element from queue if queue is full
			this.remove();
		}
		return super.add(e);
	}
	
	
 
}