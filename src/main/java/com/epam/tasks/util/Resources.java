package com.epam.tasks.util;

import java.net.URL;

public class Resources {
    public static URL getResource(String name) {
        return Resources.class.getClassLoader().getResource(name);
    }
}
