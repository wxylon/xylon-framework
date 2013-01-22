/**
* Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.stack;

import java.util.Stack;

/**
 * 用两个栈实现队列
 * @author wxylon@gmail.com
 * @date 2013-1-22
 */
public class StackToQueue {
    private Stack<String> A=new Stack<String>();
    private Stack<String> B=new Stack<String>();
    
    public void offer(String str){
        A.push(str);       
    }
    
    public String poll(){
        while(!A.empty()){
            B.push(A.pop());
        }
        String rs=B.pop();
        while(!B.empty()){
           A.push(B.pop());
        }
        return rs;
    }
    
    public boolean isEmpty(){
        if(A.empty()&&B.empty())
            return true;
        else return false;
    }
    
    public static void main(String[] args){
        StackToQueue sq=new StackToQueue();
        sq.offer("A");
        sq.offer("B");
        sq.offer("C");        
        sq.offer("D");
        while(!sq.isEmpty()){
            System.out.println(sq.poll());
        }
    }
}



