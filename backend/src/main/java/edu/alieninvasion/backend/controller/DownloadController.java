package edu.alieninvasion.backend.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

/**
 * <h1>DownloadController</h1>
 * This is a controller class to download the game client.
 *
 * @author  Ran Qin and Shenquan Wang
 * @version 1.0
 * @since   2021-07-16
 */

@RestController
public class DownloadController {
    @GetMapping("/download")
    public ResponseEntity<FileSystemResource> getFile() throws FileNotFoundException {
        File file = new File("src/main/resources/assets/Alien Invasion.exe");
        if(file.exists()) {
            return export(file);
        }
        return null;
    }

    public ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) return null;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok().headers(headers).contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(file));
    }
}
