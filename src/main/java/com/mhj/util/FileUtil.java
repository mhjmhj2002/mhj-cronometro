package com.mhj.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil {
	
	public static File carregaFile (@SuppressWarnings("rawtypes") Class classe, String path, File file) throws IOException{
		InputStream in = null;
		OutputStream outStream = null;
		try {
			in = classe.getClassLoader().getResourceAsStream(path);
	        byte[] buffer = new byte[in.available()];
	        in.read(buffer);     
	        outStream = new FileOutputStream(file);
	        outStream.write(buffer);
	        return file;
		} finally {
			if (in != null) {
				in.close();
			}
			if (outStream != null) {
				outStream.close();
			}
		}
	}

}
