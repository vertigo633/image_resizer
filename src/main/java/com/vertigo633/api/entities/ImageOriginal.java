package com.vertigo633.api.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Vertigo633 on 29.06.2015.
 */
@Entity
@Table
public class ImageOriginal {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ImageResized> getImageResizedList() {
        return imageResizedList;
    }

    public void setImageResizedList(List<ImageResized> imageResizedList) {
        this.imageResizedList = imageResizedList;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Id
    Integer id;
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="imageOriginal")
//    @JoinColumn(name="image_original")
    List<ImageResized> imageResizedList;
    String path;
}
