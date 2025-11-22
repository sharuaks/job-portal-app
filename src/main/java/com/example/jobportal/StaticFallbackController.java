package com.example.jobportal;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class StaticFallbackController {

    @GetMapping(value = "/webjars/popper.js/umd/popper.min.js")
    public ResponseEntity<InputStreamResource> servePopper() throws IOException {
        Resource r = new ClassPathResource("static/webjars/popper.js/umd/popper.min.js");
        if (!r.exists()) {
            return ResponseEntity.notFound().build();
        }
        long length = r.contentLength();
        InputStreamResource isr = new InputStreamResource(r.getInputStream());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/javascript")
                .contentLength(length)
                .body(isr);
    }
}
