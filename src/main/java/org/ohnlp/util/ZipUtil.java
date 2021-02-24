package org.ohnlp.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    public static byte[] getZippedResourcesFromPath(Path p) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ZipOutputStream zip = new ZipOutputStream(bos)) {
            Files.walk(p).filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry ze = new ZipEntry(p.relativize(path).toString());
                        try {
                            zip.putNextEntry(ze);
                            Files.copy(path, zip);
                            zip.closeEntry();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            zip.finish();
            zip.flush();
            bos.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
