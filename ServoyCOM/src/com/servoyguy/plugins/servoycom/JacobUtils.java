package com.servoyguy.plugins.servoycom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import com.jacob.com.LibraryLoader;
import com.jacob.com.Variant;
import com.servoy.j2db.util.Debug;

public class JacobUtils {
	
	private static final int BUFFER_SIZE = 1024;
	
	private static AtomicBoolean installed = new AtomicBoolean(false);
	private static AtomicBoolean loaded = new AtomicBoolean(false);
	
	// not to be instantiated, static methods only
	private JacobUtils() {}

	public static boolean loadDLL(String jacobDLL) {
		if (!loaded.get()) {
			try {
				String path = System.getProperty("user.home") + "/.servoy/" + jacobDLL;
				System.setProperty("jacob.dll.path", path);
				System.load(path);
				loaded.set(true);
				return true;
			} catch (final UnsatisfiedLinkError e) {
				Debug.error("Could not load Jacob DLL: " + jacobDLL);
				return false;
			}
		}
		return true;
	}
	
	public static boolean isJacobInstalled() {
		if (installed.get() && loaded.get()) return true;
		final String jacobLib = LibraryLoader.getPreferredDLLName() + ".dll";
		return isJacobInstalled(jacobLib);
	}

	public static boolean isJacobInstalled(final String jacobFile) {
		if (installed.get() && loaded.get()) return true;
		return loadJACOBDLL(jacobFile);
	}

	public static boolean loadJACOBDLL(final String jacobFile) {
		if (!installed.get()) {
			final boolean installedDLL = installFile(jacobFile);
			if (installedDLL) {
				installed.set(true);
				return loadDLL(jacobFile);
			} else {
				Debug.error("Error finding DLL: " + jacobFile);
				return false;
			}
		}
		return true;
	}

	public static boolean installFile(final String DLLName) {
		try {
			final File servoyDir = new File(System.getProperty("user.home"), "/.servoy/");
			if (!servoyDir.exists()) {
				servoyDir.mkdirs();
			}

			final File newDLL = new File(servoyDir, DLLName);
			if (!newDLL.exists()) {
				InputStream in = JacobUtils.class.getResourceAsStream("ressources/" + DLLName);
				OutputStream out = new FileOutputStream(newDLL);
				streamAndClose(in, out);
			}
			return true;
		} catch (final Exception e) {
			Debug.error("Cannot deploy Jacob DLL", e);
		}
		return false;
	}

	public static boolean isDispatch(final Variant v) {
		int type = v.getvt();
		return type == 9;
	}
	
	public static void streamAndClose(final InputStream in, final OutputStream out) throws IOException {
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
		final byte buffer[] = new byte[BUFFER_SIZE];
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
