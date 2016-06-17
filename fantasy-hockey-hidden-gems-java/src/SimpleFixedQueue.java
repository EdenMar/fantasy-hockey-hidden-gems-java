
public class SimpleFixedQueue {
	private int[] elements;
	private static final int DEFAULT_CAPACITY = 10;
	int size;
	
	public SimpleFixedQueue(){
		this(DEFAULT_CAPACITY);
	}
	
	public SimpleFixedQueue(int capacity){
		elements = new int[capacity];
	}
	
	public void enqueue(int n) throws ArrayIndexOutOfBoundsException {
		if (size == elements.length){
			throw new ArrayIndexOutOfBoundsException("Stack only holds max of " + elements.length + "!");
		}
		elements[size++] = n;
		
	}
	
	public int dequeue(){
		int[] array = new int[elements.length];
		System.arraycopy(elements, 1, array, 0, elements.length-1);
		int tmp = elements[0];
		elements = array;
		size--;
		return tmp;
		
	}
	
	public int peek(){
		return elements[0];
	}
	
	public boolean empty(){
		return size == 0;
	}
	
	public int getSize(){
		return size;
	}

}
