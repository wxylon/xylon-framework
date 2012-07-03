/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.io.nio.n2;

/**
 * http://fayaa.com/code/view/259/#
 * 在文件里查找某个字符串出现的次数
 * @author wxylon@gmail.com
 * @date 2012-7-3
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class StringSearcher {

   public static void main(String[] args) {
       String file = args[0];
       String pattern = args[1];
       int count = 0;
       long d = System.currentTimeMillis();
       for (int i = 0; i < 1; i++) {
           count = searchCountWithNIO(file, pattern);
       }
       System.err.println((System.currentTimeMillis() - d) + ":" + count);
       System.gc();
       d = System.currentTimeMillis();
       for (int i = 0; i < 1; i++) {
           count = searchCountWithBufferReader(file, pattern);
       }
       System.err.println((System.currentTimeMillis() - d) + ":" + count);
       System.gc();
       d = System.currentTimeMillis();
       for (int i = 0; i < 1; i++) {
           count = indexCountWithBufferReader(file, pattern);
       }
       System.err.println((System.currentTimeMillis() - d) + ":" + count);
    }

   public static int searchCountWithNIO(String fileName, String patten) {
       int StringCount = 0;
       FileInputStream inputStream = null;
       try {
           File file = new File(fileName);
           inputStream = new FileInputStream(file);
           FileChannel channel = inputStream.getChannel();
           ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0,
                  channel.size());
           Charset cs = Charset.defaultCharset();
           CharBuffer charBuffer = cs.decode(buffer);
           char d[] = charBuffer.array();
           char[] a = patten.toCharArray();
           int next[] = KMP_getNext(a); 
          int i = 0, j = 0;
           char first =  a[0];
           int ns = next.length - 1;
           
          while (i < d.length) {
              if (d[i] == a[j]) {
                  if (j >= ns) {
                     StringCount++;
                     j = next[j];
                  }
                  i++;
                  j++;
              } else {
                  j = next[j];
                  if (j <= 0) {
                     while (i < d.length && d[i] != first)
                         i++;
                     j = 1;
                     i++;
                  }
              } 
          }
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           try {
              inputStream.close();
           } catch (IOException e) {
              e.printStackTrace();
           }
       }
       return StringCount;
    }
   
   public static int searchCountWithBufferReader(String fileName, String patten) {
       int StringCount = 0;
       FileInputStream inputStream = null;
       try {
           File file = new File(fileName);
           inputStream = new FileInputStream(file);
           InputStreamReader reader = new InputStreamReader(inputStream);
           BufferedReader bufferedReader = new BufferedReader(reader);

           char[] a = patten.toCharArray();
           int next[] = KMP_getNext(a);
           int c =  bufferedReader.read();
           int j = 0;
           int ns = next.length - 1;
           char first = a[0];

           while (c >= 0) {

              if ((char)c == a[j]) {

                  if (j >= ns) {
                     StringCount++;
                     j = next[j];
                  }
                  j++;
                  c =  bufferedReader.read();
              } else {
                  j = next[j];
                  if (j <= 0) {
                     while (c >= 0 && (char)c != first)
                         c =  bufferedReader.read();
                     j = 1;
                     c =  bufferedReader.read();
                  }
              } 
          }
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           try {
              inputStream.close();
           } catch (IOException e) {
              e.printStackTrace();
           }
       }
       return StringCount;
    }
   
   public static int indexCountWithBufferReader(String fileName, String patten) {
       int StringCount = 0;
       FileInputStream inputStream = null;
       try {
           File file = new File(fileName);
           inputStream = new FileInputStream(file);
           InputStreamReader reader = new InputStreamReader(inputStream);
           BufferedReader bufferedReader = new BufferedReader(reader);
           String line = null;

           while ((line = bufferedReader.readLine()) != null) {
              int start = 0;
              while ((start = line.indexOf(patten, start)) >= 0) {
                  // System.err.print(line.indexOf(patten));
                  // System.err.println(line);
                  StringCount++;
                  start++;
              }
           }
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           try {
              inputStream.close();
           } catch (IOException e) {
              e.printStackTrace();
           }
       }
       return StringCount;
    }
    
   public static int[] KMP_getNext(char[] inStr) {
       int next[] = new int[inStr.length];
       int i = 0, j = -1;
       next[0] = j;

      while (i < inStr.length - 1) {
           if (j == -1 || inStr[i] == inStr[j]) {
              i++;
              j++;
              next[i] = j;
           } else {
              j = next[j];
           }
       }
       return next;
    }
}