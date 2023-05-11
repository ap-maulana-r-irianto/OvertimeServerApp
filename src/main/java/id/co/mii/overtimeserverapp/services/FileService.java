package id.co.mii.overtimeserverapp.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import id.co.mii.overtimeserverapp.exceptions.FileStorageException;
import id.co.mii.overtimeserverapp.exceptions.MyFileNotFoundException;
import id.co.mii.overtimeserverapp.models.File;
import id.co.mii.overtimeserverapp.models.Reimburse;
import id.co.mii.overtimeserverapp.repositories.FileRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileService {

    private FileRepository fileRepository;
    private ReimburseService reimburseService;

    public File storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            File files = new File();
            files.setFile_name(fileName);
            files.setFile_type(file.getContentType());
            files.setData(file.getBytes());

            return fileRepository.save(files);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public File getFile(String fileId) {
        return fileRepository.findById(Integer.parseInt(fileId))
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}
