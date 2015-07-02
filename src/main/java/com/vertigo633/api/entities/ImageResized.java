package com.vertigo633.api.entities;

//import org.hibernate.mapping.*;

import javax.persistence.*;

/**
 * Created by Vertigo633 on 29.06.2015.
 */
@Entity
@Table
public class ImageResized {
    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne
    @JoinColumn(name="imageoriginal_id")
    ImageOriginal imageOriginal;

    @OneToOne
    @JoinColumn(name="size_name")
    Size size;
    String path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ImageOriginal getImageOriginal() {
        return imageOriginal;
    }

    public void setImageOriginal(ImageOriginal imageOriginal) {
        this.imageOriginal = imageOriginal;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
