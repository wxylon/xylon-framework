/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package sun.misc;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.junit.Test;

/**
 * http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/9b8c96f96a0f/src/share/classes/sun/misc/Unsafe.java
 * @author wxylon@gmail.com
 * @date 2012-12-19
 */
public class UnsafeTest {
	private static int byteArrayBaseOffset;

	@Test
	public void testUnsafe() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
		theUnsafe.setAccessible(true);
		Unsafe UNSAFE = (Unsafe) theUnsafe.get(null);
		System.out.println(UNSAFE);

		byte[] data = new byte[10];
		System.out.println(Arrays.toString(data));
		byteArrayBaseOffset = UNSAFE.arrayBaseOffset(byte[].class);

		System.out.println(byteArrayBaseOffset);
		UNSAFE.putByte(data, byteArrayBaseOffset, (byte) 1);
		UNSAFE.putByte(data, byteArrayBaseOffset + 5, (byte) 5);
		System.out.println(Arrays.toString(data));
	}
}
