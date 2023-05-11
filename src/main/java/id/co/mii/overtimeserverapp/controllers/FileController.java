package id.co.mii.overtimeserverapp.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import id.co.mii.overtimeserverapp.models.dto.responses.UploadFileResponse;
import id.co.mii.overtimeserverapp.services.FileStorageService;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    // @Autowired
    // private FileService fileService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    // @PostMapping("/uploadFile")
    // public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
    //     File files = fileService.storeFile(file);

    //     String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
    //             .path("/downloadFile/")
    //             .path(files.getFile_name())
    //             .toUriString();

    //     return new UploadFileResponse(files.getFile_name(), fileDownloadUri,
    //             file.getContentType(), file.getSize());
    // }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    // @GetMapping("/downloadFile/{fileId}")
    // public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
    //     // Load file from database
    //     File file = fileService.getFile(fileId);

    //     return ResponseEntity.ok()
    //             .contentType(MediaType.parseMediaType(file.getFile_type()))
    //             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFile_name() + "\"")
    //             .body(new ByteArrayResource(file.getData()));
    // }
}
