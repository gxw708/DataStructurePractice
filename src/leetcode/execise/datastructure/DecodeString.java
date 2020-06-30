package leetcode.execise.datastructure;

import java.util.Stack;

public class DecodeString {
    public String decodeString(String s) {
        if(s.indexOf("[") == -1) // no encoding string, return directly
            return s;
        
        StringBuffer sb = new StringBuffer(s);
        Stack<Integer> stack = new Stack<Integer>();
        int foundPos = sb.indexOf("[");
        while(foundPos != -1)
        {
            stack.push(foundPos);
            foundPos = sb.indexOf("[", foundPos+1);
        }
        
        while(!stack.isEmpty())
        {
            Integer start = stack.pop();
            int repeatStartPos = getRepeatStartPos(start-1, sb);
            int repeat = Integer.parseInt(sb.substring(repeatStartPos, start));
            int end = sb.indexOf("]", start+1);
            StringBuffer temp = new StringBuffer();
            for(int i=0;i<repeat;i++)
            {
                temp.append(sb.substring(start+1, end));
            }
            sb.replace(repeatStartPos, end+1, temp.toString());
        }
        
        return sb.toString();
    }    
    
    private static int getRepeatStartPos(int end, StringBuffer sb)
    {
        while(end > -1 && isNumber(sb.charAt(end)))
        {
            end--;
        }
        
        return end+1;
    }
    
    private static boolean isNumber(char c)
    {
        return (int)c >= 48 && (int)c<=57;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        DecodeString ds = new DecodeString();
        System.out.println(ds.decodeString("2[abc]3[cd]ef"));
    }

}
