package mk.finki.diplomska.rabota.diplomska.controllers;






import mk.finki.diplomska.rabota.diplomska.models.DBFile;
import mk.finki.diplomska.rabota.diplomska.payload.file.UploadFileResponse;
import mk.finki.diplomska.rabota.diplomska.services.DBFileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;


@RestController
@CrossOrigin("*")
@RequestMapping("/files")
public class FileController {




    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private DBFileStorageService dbFileStorageService;




    @PostMapping(value = "/uploadFile")
    public DBFile uploadFile(@RequestPart("file") MultipartFile file) throws IOException {
            DBFile dbFile = dbFileStorageService.storeFile(file);

            return dbFile;


    }





    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") String fileId) {
        // Load file from database

            DBFile dbFile = dbFileStorageService.getFile(fileId);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(dbFile.getFiletype()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFilename() + "\"")
                    .body(new ByteArrayResource(dbFile.getData()));


    }
    @GetMapping("/get/{id}")
    public DBFile getFile(@PathVariable("id") String id) {
        DBFile fileDB =dbFileStorageService.getFile(id);

        return fileDB;
    }


}
