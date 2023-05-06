package com.xshaffter.marymod.updater;

import com.google.gson.Gson;
import com.xshaffter.marymod.MaryModClient;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ResourceDownloader {
    private final String VERSIONS_URLS = "https://www.dropbox.com/s/ops3ucpot7e6dxe/versions.json?dl=1";
    private final Path LOCAL_VERSIONS_PATH = Paths.get(String.valueOf(MaryModClient.getClientPath()), "marymod_versions.json");
    private final Path TEMP_VERSIONS_PATH = Paths.get(String.valueOf(MaryModClient.getClientPath()), "marymod_versions_temp.json");

    private Path saveFile(InputStream modZipFile) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(TEMP_VERSIONS_PATH.toFile())) {

            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = modZipFile.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            return TEMP_VERSIONS_PATH;
        }
    }

    private String downloadNewData() throws IOException {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(VERSIONS_URLS).openStream())) {
            return this.saveFile(bufferedInputStream).toString();
        }
    }

    private VersionHashMap loadJSON(String path) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(path)) {
            //noinspection unchecked
            return gson.fromJson(reader, VersionHashMap.class);
        } catch (FileNotFoundException e) {
            return new VersionHashMap();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateVersions() throws IOException {
        try {
            var zipManager = new ZipManager();
            zipManager.extract();
        } catch (IOException ignored) {

        }
        if (Files.exists(LOCAL_VERSIONS_PATH)) {
            Files.delete(LOCAL_VERSIONS_PATH);
        }
        Files.copy(TEMP_VERSIONS_PATH, LOCAL_VERSIONS_PATH);
    }

    public void CheckModpackVersion() throws IOException {
        this.downloadNewData();
        var localVersions = loadJSON(LOCAL_VERSIONS_PATH.toString());
        var tempVersions = loadJSON(TEMP_VERSIONS_PATH.toString());
        if (localVersions.equals(tempVersions)) {
            System.out.println("a");
        } else {
            cleanMods();
            updateVersions();
        }
        //noinspection ResultOfMethodCallIgnored
        TEMP_VERSIONS_PATH.toFile().delete();
        //noinspection ResultOfMethodCallIgnored
        new File("compressed.zip").delete();
    }

    private void cleanMods() {
        var modsFolder = new File("mods/");
        for (File modFile : Objects.requireNonNull(modsFolder.listFiles())) {
            System.out.println(modFile);
            //noinspection ResultOfMethodCallIgnored
            modFile.delete();
        }
    }

}
