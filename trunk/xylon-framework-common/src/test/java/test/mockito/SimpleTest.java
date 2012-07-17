/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package test.mockito;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * http://www.open-open.com/home/space-37924-do-blog-id-8736.html
 * @author wxylon@gmail.com
 * @date 2012-7-17
 */
public class SimpleTest {
	@Test 
    public void simpleTest(){   
           
        //创建mock对象，参数可以是类，也可以是接口   
        List<String> list = mock(List.class);   
           
        //设置方法的预期返回值   
        when(list.get(0)).thenReturn("helloworld");   
       
        String result = list.get(0);   
           
        //验证方法调用(是否调用了get(0))   
        verify(list).get(0);   
           
        //junit测试   
        Assert.assertEquals("helloworld", result);   
    }
	
	@Test  
	public void argumentMatcherTest(){  
	    List<String> list = mock(List.class);  
	    when(list.get(anyInt())).thenReturn("hello","world");  
	    String result = list.get(0)+list.get(1);  
	    verify(list,times(2)).get(anyInt());  
	    Assert.assertEquals("helloworld", result);  
	}
	
	@Test  
	public void argumentMatcherTest2(){  
	    Map<Integer,String> map = mock(Map.class);  
	    when(map.put(anyInt(),anyString())).thenReturn("hello");//anyString()替换成"hello"就会报错  
	    map.put(1, "world");  
	    verify(map).put(eq(1), eq("world")); //eq("world")替换成"world"也会报错  
	}
	
	@Test  
	public void verifyInvocate(){   
	       
	    List<String> mockedList = mock(List.class);   
	    //using mock    
	     mockedList.add("once");   
	     mockedList.add("twice");   
	     mockedList.add("twice");   
	        
	     mockedList.add("three times");   
	     mockedList.add("three times");   
	     mockedList.add("three times");   
	        
	     /**  
	      * 基本的验证方法  
	      * verify方法验证mock对象是否有没有调用mockedList.add("once")方法  
	      * 不关心其是否有返回值，如果没有调用测试失败。  
	      */  
	     verify(mockedList).add("once");    
	     verify(mockedList, times(1)).add("once");//默认调用一次,times(1)可以省略   
	        
	     verify(mockedList, times(2)).add("twice");   
	     verify(mockedList, times(3)).add("three times");   
	        
	     //never()等同于time(0),一次也没有调用   
	     verify(mockedList, times(0)).add("never happened");   
	        
	     //atLeastOnece/atLeast()/atMost()   
	     verify(mockedList, atLeastOnce()).add("three times");   
	     verify(mockedList, atLeast(2)).add("twice");   
	     verify(mockedList, atMost(5)).add("three times");   
	  
	}  
}

