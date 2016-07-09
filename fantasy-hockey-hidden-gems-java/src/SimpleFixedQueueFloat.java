
public class SimpleFixedQueueFloat extends SimpleFixedQueue {
	private float[] floatElements;
	private static final int DEFAULT_CAPACITY = 10;
	int size;
	
	public SimpleFixedQueueFloat(){
		this(DEFAULT_CAPACITY);
	}
	
	public SimpleFixedQueueFloat(int capacity){
		floatElements = new float[capacity];
	}
	
	public void enqueueFloat(float n) throws ArrayIndexOutOfBoundsException {
		if (size == floatElements.length){
			throw new ArrayIndexOutOfBoundsException("Stack only holds max of " + floatElements.length + "!");
		}
		floatElements[size++] = n;
		
	}
	
	public float dequeueFloat(){
		float[] array = new float[floatElements.length];
		System.arraycopy(floatElements, 1, array, 0, floatElements.length-1);
		float tmp = floatElements[0];
		floatElements = array;
		size--;
		return tmp;
		
	}
	
	public float peekFloat(){
		return floatElements[0];
	}
	
	public boolean empty(){
		return size == 0;
	}
	
	public int getSize(){
		return size;
	}
	
	public String toString(){
		return java.util.Arrays.toString(floatElements);
	}

}
