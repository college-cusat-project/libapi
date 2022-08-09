package com.api.service;

import java.nio.file.Path;

public interface FileExporter {

    public Path export(String fileContent, String fileName);

}