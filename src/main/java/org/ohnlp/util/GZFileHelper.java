package org.ohnlp.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class GZFileHelper {
    
    public static String unzip(String filePath) {

        String opFilePath = filePath.substring(0, filePath.lastIndexOf("."));
        System.out.println("* filePath: " + filePath);
        System.out.println("* opFilePath: " + opFilePath);
        byte[] buffer = new byte[1024];

        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(fileIn);
            FileOutputStream fileOutputStream = new FileOutputStream(opFilePath);
            int bytes_read;

            while ((bytes_read = gZIPInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, bytes_read);
            }
 
            gZIPInputStream.close();
            fileOutputStream.close();
 
            System.out.println("* Decompressed successfully!");
 
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return opFilePath;
    }

    public static void main(String[] args) {
        String filePath = args[0];
        GZFileHelper.unzip(filePath);
    }
}