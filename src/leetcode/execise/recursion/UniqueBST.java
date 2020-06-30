package leetcode.execise.recursion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class UniqueBST {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
		
		public String toString()
		{
			return "\n" + this.toString(new StringBuilder(), true, new StringBuilder()).toString();
		}
		
		private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb)
        {
            if (right != null)
            {
                right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
            }
            sb.append(prefix).append(isTail ? "└── " : "┌── ").append(String.valueOf(val)).append("\n");
            if (left != null)
            {
                left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
            }
            return sb;
        }
	}
	
	public int getNumTrees(int n)
	{
		if (n < 0) {
            return -1;
        }

        int[] count = new int[n + 1];
        count[0] = 1;
        for (int i = 1; i < n + 1; ++i) {
            for (int j = 0; j < i; ++j) {
                count[i] += count[j] * count[i - j - 1];
            }
        }

        return count[n];
	}

    public List<TreeNode> generateTrees(int n) {
    	return genBST(1, n);
    }
    
    private List<TreeNode> genBST(int min, int max)
    {
    	List<TreeNode> result = new ArrayList<TreeNode>();
    	if(min > max)
    	{
    		result.add(null);
    		return result;
    	}
    	
    	for(int i=min;i<=max;i++)
    	{
    		List<TreeNode> leftSubTrees = genBST(min, i-1);
    		List<TreeNode> rightSubTrees = genBST(i+1, max);
    		for(int l=0;l<leftSubTrees.size();l++)
    		{
    			for(int r=0;r<rightSubTrees.size();r++)
    			{
    				TreeNode root = new TreeNode(i);
    	    		result.add(root);
    				root.left = leftSubTrees.get(l);
    				root.right = rightSubTrees.get(r);
    			}
    		}
    	}
    	
    	return result;
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if(root == null)
            return ret;
        
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        int visited = 1;
        List<Integer> level = new ArrayList<Integer>();
        level.add(root.val);
        
        while(q.size() != 0)
        {
            if((visited+1)%2 == 0)
            {
                ret.add(level);
                level = new ArrayList<Integer>();
            }
            TreeNode node = q.poll();
            level.add(node.val);
            if(node.left != null)
                q.offer(node.left);
            if(node.right != null)
                q.offer(node.right);
        }
        
        ret.add(level);
        return ret;
    }

	public static void main(String[] args) {
		UniqueBST test = new UniqueBST();
//		int num = 4;
//		List<TreeNode> result = test.generateTrees(num);
//		System.out.println(Arrays.deepToString(result.toArray()));
//		System.out.println(test.getNumTrees(num));
		
		char[] letters = new char[]{'c','f','j'};
		System.out.println(test.nextGreatestLetter(letters, 'c'));
		
		int nums[] = new int[]{9, 4, 9, 8, 4};
		int num = 4;
		System.out.println(Arrays.binarySearch(nums, num));
		
		int[][] sda = new int[3][4];
		System.out.println(sda[0].length+", "+sda.length);
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		Collections.reverse(list);
		System.out.println(Arrays.toString(list.toArray()));
		String s = "  a good   example  ";
		String[] strs = s.trim().split("\\s+");
		System.out.println(Arrays.toString(strs));
        int left = 0;
        int right = strs.length - 1;
        while(left <= right)
        {
            String temp = strs[right];
            strs[right] = strs[left];
            strs[left] = temp;
            
            left++;
            right--;
        }
        
        StringBuffer sb = new StringBuffer();
        for(String str : strs)
        {
            sb.append(str).append(" ");
        }
		sb.deleteCharAt(sb.length()-1);
		
		System.out.println(sb.toString());
	}

	public char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length;
        
        while(left < right)
        {
            int mid = left + (right - left)/2;
            char mc = letters[mid];
            if(mc > target)
            {
                System.out.println(mc + " > " + target);
                right = mid;
            }
            else
            {
                left = mid + 1;
            }
        }
        
        if(left < letters.length || letters[left] > target) return letters[left];
        return Character.MIN_VALUE;
    }
}
