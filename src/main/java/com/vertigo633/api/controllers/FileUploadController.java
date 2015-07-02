package com.vertigo633.api.controllers;

import com.vertigo633.api.components.Properties;
import com.vertigo633.api.entities.ImageOriginal;
import com.vertigo633.api.service.ImageResizerService;
import com.vertigo633.api.utils.ResizerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Vertigo633 on 28.06.2015.
 */
@Controller
@RequestMapping(value = "/api/v1/picture-resizer")
public class FileUploadController {

    @Autowired
    ImageResizerService imageResizerService;

    @Autowired
    Properties properties;

    @RequestMapping(value = "/{id}/upload", method = RequestMethod.POST)
    public
    @ResponseBody
    String uploadFileHandler(@RequestBody MultipartFile image, @PathVariable(value = "id") Integer image_id) {
        if (!image.isEmpty()) {
            try {
                ImageOriginal imageOriginal = imageResizerService.getOriginalImageById(image_id);
                if (imageOriginal != null) {
                    return "File already exists!";
                } else {
                    String rootPath = properties.rootFolder;
                    String path = ResizerUtil.generateOriginalFilePath(rootPath, image_id);
                    ResizerUtil.saveOriginalToFileSystem(path, image);
                    imageResizerService.saveImageOriginal(path, image_id);

                    return "You successfully uploaded file";
                }
            } catch (Exception e) {
                return "You failed to upload";
            }
        } else {
            return "You failed to upload, because the file was empty.";
        }
    }

    @RequestMapping(value = "/{id}/get/{size}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> getResizedImage(@PathVariable(value = "id") Integer image_id, @PathVariable(value = "size") String size) {
        byte[] imageResized = null;
        ImageOriginal imageOriginal = imageResizerService.getOriginalImageById(image_id);
        if (imageOriginal != null) {
            imageResized = imageResizerService.getImageResized(imageOriginal, size);
        }

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        ResponseEntity responseEntity = new ResponseEntity(imageResized, headers, HttpStatus.CREATED);
        return responseEntity;
    }
}
