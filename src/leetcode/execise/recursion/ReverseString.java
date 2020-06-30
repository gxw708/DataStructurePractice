package leetcode.execise.recursion;

public class ReverseString {
	
	public void reverseString(char[] s) {
        if(s == null || s.length == 1)
        {
            return;
        }
        
        int startPos = 0;
        int reversePos = s.length-1;
        reverse(startPos, reversePos, s);
        
        System.out.println(s);
    }
	
	private void reverse(int startPos, int reversePos, char[] s) {
		char temp = s[reversePos];
		s[reversePos] = s[startPos];
		s[startPos] = temp;
		
		startPos++;
		reversePos--;
		
		if(startPos <= reversePos)
		{
			reverse(startPos, reversePos, s);
		}
		else
		{
			return;
		}
	}
	
	public String printStringRecursion(char[] s) 
	{
		if(s.length>1) 
		{
    		char[] ns = new char[s.length-1];
    		System.arraycopy(s, 1, ns, 0, ns.length);
    		return String.format("%s,%s", printStringRecursion(ns), s[0]);
		}
		else
		{
			return String.format("%s", s[0]);
		}
	}
	
	public static void main(String[] args) {
		ReverseString rs = new ReverseString();
		System.out.println(rs.printStringRecursion("Hannah".toCharArray()));
		
		System.out.println(Double.valueOf(rs.myPow(8.95371, -1)));
	}

	public double myPow(double x, int n) {
		if(x == 1)
			return 1;
		
		if(x == -1)
			return n > 0 ? -1 : 1;
		
		if(n == 0)
            return 1;
		
		if(n == 1)
			return x;
		
		if(n == Integer.MIN_VALUE || n == Integer.MAX_VALUE)
			return 0;
		
		double base = x;
        if(n<0)
        {
            return 1/myPow(x, -n);
        }
        
        while(n>1)
        {
        	x *= base;
        	n--;
        }
        
        return x;
    }
}
