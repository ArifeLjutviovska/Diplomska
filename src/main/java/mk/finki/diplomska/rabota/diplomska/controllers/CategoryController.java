package mk.finki.diplomska.rabota.diplomska.controllers;


import mk.finki.diplomska.rabota.diplomska.models.Category;
import mk.finki.diplomska.rabota.diplomska.services.Impl.CategoryServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategories(){
        return this.categoryService.getCategories();
    }
}
