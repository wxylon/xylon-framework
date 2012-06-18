package com.xylon.utils.string;

import java.util.Stack;

public class StringSplitTest {
	public static String s = "mno,jkl,ghi,def,abc";
	
	public static void main(String[] args) {
//		System.out.println(reverse(s));
		System.out.println(ReverseStrWithStack(s));
	}
	
	/**
	 * 成立
	 * @param s
	 * @return
	 */
	public static String reverse(String s) {
		StringBuilder sb = new StringBuilder();
		int j = 0;
		for (char c : s.toCharArray()){
			sb.insert((c == ',' ? j = 0 : j++), c);
		}
			
		return sb.toString();
	}
	
	/**
	 * 不成立
	 * @param s
	 * @return
	 */
	public static String reverse1(String s) {
		StringBuffer sb = new StringBuffer(s);
		return sb.reverse().toString();
	}
	
	private static String reverseStr(String src){ 	
		if(src.length() == 0){
		    return src;
		}else{
		    return reverseStr(src.substring(1, src.length())) + src.substring(0,1);
		}	
	}
	
	private static String ReverseStrWithStack(String Src){
		Stack<Character> charstack=new Stack<Character>();
		for(int i=0;i<Src.length();i++){
		    charstack.push(Src.charAt(i));
		}
		String rtn="";
		for(int i=0;i<Src.length();i++){
		    rtn+=charstack.pop();  
		}
		return rtn;
	}


}
