package com.DDN.login.controller;

import com.DDN.login.model.FileInfo;
import com.DDN.login.security.service.FileStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fileUpload")
public class FileUploadController {
    @Autowired
    FileStoreService fileStoreService;

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file")MultipartFile file){
        return fileStoreService.store(file);
    }

    @GetMapping("/")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = fileStoreService.loadAll().map(path -> {
            String fileName = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "getfile", path.getFileName().toString()).build().toString();
            return new FileInfo(fileName, url);
        }).collect(Collectors.toList());
//        Stream<Path> pathStream = fileStoreService.loadAll();
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file = fileStoreService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\" ").body(file);
    }

}
