package com.cmr.cloud.web;

import org.springframework.http.MediaType;
import java.nio.charset.Charset;

public class WebTestConstants {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    static final String ERROR_CODE_TODO_ENTRY_NOT_FOUND = "NOT_FOUND";
    static final String ERROR_CODE_VALIDATION_FAILED = "BAD_REQUEST";

    /**
     * Prevents instantiation.
     */
    private WebTestConstants() {}
}
