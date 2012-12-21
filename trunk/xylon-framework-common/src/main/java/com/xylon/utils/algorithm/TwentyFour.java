/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.algorithm;

/**
 * http://www.iteye.com/topic/312476
 * @author wxylon@gmail.com
 * @date 2012-8-10
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class TwentyFour {
	public static void main(String[] args){
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String line;
		try{
			while((line=br.readLine())!=null){
				try{
					if ("exit".equals(line)) break;
					
					String[] s=line.split("\\s");
					int[] v=new int[4];
					for(int idx=0;idx<4;idx++) {
						v[idx]=Integer.parseInt(s[idx]);
						if (v[idx]<=0||v[idx]>=10) throw new Exception("Input error.");
					}
					evaluate(v);
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void evaluate(int[] v){
		for(int a=0;a<4;a++)
			for(int b=0;b<4;b++){
				if (a==b) continue;
				for (int c=0;c<4;c++){
					if (a==c||b==c) continue;
					for (int d=0;d<4;d++){
						if (a==d || b==d || c==d ) continue; 
						check(v,new int[]{a,b,c,d});
					}
				}
			}
		evaluate(new int[]{v[0],v[1],v[2],v[3]},new char[]{'+','+','+'});
		evaluate(new int[]{v[0],v[1],v[2],v[3]},new char[]{'*','*','*'});
	}
	static char[] op={'+','-','*','/'};
	public static void check(int[] v,int[] idx){
		
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				for(int k=0;k<4;k++){
					if (i==j && j==k) continue;
					evaluate(new int[]{v[idx[0]],v[idx[1]],v[idx[2]],v[idx[3]]},new char[]{op[i],op[j],op[k]});
				}
			}
		}
	}

	/**
	 * 计算四个数字排列与操作符的运算结果
	 * @param num  数字排列
	 * @param op  操作符排列
	 */
	public static void evaluate(int[] num,char[] op){
		MyStack stack=new MyStack();
		//要入栈的操作数个数1-4
		int dataNum=0;
		if (op[0]==op[1] && op[0]==op[2]) dataNum=num.length - 1;
		for(;dataNum<num.length;dataNum++){
			//要入栈的操作符个数1-3
			int opNum=0;
			if (dataNum+1==num.length) opNum=op.length-1;
			int maxOpNum=dataNum;
			if (dataNum==0) maxOpNum=1;
			repeat:
			for(;opNum<maxOpNum;opNum++){
				int numCount=0;
				int dataIndex=0;
				int opIndex=0;

				stack.clear();
				
				while(dataIndex<num.length || opIndex<op.length){
					//操作数入栈
					for(int i=0;dataIndex<num.length && i<dataNum+1;i++){
						stack.push(num[dataIndex]);
						dataIndex++;
						numCount++;
					}
					//操作符入栈
					for(int k=0;opIndex<op.length && k<opNum+1;k++){
						if (numCount>1){
							stack.push(op[opIndex]);
							if (stack.isStop()) break repeat;
							opIndex++;
							numCount--;
						}
					}
				}
				if ((Integer)stack.pop()==24){
					System.out.println(stack.toString());
				}
			}
		}
	}

	public static class MyStack extends Stack{
		boolean stop=false;
		Stack stack=new Stack();
		
		public String toString(){
			return getExpression();
		}
		
		public boolean isStop(){
			return stop;
		}
		public String getExpression(){
			Object v=stack.pop();
			if (v instanceof Character){
				String right=getExpression();
				String left=getExpression();
				return "("+left+v+right+")";
			}
			return v.toString();
		}
		public void clear(){
			super.clear();
			stack.clear();
                        stop=false;
		}
		
		public Object push(Object v){
			stack.push(v);
			if (v instanceof Character){
				Integer v1=(Integer)pop();
				Integer v2=(Integer)pop();
				Integer v3=0;
				switch((Character)v){
				case '+': 
					v3=v2+v1;break;
				case '-':
					v3=v2-v1;
					if (v3<0) stop=true;
					break;
				case '*':
					v3=v2*v1;break;
				case '/':
					if (v1!=0 && v2%v1==0){
						v3=v2/v1;
					}else{
						stop=true;
					}
					break;
				}
				return super.push(v3);
			}else{
				return super.push(v);
			}
			
		}
	}
}


