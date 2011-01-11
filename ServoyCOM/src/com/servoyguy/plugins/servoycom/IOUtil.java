package com.servoyguy.plugins.servoycom;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtil {
	
	public static void streamAndClose(InputStream in, OutputStream out) throws IOException {
		try {
			stream(in, out);
		} finally {
			try {
				in.close();			
			} finally {
				out.close();
			}
		}
	}
 
	public static void stream(InputStream in, OutputStream out) throws IOException {
        // Copy the input stream to the output stream
		byte buffer[] = new byte[1024];
		int len = buffer.length;
		while (true) {
			len = in.read(buffer);
			if (len == -1) {
				break;
			}
			out.write(buffer, 0, len);
		}
		out.flush();
		
	}
}
