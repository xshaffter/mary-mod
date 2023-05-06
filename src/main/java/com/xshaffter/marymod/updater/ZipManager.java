package com.xshaffter.marymod.updater;

import com.xshaffter.marymod.MaryModClient;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipManager {
    @SuppressWarnings("FieldCanBeLocal")
    private final String MODS_URL = "https://www.dropbox.com/s/g7284kk3p78d7dy/marycraft.zip?dl=1";
    @SuppressWarnings("FieldCanBeLocal")
    private final int BUFFER_SIZE = 4096;

    public void extract() throws IOException {
        final String zipPath = getInstallationData();
        extractEntries(zipPath, MaryModClient.getClientPath());
    }

    private String saveFile(InputStream modZipFile) throws IOException {
        final String path = "compressed.zip";
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {

            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = modZipFile.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            return path;
        }
    }

    private void extractEntries(final String zipFile, final Path path) throws IOException {
        ZipInputStream zipData = new ZipInputStream(new FileInputStream(zipFile));
        ZipEntry zipEntry;
        while ((zipEntry = zipData.getNextEntry()) != null) {
            Path filePath = Paths.get(String.valueOf(path), zipEntry.getName());
            File dir = new File(filePath.toUri());
            if (zipEntry.isDirectory()) {
                //noinspection ResultOfMethodCallIgnored
                dir.mkdirs();
            } else {
                //noinspection ResultOfMethodCallIgnored
                dir.getParentFile().mkdirs();
                extractFile(zipData, filePath);
            }
            zipData.closeEntry();
        }
        zipData.close();
    }

    public String getInstallationData() throws IOException {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(MODS_URL).openStream())) {
            return this.saveFile(bufferedInputStream);
        }
    }

    private void extractFile(ZipInputStream zipIn, Path filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath.toFile()));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
}
