package com.gratjar.notes.model;

import lombok.Data;

@Data
public class ResponseModel {

    private String body;
    private int status;
    private String message;
    
}
