package com.codegym.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostForm {
    private Long id;

    private String title;
    private String content;
    private String description;
    private MultipartFile image;
    private Type type;

}
