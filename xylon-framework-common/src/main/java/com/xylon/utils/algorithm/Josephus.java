package com.xylon.utils.algorithm;

public class Josephus {
	public static int[] arrayOfJosephus(int number, int per) {
		int[] man = new int[number];
		for(int count = 1, i = 0, pos = -1; count <= number; count++) {
			do {
				pos = (pos+1) % number;  // 环状处理 
				if(man[pos] == 0)
					i++;      
				if(i == per) {  // 报数为3了           
					i = 0;            
					break;
				}
			} while(true);
			man[pos] = count;    
		}
		return man;
	}
	
	public static void main(String[] args) {   
		int[] man = Josephus.arrayOfJosephus(41, 3); 
		int alive = 3;             
		System.out.println("约琴夫排列：");   
		for(int i = 0; i < 41; i++)        
			System.out.print(man[i] + " ");  
		System.out.println("\nL表示3个存活的人要放的位置：");  
		for(int i = 0; i < 41; i++) {    
			if(man[i] > alive)              
				System.out.print("D");            
			else        
				System.out.print("L");    
			if((i+1) % 5 == 0)          
				System.out.print("  "); 
    	}         
		System.out.println();  
   }
}
				


