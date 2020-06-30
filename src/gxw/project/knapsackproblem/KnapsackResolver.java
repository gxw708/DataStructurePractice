package gxw.project.knapsackproblem;

public class KnapsackResolver {
	private int[][] maxValues;
	private int[] weights;
	private int[] values;
	private int numOfItems;
	private int capacity;
	
	public KnapsackResolver(int knapsack_capacity, Treasure[] treasures) {
		if(knapsack_capacity <= 0)
			throw new IllegalArgumentException(String.format("Knapsack capacity %d must be a positive integer and can't be zero.", knapsack_capacity));
		
		if(treasures.length == 0)
			throw new IllegalArgumentException(String.format("empty treasure"));
		
		this.capacity = knapsack_capacity;
		this.numOfItems = treasures.length;
		this.weights = new int[numOfItems+1];
		this.values = new int[numOfItems+1];
		
		for(int i = 0;i<treasures.length;i++) {
			Treasure t = treasures[i];
			this.weights[i+1] = t.weight;
			this.values[i+1] = t.value;
		}
		
		this.maxValues = new int[numOfItems+1][capacity+1];
	}
	
	public int getMaxValue() {
//		return getMaxValueRec(numOfItems, capacity);
		return getMaxValueBottomUp(numOfItems, capacity);
	}
	
	
	
	public String getStatusMap() {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<maxValues.length;i++) {
			for(int j=1;j<maxValues[i].length;j++) {
				sb.append(maxValues[i][j]);
				if(j< maxValues[i].length -1)
					sb.append(",");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	private int getMaxValueRec(int n, int c) {
		// return 0 if no more items or the knapsack has full
		if(n <= 0 || c <= 0) {
			return 0;
		}
		
		if(maxValues[n][c] != 0) {
			// return the value has calculated
			return maxValues[n][c];
		}
		
		
		if(weights[n] > c) {
			// the capacity is too small to carry the current item, then the max value
			// should be calculated without it
			maxValues[n][c] = getMaxValueRec(n-1, c);
		} else {
			// the capacity is larger than the current item, then we need to consider
			// whether carry the current item or not 
			int temp1 = getMaxValueRec(n-1, c); // the capacity's max value if not carry the current item
			int temp2 = values[n] + getMaxValueRec(n -1, c - weights[n]); // the capacity's max value if carry the current item
			maxValues[n][c] = Math.max(temp1, temp2);
		}
		
		return maxValues[n][c];
			
	}
	
	private int getMaxValueBottomUp(int n, int c) {
		for(int i=1;i<weights.length;i++) {
			int weight = weights[i];
			int value = values[i];
			for(int j=1;j<capacity+1;j++) {
				if(weight > j) {
					// capacity is smaller than the weight, so the max value is the one if not carry
					maxValues[i][j] = maxValues[i-1][j];
				} else {
					// capacity is equal or larger than the weight, so the max value is the bigger one between 
					// carry this item or not
					maxValues[i][j] = Math.max(maxValues[i-1][j], maxValues[i-1][j-weight]+value);
				}
			}
		}
		return maxValues[n][c];
	}
	
	private static class Treasure{
		private int weight;
		private int value;
		
		private Treasure(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
		
		@Override
		public String toString() {
			return String.format("Treature w: %d v: %d", this.weight, this.value);
		}
	}
	
	public static void main(String[] args) {
		Treasure[] treatures = new Treasure[]{
			new Treasure(4, 6),
			new Treasure(2, 3),
			new Treasure(5, 4),
			new Treasure(6, 5),
			new Treasure(2, 6),
			new Treasure(3, 7),
		};
		
		KnapsackResolver resolver = new KnapsackResolver(10, treatures);
		System.out.println(resolver.getMaxValue());
		System.out.println(resolver.getStatusMap());
	}
}
