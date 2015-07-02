package com.vertigo633.api.dao;

import com.vertigo633.api.entities.ImageOriginal;
import com.vertigo633.api.entities.ImageResized;

/**
 * Created by Vertigo633 on 30.06.2015.
 */
public interface ImageResizedDAO {
    ImageResized get(ImageOriginal imageOriginal, String size);

    void save(ImageResized imageResized);
}
