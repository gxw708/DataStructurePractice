package leetcode.execise.recursion;

public class KthGrammar {
	
	public int kthGrammar(int N, int K) {
        if(N == 1)
            return 0;
        
        if(K == 1)
        	return 0;
        
        if(K == 2)
        	return 1;
        
        if(Math.pow(2, N-1) == K)
        	return N%2==0 ? 1 : 0;
        
        int prevK = K%2 == 0 ? K/2 : K/2 + 1;
        if(kthGrammar(N-1, prevK) == 1)
        {
        	return K%2 == 1 ? 1 : 0;
        }
        else
        {
        	return K%2 == 1 ? 0 : 1;
        }
    }

	public static void main(String[] args) {
		KthGrammar test = new KthGrammar();
		System.out.println(test.kthGrammar(4,5));
	}

}
