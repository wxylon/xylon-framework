package com.xylon.utils.io.nio;

/**
 * @author wxylon@gmail.com
 * @date 2012-6-18
 */
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.nio.channels.FileChannel;  
import java.nio.ByteBuffer;  
public class TestCopyFile {  
    public static void main(String[] args) throws IOException {  
        //调用FileManager类的copyFile静态方法   
        FileManager.copyFile(new File("src.txt"), new File("dst.txt"));  
    }  
}  

class FileManager {  
    //把可能出现的异常抛给上层调用者处理   
    public static void copyFile(File src, File dst)throws FileNotFoundException, IOException {  
        //得到一个源文件对应的输入通道   
        FileChannel fcin = new FileInputStream(src).getChannel();  
        //得到一个目标文件对应的输出通道   
        FileChannel fcout = new FileOutputStream(dst).getChannel();  
        //生成一个1024字节的ByteBuffer实例   
        ByteBuffer buf = ByteBuffer.allocate(1024);  
        while(fcin.read(buf) != -1) {  
            buf.flip();     //准备写   
            fcout.write(buf);  
            buf.clear();        //准备读   
        }  
    }  
}