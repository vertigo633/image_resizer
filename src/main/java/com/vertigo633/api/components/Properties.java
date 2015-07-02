package com.vertigo633.api.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Vertigo633 on 30.06.2015.
 */
@Component
@PropertySource("WEB-INF/jdbc.properties")
public class Properties {

    @Value("${images.rootFolder}")
    public String rootFolder;

}
