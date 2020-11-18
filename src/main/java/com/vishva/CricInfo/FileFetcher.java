package com.vishva.CricInfo;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class FileFetcher {
    public List<File> getFilesFromFolder(String folderName) throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(folderName);
        assert resource != null;
        File folder = new File(resource.toURI());
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        return Arrays.asList(listOfFiles);
    }
}