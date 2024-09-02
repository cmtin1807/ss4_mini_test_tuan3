package com.codegym.controller;

import com.codegym.model.Type;
import com.codegym.service.post.IPostService;
import com.codegym.service.type.ITypeService;
import com.codegym.service.type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/types")
public class TypeController {
    @Autowired
    private ITypeService typeService;

    @Autowired
    private IPostService postService;

    @GetMapping("")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("type/list");
        mav.addObject("types", typeService.findAll());
        return mav;

    }
    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView("type/create");
        mav.addObject("type", new Type() );
        return mav;
    }
    @PostMapping("/save")
    public ModelAndView save(Type type) {
        typeService.save(type);
        ModelAndView mav = new ModelAndView("redirect:/types");
        mav.addObject("message", "create success");
        return mav;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("type/edit");
        mav.addObject("type",typeService.findById(id).get());
        return mav;
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        typeService.deleteTypeById(id);
        return "redirect:/types";
    }
    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("type/view");
        mav.addObject("type",typeService.findById(id).get());
        return mav;
    }
    @PostMapping("/update")
    public ModelAndView update(Type type) {
        typeService.save(type);
        ModelAndView mav = new ModelAndView("redirect:/types");
        mav.addObject("message", "update success");
        return mav;
    }





}
