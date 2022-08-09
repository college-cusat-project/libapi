package com.api.controller;

import com.api.helper.Helper;
import com.api.service.FileExporter;
import com.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;

@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private FileExporter fileExporter;

    @PostMapping("/file/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String result = new String();
        if (Helper.checkExcelFormat(file)) {
            result = this.productService.save(file);
        }
        return result;
    }


    @RequestMapping("/download/{json}")
    public ResponseEntity<InputStreamResource> downloadTextFileExample1(@PathVariable("json") String jsonToDownload) throws FileNotFoundException {
        String fileName = "json.txt";
        String fileContent = jsonToDownload;

        // Create text file
        Path exportedPath = fileExporter.export(fileContent, fileName);

        // Download file with InputStreamResource
        File exportedFile = exportedPath.toFile();
        FileInputStream fileInputStream = new FileInputStream(exportedFile);
        InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(exportedFile.length())
                .body(inputStreamResource);
    }

}
