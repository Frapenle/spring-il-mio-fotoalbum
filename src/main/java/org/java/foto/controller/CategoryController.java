package org.java.foto.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.java.foto.pojo.Category;
import org.java.foto.pojo.Foto;
import org.java.foto.service.CategoryService;
import org.java.foto.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private FotoService fotoService;
	
	@GetMapping
	public String getIngredientIndex(Model model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "category/category";
	}
	
	@GetMapping("/create")
	public String createCategory(Model model) {
		Category category = new Category();
		model.addAttribute(category);
		return "category/category-create";
	}
	
	@PostMapping("/create")
	public String storeCategory(Model model, @ModelAttribute Category category) {
		categoryService.save(category);
		return "redirect:/admin/category";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCategory(@PathVariable Integer id) {
		Optional<Category> categoryOpt = categoryService.findById(id);
		if(categoryOpt.isPresent()) {
			Category category = categoryOpt.get();
			Set<Foto> fotos = category.getFotos();
			for(Foto foto : fotos) {
				foto.removeCategory(category);
				fotoService.save(foto);
			}
			categoryService.delete(category);
		}
		return "redirect:/admin/category";
	}
	
	
}
