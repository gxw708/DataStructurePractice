package gxw.project.string;

import java.util.Arrays;

public class HuffmanCoding {
	private final char[] chars;
	private final int[] counts;
	private String[] codes;
	
	public HuffmanCoding(String str) {
		char[] tempChars = new char[str.length()];
		int[] tempCounts = new int[str.length()];

		int cPos = 0;
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			boolean found = false;
			for(int j=0; j<tempChars.length; j++) {
				if(c == tempChars[j]) {
					found = true;
					tempCounts[j]++;
					break;
				}
			}
			if(!found) {
				tempCounts[cPos]++;
				tempChars[cPos++] = c;
			}
		}
		chars = new char[cPos];
		System.arraycopy(tempChars, 0, chars, 0, cPos);
		counts = new int[cPos];
		System.arraycopy(tempCounts, 0, counts, 0, cPos);
		
		System.out.println(Arrays.toString(chars));
		System.out.println(Arrays.toString(counts));
		
		mergeSort(0, cPos-1);
		System.out.println(Arrays.toString(chars));
		System.out.println(Arrays.toString(counts));
	}
	
	private void mergeSort(int l, int h) {
		int mid = (l+h)/2;
		if(l<h) {
			mergeSort(l, mid);
			mergeSort(mid+1, h);
			merge(l, mid, h);
		}
	}
	
	private void merge(int l, int mid, int h) {
		// this method merges 2 pieces of parts on the counts array.
		// one piece of part is from l to mid
		// another piece of part is from mid+1 to h
		int fstPos=0,fstOffSet=l,fstSize=mid-fstOffSet+1;
		int secPos=0,secOffSet=mid+1,secSize=h-secOffSet+1;
		int[] mergedCounts = new int[h-l+1];
		char[] mergedChars = new char[h-l+1];
		int mergedPos=0;
		
		while(fstPos<fstSize && secPos<secSize) {
			if(counts[fstPos+fstOffSet] > counts[secPos+secOffSet]) {
				mergedCounts[mergedPos] = counts[secPos+secOffSet];
				mergedChars[mergedPos] = chars[secPos+secOffSet];
				secPos++;
				mergedPos++;
			} else {
				mergedCounts[mergedPos] = counts[fstPos+fstOffSet];
				mergedChars[mergedPos] = chars[fstPos+fstOffSet];
				fstPos++;
				mergedPos++;
			}
		}
		
		for(;fstPos<fstSize;fstPos++) {
			mergedCounts[mergedPos] = counts[fstPos+fstOffSet];
			mergedChars[mergedPos] = chars[fstPos+fstOffSet];
			mergedPos++;
		}
		
		for(;secPos<secSize;secPos++) {
			mergedCounts[mergedPos] = counts[secPos+secOffSet];
			mergedChars[mergedPos] = chars[secPos+secOffSet];
			mergedPos++;
		}
		
		System.arraycopy(mergedCounts, 0, counts, l, mergedCounts.length);
		System.arraycopy(mergedChars, 0, chars, l, mergedChars.length);
	}
	
	public String getCompressedMessage() {
		return "";
	}
	
	public int getTotalSize() {
		return getMesssageSize() + getDictTableSize();
	}
	
	public int getMesssageSize() {
		return 0;
	}
	
	public int getDictTableSize() {
		return 0;
	}
	
	
	public static void main(String[] args) {
		String message = "hellow world";
		System.out.println(String.format("Length of %s before compress is %d", message, message.getBytes().length * 8));
		HuffmanCoding coding = new HuffmanCoding(message);
		System.out.println(String.format("Length of %s after compress is %s. Its size is %d", message, coding.getCompressedMessage(), coding.getTotalSize()));
	}
}
