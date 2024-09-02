package com.codegym.service.post;

import com.codegym.model.Post;
import com.codegym.service.IGenerateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostService extends IGenerateService<Post> {
    Page<Post> findAllByTitleContaining(String title, Pageable pageable);
    Page<Post> findAll(Pageable pageable);
}
