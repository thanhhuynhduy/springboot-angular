package com.example.demo.cache.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class XoaCacheEvent {
    private String value;
    private String key;
    private boolean evictAll;
}
