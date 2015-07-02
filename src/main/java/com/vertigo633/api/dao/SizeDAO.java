package com.vertigo633.api.dao;

import com.vertigo633.api.entities.Size;

/**
 * Created by Vertigo633 on 30.06.2015.
 */
public interface SizeDAO {
    Size getSizeByName(String size_name);

    void save(Size size);

}
