package leetcode.execise.interview;

import java.math.BigDecimal;

public class NumberComplement {
	public static int findComplement(int num)
	{
		return Integer.parseInt(Integer.toString(num, 2).replaceAll("0", "2").replaceAll("1", "0").replaceAll("2", "1"), 2);
	}
	
	public static void main(String[] str)
	{
		Double d = Double.parseDouble("123");
		float f = (1F/2F);
		System.out.println(Float.toString(f));
		int number = 2;
		System.out.println(String.format("Number %d's complement is %d", number, findComplement(number)));
	}
}
