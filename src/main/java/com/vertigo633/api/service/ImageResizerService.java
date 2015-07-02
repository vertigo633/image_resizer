package com.vertigo633.api.service;

import com.vertigo633.api.entities.ImageOriginal;

/**
 * Created by Vertigo633 on 28.06.2015.
 */
public interface ImageResizerService {
    ImageOriginal getOriginalImageById(Integer image_id);

    void saveImageOriginal(String path, Integer image_id);

    byte[] getImageResized(ImageOriginal imageOriginal, String size_name);

}
