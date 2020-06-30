package leetcode.execise.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalTriangle {
	public static void main(String[] args) {
		PascalTriangle test = new PascalTriangle();
		System.out.println(Arrays.deepToString(test.getRow(2).toArray()));
	}

	public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<Integer>();
        result.add(1);
        if(rowIndex == 0)
            return result;
        for(int i = 1; i <=  rowIndex ; i++){
            for(int j = i-1;j > 0; j--){
                result.set(j,result.get(j-1)+result.get(j));
            }
            result.add(1);
        }
        return result;
    }
}
