package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Phone;
import com.codegym.service.CategoryService;
import com.codegym.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @GetMapping("/phone")
    public ModelAndView showListBook(@RequestParam("s") Optional<String> s, @PageableDefault(size = 5) Pageable pageable) {
        Page<Phone> phones;
        if (s.isPresent()) {
            Double d = Double.parseDouble(s.get());
            phones = phoneService.findAllByPrice(d, pageable);
        } else {
            phones = phoneService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("phone/list");
        modelAndView.addObject("phones", phones);
        return modelAndView;
    }

    @GetMapping("/create-phone")
    public ModelAndView showCreatePhone() {
        ModelAndView modelAndView = new ModelAndView("phone/create");
        modelAndView.addObject("phone", new Phone());
        return modelAndView;
    }

    @PostMapping("/create-phone")
    public ModelAndView savePhone(@ModelAttribute Phone phone) {
        phoneService.save(phone);
        ModelAndView modelAndView = new ModelAndView("phone/create");
        modelAndView.addObject("phone", new Phone());
        modelAndView.addObject("message", "Create phone successfully");
        return modelAndView;
    }

    @GetMapping("/edit-phone/{id}")
    public ModelAndView showEditPhone(@PathVariable Long id) {
        Phone phone = phoneService.findById(id);
        ModelAndView modelAndView = null;
        if (phone != null) {
            modelAndView = new ModelAndView("phone/edit");
            modelAndView.addObject("phone", phone);
        } else {
            modelAndView = new ModelAndView("error.404");
        }
        return modelAndView;
    }

    @PostMapping("/edit-phone")
    public ModelAndView updateBook(@ModelAttribute Phone phone) {
        phoneService.save(phone);
        ModelAndView modelAndView = new ModelAndView("phone/edit");
        modelAndView.addObject("phone", phone);
        modelAndView.addObject("message", "Edit phone successfully");
        return modelAndView;
    }

    @GetMapping("/delete-phone/{id}")
    public ModelAndView deleteBook(@PathVariable Long id) {
        phoneService.remove(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/phone");
        return modelAndView;
    }

    @GetMapping("/sort-by-price-asc")
    public ModelAndView getBookByPriceAsc(@PageableDefault(size = 5) Pageable pageable){
        Page<Phone> phones = phoneService.findAllByOrderByPriceAsc(pageable);
        ModelAndView modelAndView = new ModelAndView("phone/list");
        modelAndView.addObject("phones", phones);
        return modelAndView;
    }

    @GetMapping("/sort-by-price-desc")
    public ModelAndView getBookByPriceDesc(@PageableDefault(size = 5) Pageable pageable){
        Page<Phone> phones = phoneService.findAllByOrderByPriceDesc(pageable);
        ModelAndView modelAndView = new ModelAndView("phone/list");
        modelAndView.addObject("phones",phones);
        return modelAndView;
    }

    @GetMapping("/sort-by-date-asc")
    public ModelAndView getBookByDateAsc(@PageableDefault(size = 5) Pageable pageable){
        Page<Phone> phones = phoneService.findAllByOrderByDateOfPurchaseAsc(pageable);
        ModelAndView modelAndView = new ModelAndView("phone/list");
        modelAndView.addObject("phones", phones);
        return modelAndView;
    }

    @GetMapping("/sort-by-date-desc")
    public ModelAndView getBookByDateDesc(@PageableDefault(size = 5) Pageable pageable){
        Page<Phone> phones = phoneService.findAllByOrderByDateOfPurchaseDesc(pageable);
        ModelAndView modelAndView = new ModelAndView("phone/list");
        modelAndView.addObject("phones", phones);
        return modelAndView;
    }
}
