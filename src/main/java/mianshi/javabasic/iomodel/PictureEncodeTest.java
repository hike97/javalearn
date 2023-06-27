package mianshi.javabasic.iomodel;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author hike97
 * @Description 图片加密操作
 * @create 2020-11-18 14:05
 * @Modified By:
 **/
public class PictureEncodeTest {
	@Test
	public void test () {
		FileInputStream fis  = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream ("happysecret.jpg");
			fos = new FileOutputStream ("happydecodesecret.jpg");
			byte[] buff = new byte[1024];
			int len;
			while ((len=fis.read (buff))!=-1){
				for (int i = 0; i < len; i++) {
					buff[i] = (byte) (buff[i]^5);
				}
				fos.write (buff,0,len);
			}
		} catch (IOException e) {
			e.printStackTrace ();
		} finally {
			try {
				fos.close ();
				fis.close ();
			} catch (IOException e) {
				e.printStackTrace ();
			}
		}

	}
}
