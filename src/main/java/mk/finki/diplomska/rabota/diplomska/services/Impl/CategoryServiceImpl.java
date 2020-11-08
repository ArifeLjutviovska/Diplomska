package mk.finki.diplomska.rabota.diplomska.services.Impl;

import mk.finki.diplomska.rabota.diplomska.models.Category;
import mk.finki.diplomska.rabota.diplomska.repository.CategoryRepository;
import mk.finki.diplomska.rabota.diplomska.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }
}
