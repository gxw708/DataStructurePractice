package gxw.project.practice;

import java.util.Stack;

public class TestPractices {
	public static int fab(int n) {
		if(n == 1 || n == 2)
			return 1;
		
		int[] memo = new int[n+1];
		memo[1] = 1;
		memo[2] = 1;
		
		for(int i=3;i<=n; i++)
			memo[i] = memo[i-1] + memo[i-2];
		
		return memo[n];
	}
	
	public final static void main(String[] args) {
		TestPractices test = new TestPractices();
		System.out.println(test.largestRectangleArea(new int[]{2,1,2}));
	}
	
	public int largestRectangleArea(int[] heights) {
		Stack<Integer> stack = new Stack<Integer>();
		int maxArea = 0;
		int area = 0;
		int i = 0;
		while (i < heights.length) {
			if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
				stack.push(i++);
			} else {
				int top = stack.pop();
				if (stack.isEmpty()) {
					area = heights[top] * i; // top is the lowest height
				} else {
					area = heights[top] * (i - stack.peek() - 1);
				}

				maxArea = Math.max(maxArea, area);
			}
		}

		while (!stack.isEmpty()) {
			int top = stack.pop();
			if (stack.isEmpty()) {
				area = heights[top] * i; // top is the lowest height
			} else {
				area = heights[top] * (i - stack.peek() - 1);
			}

			maxArea = Math.max(maxArea, area);
		}

		return maxArea;
	}
}
