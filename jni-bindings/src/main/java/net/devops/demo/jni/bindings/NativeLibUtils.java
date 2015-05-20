package net.devops.demo.jni.bindings;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Slf4j
public class NativeLibUtils {

    public static void loadLibFromJar(String libName) {
        try {
            InputStream resourceAsStream = NativeLibUtils.class.getClassLoader().getResourceAsStream(libName + ".dll");
            if (resourceAsStream == null) {
                throw new IOException("library " + libName + " not found");
            }
            Path tempFile = Files.createTempFile(null, ".lib.tmp");
            long libLenght = Files.copy(resourceAsStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
            System.load(tempFile.toAbsolutePath().toString());
            log.info("loaded libraray {} with size {}", libName, libLenght);
        } catch (IOException e) {
            log.error("error loading native library " + libName, e);
        }
    }
}
