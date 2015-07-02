package com.vertigo633.api.utils;

import com.vertigo633.api.entities.Size;
import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Vertigo633 on 29.06.2015.
 */
public class ResizerUtil {

    public static BufferedImage resize(String path, Size size) {

        Integer width = size.getWidth();
        Integer height = size.getHeight();

        BufferedImage image = getImageFromFileSystem(path);

        BufferedImage resizedImage =

                Scalr.resize(image, Scalr.Method.SPEED, Scalr.Mode.AUTOMATIC,
                        width, height, Scalr.OP_ANTIALIAS);
        return resizedImage;
    }

    public static BufferedImage getImageFromFileSystem(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static void saveResizedToFileSystem(String path, BufferedImage bufferedImage) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            File serverFile = new File(path);
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            ImageIO.write(bufferedImage, "jpg", baos);
            byte[] imageInByte = baos.toByteArray();

            stream.write(imageInByte);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String generateResizedFilePath(String rootFolder, Integer id, String size_name) {

        File dir = new File(rootFolder + File.separator + "resized");
        if (!dir.exists())
            dir.mkdirs();
        String path = dir.getAbsolutePath()
                + File.separator + id + "-" + size_name + ".jpg";
        return path;
    }

    public static void saveOriginalToFileSystem(String path, MultipartFile image) {

        byte[] bytes;
        try {
            bytes = image.getBytes();

            File serverFile = new File(path);
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateOriginalFilePath(String rootFolder, Integer id) {
        File dir = new File(rootFolder + File.separator + "original");
        if (!dir.exists())
            dir.mkdirs();
        String path = dir.getAbsolutePath()
                + File.separator + id + ".jpg";
        return path;
    }
}
