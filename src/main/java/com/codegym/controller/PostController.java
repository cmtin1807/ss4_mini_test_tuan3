package com.codegym.controller;

import com.codegym.model.Post;
import com.codegym.model.PostForm;
import com.codegym.model.Type;
import com.codegym.service.post.IPostService;
import com.codegym.service.post.PostService;
import com.codegym.service.type.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("posts")
public class PostController {
    @Autowired
    private IPostService postService;
    @Autowired
    private ITypeService typeService;
    @ModelAttribute("types")
    public Iterable<Type> getTypes() {
        return typeService.findAll();
    }
    @GetMapping("")
    public ModelAndView index(@RequestParam(defaultValue = "") String search,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Post> posts;


        if (search.trim().isEmpty()) {
            posts = postService.findAll(pageable);
        } else {
            posts = postService.findAllByTitleContaining(search, pageable);
        }

        ModelAndView mav = new ModelAndView("post/list");
        mav.addObject("posts", posts);
        return mav;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("post", new Post());
        return "post/create";
    }

    @Value("${file-upload}")
    private String upload;
    @PostMapping("/create")
    public String save(PostForm postForm, RedirectAttributes redirectAttributes) {
        MultipartFile file = postForm.getImage();
        String fileName = file.getOriginalFilename();
        if (fileName != null && !fileName.isEmpty()) {
            try {
                File uploadDir = new File(upload);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                FileCopyUtils.copy(file.getBytes(), new File(upload + fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Post post = new Post();
        post.setId(postForm.getId());
        post.setTitle(postForm.getTitle());
        post.setDescription(postForm.getDescription());
        post.setContent(postForm.getContent());
        post.setType(postForm.getType()); // Đảm bảo rằng postForm.getType() trả về Type đối tượng hợp lệ
        post.setImage(fileName);
        postService.save(post);
        redirectAttributes.addFlashAttribute("message", "Post saved successfully");
        return "redirect:/posts";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id ){
        Optional<Post> post = postService.findById(id);
        if (post.isPresent()) {
            ModelAndView mav = new ModelAndView("post/edit");
            mav.addObject("post", post.get());
            return mav;
        }

        ModelAndView mav = new ModelAndView("redirect:/posts");
        mav.addObject("message", "form update fail");
        return mav;
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("postForm") PostForm postForm, RedirectAttributes redirectAttributes) {
        MultipartFile file = postForm.getImage();
        String fileName = file.getOriginalFilename();
        if (fileName != null && !fileName.isEmpty()) {
            try {
                File uploadDir = new File(upload);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                FileCopyUtils.copy(file.getBytes(), new File(upload + fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Post post = new Post();
        post.setId(postForm.getId());
        post.setTitle(postForm.getTitle());
        post.setDescription(postForm.getDescription());
        post.setContent(postForm.getContent());
        post.setType(postForm.getType()); // Đảm bảo rằng postForm.getType() trả về Type đối tượng hợp lệ
        post.setImage(fileName);
        postService.save(post);
        redirectAttributes.addFlashAttribute("message", "Post saved successfully");
        return "redirect:/posts";

    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        Optional<Post> post = postService.findById(id);
        if (post.isPresent()) {
            ModelAndView mav = new ModelAndView("post/delete");
            mav.addObject("post", post.get());
            return mav;
        }
        ModelAndView mav = new ModelAndView("redirect:/posts");

        return mav;
    }
    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute("post") Post post, RedirectAttributes redirectAttributes) {
        postService.delete(post.getId());
        redirectAttributes.addAttribute("message","delete success");
        return new ModelAndView("redirect:/posts");
    }
    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable Long id) {
        Optional<Post> post = postService.findById(id);
        if (post.isPresent()) {
            ModelAndView mav = new ModelAndView("post/view");
            mav.addObject("post", post.get());
            return mav;
        }
        ModelAndView mav = new ModelAndView("redirect:/posts");
        mav.addObject("message", "form view fail");
        return mav;
    }



}
