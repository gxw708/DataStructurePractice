package gxw.project.string;

import java.util.Arrays;

public class StringPatternSearcher {
	private LPSTable lpsTable;
	private final String pattern;
	
	public StringPatternSearcher(String pattern) {
		this.pattern = pattern;
	}
	
	private void buildLPSTable(String pattern) {
		lpsTable = new LPSTable(pattern);
	}
	
	public boolean bfSearch(String str) {
		int i = 0, j = 0, start = 0;
		while(start < str.length()) {
			if(str.charAt(i) == pattern.charAt(j)) {
				// the character in pattern match the character in str
				i++;
				j++;
			} else {
				i = start++;
				j = 0;
			}
			if(j == pattern.length())
				return true;
		}
		return false;
	}
	
	public boolean kmpSearch(String str) {
		buildLPSTable(pattern);
		
		int i = 0, j = 0;
		while(i < str.length()) {
			if(str.charAt(i) == lpsTable.getChar(j)) {
				// the character in pattern match the character in str
				i++;
				j++;
			} else {
				if((j - 1) != 0) {
					// not match, search from the next of the previous repeated position
					j = lpsTable.getIndex(j-1) + 1;
				} else {
					i++;
				}
			}
			if(j == lpsTable.getLength())
				return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		String str = "ababcabcabababd";
		String pattern = "ababd";
		StringPatternSearcher searcher = new StringPatternSearcher(pattern);
		System.out.println(String.format("%s %s %s",  str, searcher.bfSearch(str) ? "contains" : "doesn't contain", pattern));
	}
	
	private class LPSTable {
		final char[] characters;
		final int[] indexes;
		
		LPSTable(String pattern) {
			characters = pattern.toCharArray();
			indexes = computeLPSArray(pattern);
		}
		
		int[] computeLPSArray(String pattern) {
			int[] lpsArray = new int[pattern.length()];
			int j = 0; // it is also the length of the previous longest prefix suffix
			int i = 1; // it is also the length of the current longest prefix suffix
			lpsArray[0] = 0; // lpsArray[0] is always 0
			
			// build the LPS table from 1 to pattern.length()
			while(i<pattern.length()) {
				if(pattern.charAt(i) == pattern.charAt(j)) {
					lpsArray[i] = j+1;
					i++;
					j++;
				} else {
					if(j != 0) {
						j = lpsArray[j-1];
					} else {
						lpsArray[i] = 0;
						i++;
					}
				}
			}
			
			return lpsArray;
		}
		
		void print() {
			System.out.println("LPS Table:");
			System.out.println(Arrays.toString(characters));
			System.out.println(Arrays.toString(indexes));
		}
		
		char getChar(int pos) {
			return characters[pos];
		}
		
		int getIndex(int pos) {
			return indexes[pos];
		}
		
		int getLength() {
			return indexes.length;
		}
	}
}
