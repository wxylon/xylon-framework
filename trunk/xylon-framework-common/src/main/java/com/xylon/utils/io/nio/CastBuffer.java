package com.xylon.utils.io.nio;

/**
 * @author wxylon@gmail.com
 * @date 2012-6-18
 */
import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class CastBuffer {
	static byte[] bytes = new byte[] { 0, 1, 2, 3, 4, 5, 'a', 'b', 'c' };

	public static void main(String[] args) {
		ByteBuffer bBuf = ByteBuffer.wrap(bytes);
		System.out.println(Arrays.toString(bBuf.array()));
		// 转换成IntBuffer
		IntBuffer iBuf = ((ByteBuffer) bBuf.rewind()).asIntBuffer();
		while (iBuf.hasRemaining()) {
			System.out.print(iBuf.get() + ",");
		}
		// 转换成FloatBuffer
		FloatBuffer fBuf = ((ByteBuffer) bBuf.rewind()).asFloatBuffer();
		while (fBuf.hasRemaining()) {
			System.out.print(fBuf.get() + ",");
		}
	}
}
