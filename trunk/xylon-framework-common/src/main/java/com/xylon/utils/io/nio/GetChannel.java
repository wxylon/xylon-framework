package com.xylon.utils.io.nio;

/**
 * @author wxylon@gmail.com
 * @date 2012-6-18
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {
	// 为了使代码明晰，暂不处理异常
	public static void main(String[] args) throws Exception {
		FileChannel fc = null;
		// 向一个文件中写入文本
		fc = new FileOutputStream(new File("data.txt")).getChannel();
		fc.write(ByteBuffer.wrap("some text".getBytes()));
		fc.close();
		// 以读写方式打开文件，并在尾部追加内容
		fc = new RandomAccessFile("data.txt", "rw").getChannel();
		fc.position(fc.size());
		fc.write(ByteBuffer.wrap("some more".getBytes()));
		fc.close();
		// 将文件里的内容读出来
		fc = new FileInputStream("data.txt").getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		fc.read(buf);
		buf.flip();
		while (buf.hasRemaining()) {
			System.out.print((char) buf.get());
		}
	}
}
