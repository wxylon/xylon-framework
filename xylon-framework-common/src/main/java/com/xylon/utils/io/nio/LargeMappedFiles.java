package com.xylon.utils.io.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * @author wxylon@gmail.com
 * @date 2012-6-18
 */
public class LargeMappedFiles {  
    public static void main(String args[]) {     
        try {  
            File[] files = new File[] {new File("src1.txt"), new File("src2.txt")};   
            ArrayList<String> ls = search(files, "something is wrong");  
            for(int i=0; i<ls.size(); i++) {  
            	System.out.println(ls.get(i));  
            }  
        } catch (FileNotFoundException e) {     
            e.printStackTrace();     
        } catch (Exception e) {     
            e.printStackTrace();     
        }     
    }  
    //实现简单的内容检索   
    private static ArrayList<String> search(File[] files, String text) throws Exception {  
        //把检索结果放到一个list中   
        ArrayList<String> result = new ArrayList<String>();  
        //循环遍历文件   
        for(File src : files) {  
            //将整个文件映射到内存   
            MappedByteBuffer dst = new RandomAccessFile(src, "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, src.length());  
        //对字符进行解码   
        String str = Charset.forName("UTF-8").decode(dst).toString();  
        //准备进行读   
        dst.flip();  
        if(str.indexOf(text) != -1) {  
            result.add(src.getName());  
        }  
        //准备写   
        dst.clear();  
        }  
        return result;  
    }  
}  
