package com.wiily.pscosmeticos.PsAPI.domain.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class CategoryNotExist extends RuntimeException {
    String title = "The category is invalid!";
    String message;
    Map<String, Object> map = new HashMap<>();
    public CategoryNotExist(String message) {
        super(message);
        this.message = message;
    }
    public Map<String, Object> getMap() {
        map.put("title", title);
        map.put("message", message);
        return map;
    }

}
