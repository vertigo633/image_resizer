package com.vertigo633.api.service;

//import com.google.common.io.Files;

import com.vertigo633.api.components.Properties;
import com.vertigo633.api.dao.ImageOriginalDAO;
import com.vertigo633.api.dao.ImageResizedDAO;
import com.vertigo633.api.dao.SizeDAO;
import com.vertigo633.api.entities.ImageOriginal;
import com.vertigo633.api.entities.ImageResized;
import com.vertigo633.api.entities.Size;
import com.vertigo633.api.utils.ResizerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Vertigo633 on 28.06.2015.
 */
@Service
public class ImageResizerServiceImpl implements ImageResizerService {
    @Autowired
    ImageOriginalDAO imageOriginalDAO;
    @Autowired
    ImageResizedDAO imageResizedDAO;
    @Autowired
    SizeDAO sizeDAO;
    @Autowired
    Properties properties;

    public ImageOriginal getOriginalImageById(Integer image_id) {
        return imageOriginalDAO.getImageOriginalById(image_id);
    }

    public void saveImageOriginal(String path, Integer image_id) {
        ImageOriginal imageOriginal = new ImageOriginal();
        imageOriginal.setId(image_id);
        imageOriginal.setPath(path);
        imageOriginalDAO.save(imageOriginal);
    }

    public byte[] getImageResized(ImageOriginal imageOriginal, String size_name) {
        ImageResized imageResized = imageResizedDAO.get(imageOriginal, size_name);
        BufferedImage bufferedImage;
        if (imageResized == null) {
            Size size = sizeDAO.getSizeByName(size_name);
            bufferedImage = ResizerUtil.resize(imageOriginal.getPath(), size);
            String path = ResizerUtil.generateResizedFilePath(properties.rootFolder, imageOriginal.getId(), size_name);
            ResizerUtil.saveResizedToFileSystem(path, bufferedImage);
            imageResized = new ImageResized();
            imageResized.setPath(path);
            imageResized.setImageOriginal(imageOriginal);
            imageResized.setSize(size);
            imageResizedDAO.save(imageResized);

        } else {
            bufferedImage = ResizerUtil.getImageFromFileSystem(imageOriginal.getPath());
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            ImageIO.write(bufferedImage, "jpg", baos);
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] imageInByte = baos.toByteArray();

        return imageInByte;
    }
}
