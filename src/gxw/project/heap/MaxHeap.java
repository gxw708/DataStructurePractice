package gxw.project.heap;

public class MaxHeap<E> {
	private int lastPosition = -1;
	private E[] array;
	private final static int defaultSize = 16;
	
	@SuppressWarnings("unchecked")
	public MaxHeap() {
		this.array = (E[])new Object[defaultSize];
	}
	
	@SuppressWarnings("unchecked")
	public MaxHeap(int size) {
		if(size < 0) 
			throw new IllegalArgumentException("heap size can't be negative");
		
		this.array = (E[])new Object[size];
	}
	
	public void add(E obj) {
		if(contains(obj))
			return;
		
		array[++lastPosition] = obj;
		trickleUp(lastPosition);
		System.out.println(this);
	}
	
	public E remove() {
		if(lastPosition == -1)
			throw new IndexOutOfBoundsException("Can't remove an empty MaxHeap.");
		
		E root = this.array[0];
		swap(0, lastPosition);
		lastPosition--;
		if(lastPosition > 0) 
			trickleDown(0);
		System.out.println(String.format("Removed %s. %s", root, this));
		return root;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("MaxHeap: ");
		for(int i =0;i<=lastPosition; i++) {
			sb.append(this.array[i]);
			if(i != lastPosition)
				sb.append(",");
		}
		
		return sb.toString();
	}
	
	public void printArray() {
		StringBuffer sb = new StringBuffer("MaxHeap: ");
		for(int i =0;i<=this.array.length-1; i++) {
			sb.append(this.array[i]);
			if(i != this.array.length-1)
				sb.append(",");
		}
		System.out.println(sb.toString());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void trickleUp(int pos) {
		if(pos == 0)
			return;
		
		int parentPos = getParentPosition(pos);
		if(((Comparable)array[pos]).compareTo(array[parentPos]) > 0) {
			swap(pos, parentPos);
			trickleUp(parentPos);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void trickleDown(int pos) {
		int leftChildPos = getLeftChildPosition(pos);
		if(leftChildPos > lastPosition) {
			// return if the left child has exceeded the heap size
			return;
		} else if(leftChildPos == lastPosition ) {
			// return if the left child hits the heap size. Swap the value if the left child is greater than its parent
			if(((Comparable)this.array[leftChildPos]).compareTo(array[pos]) > 0)
				swap(pos, leftChildPos);
			return;
		}
		
		// compare values between left and right child to do the next trickle down action
		int rightChildPos = getRightChildPosition(pos);
		if(((Comparable)this.array[leftChildPos]).compareTo(this.array[rightChildPos]) > 0) {
			swap(pos, leftChildPos);
			trickleDown(leftChildPos);
		} else {
			swap(pos, rightChildPos);
			trickleDown(rightChildPos);
		}
	}

	private void swap(int fromPos, int toPos) {
		E tempObj = array[toPos];
		array[toPos] = array[fromPos];
		array[fromPos] = tempObj;
	}
	
	private static final int getLeftChildPosition(int pos) {
		return pos * 2 + 1;
	}
	
	private static final int getRightChildPosition(int pos) {
		return pos * 2 + 2;
	}
	
	private static final int getParentPosition(int pos) {
		return Math.floorDiv(pos - 1, 2);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean contains(E obj) {
		for(int i =0;i<lastPosition;i++) {
			if(((Comparable)this.array[i]).compareTo(obj) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		MaxHeap<Integer> heap = new MaxHeap<Integer>(7);
		heap.add(1);
		heap.add(2);
		heap.add(3);
		heap.add(4);
		heap.add(5);
		heap.add(6);
		heap.add(7);
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		heap.remove();
		System.out.println("After Heap Sort:");
		heap.printArray();
	}
}
