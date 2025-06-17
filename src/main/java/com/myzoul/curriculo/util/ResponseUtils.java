package com.myzoul.curriculo.util;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class ResponseUtils {
    public static <T> ResponseEntity<T> of(Optional<T> optional) {
        return optional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}