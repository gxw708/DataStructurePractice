package leetcode.execise.interview;

public class StringTest {
	public int firstUniqChar(String s) {
		if(s.length() == 1)
			return 0;
		
        int ret = -1;
        int pos = 0;
        while(pos<s.length())
        {
        	char c = s.charAt(pos);
            if(s.substring(0, pos).lastIndexOf(c) == -1 && s.substring(pos+1).indexOf(c) == -1)
            {
            	ret = pos;
            	break;
            }
            else
            {
            	pos++;
            }
        }
        return ret;
    }

	public static void main(java.lang.String[] args) {
		StringTest test = new StringTest();
		//System.out.println(test.firstUniqChar("dddccdbba"));
		
		System.out.println((int)'9');
	}

}
