package id.co.mii.overtimeserverapp.controllers;

import java.io.IOException;
import java.util.List;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import id.co.mii.overtimeserverapp.models.Reimburse;
import id.co.mii.overtimeserverapp.models.dto.requests.ReimburseRequest;
import id.co.mii.overtimeserverapp.services.FileStorageService;
import id.co.mii.overtimeserverapp.services.ReimburseService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/reimburse")
@PreAuthorize("hasRole('ADMIN')")
public class ReimburseController {
    
    private ReimburseService reimburseService;
    private FileStorageService fileStorageService;
    private static final Logger logger = LoggerFactory.getLogger(ReimburseController.class);

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "READ_ADMIN" yang dapat mengakses endpoint "/getAll"
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<Reimburse> getAll() {
        return reimburseService.getAll();
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "READ_ADMIN" yang dapat mengakses endpoint "/getById/{id}"
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("/{id}")
    public Reimburse getById(@PathVariable Integer id) {
        return reimburseService.getById(id);
    }

    // @PostMapping
    // public Reimburse create(@RequestBody Reimburse reimburse) {
    //     return reimburseService.create(reimburse);
    // }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "READ_ADMIN" yang dapat mengakses endpoint "/create"
    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public Reimburse create(ReimburseRequest reimburseRequest, @RequestParam("file") MultipartFile file) {
        return reimburseService.create(reimburseRequest, file);
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "UPDATE_ADMIN" yang dapat mengakses endpoint "/update"
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Reimburse update(
            @PathVariable Integer id,
            Reimburse reimburse,
            MultipartFile file) {
        return reimburseService.update(id, reimburse, file);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/approv/manager/{id}")
    public Reimburse approvManager(
            @PathVariable Integer id) {
        return reimburseService.approvManager(id);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/approv/hr/{id}")
    public Reimburse approvHr(
            @PathVariable Integer id) {
        return reimburseService.approvHr(id);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/reject/manager/{id}")
    public Reimburse rejectManager(
            @PathVariable Integer id,
            @RequestBody String description) {
        return reimburseService.rejectManager(id, description);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/reject/hr/{id}")
    public Reimburse rejectHr(
            @PathVariable Integer id,
            @RequestBody String description) {
        return reimburseService.rejectHr(id, description);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/paid/{id}")
    public Reimburse paid(
            @PathVariable Integer id) {
        return reimburseService.paid(id);
    }

    //memastikan hanya pengguna yang memiliki otorisasi Sebagai "DELETE_ADMIN" yang dapat mengakses endpoint "/delete/{id}"
    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Reimburse delete(@PathVariable Integer id) {
        return reimburseService.delete(id);
    }

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
}
