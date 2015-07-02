package com.vertigo633.api.dao;

import com.vertigo633.api.entities.ImageOriginal;

/**
 * Created by Vertigo633 on 28.06.2015.
 */
public interface ImageOriginalDAO {

    ImageOriginal getImageOriginalById(Integer image_id);

    void save(ImageOriginal imageOriginal);
}
