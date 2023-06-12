package org.java.foto.controller;

import java.util.List;
import java.util.Optional;

import org.java.foto.pojo.Category;
import org.java.foto.pojo.Foto;
import org.java.foto.service.CategoryService;
import org.java.foto.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/foto")
public class FotoController {
	@Autowired
	private FotoService fotoService;
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public String getHome(Model model) {
		List<Foto> fotos = fotoService.findAll();
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("fotos", fotos);
		return "foto/index";
	}
	
	@PostMapping
	public String getFotoByTitle(Model model,
			@RequestParam(required = false) String title) {
		List<Foto> fotos = fotoService.findByTitleContaining(title);
		List<Category> categories = categoryService.findAll();
		model.addAttribute("fotos", fotos);
		model.addAttribute("categories", categories);
		model.addAttribute("title", title);
		return "foto/index";
	}
	
	@GetMapping("/show/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		Optional<Foto> optFoto = fotoService.findById(id);
		List<Category> categories = categoryService.findAll();
		Foto foto = optFoto.get();
		model.addAttribute("foto", foto);
		model.addAttribute("categories", categories);
		return "foto/foto-show";
	}
	
	@GetMapping("/create")
	public String createFoto(Model model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("foto", new Foto());
		model.addAttribute("categories", categories);
		return "foto/foto-create";
	}
	
	@PostMapping("/create")
	public String storeFoto(Model model,
			@Valid @ModelAttribute Foto foto,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			for(ObjectError err : bindingResult.getAllErrors())
				System.err.println("Error: " + err.getDefaultMessage());
			model.addAttribute("foto", foto);
			model.addAttribute("errors", bindingResult);
			return "foto/foto-create";
		}
		fotoService.save(foto);
		return "redirect:/admin/foto";
	}
	
	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Optional<Foto> optFoto = fotoService.findById(id);
		List<Category> categories = categoryService.findAll();
		Foto foto = optFoto.get();
		model.addAttribute("categories", categories);
		model.addAttribute("foto", foto);
		return "foto/foto-update";
	}
	
	@PostMapping("edit/{id}")
	public String update(Model model,
			@PathVariable Integer id,
			@Valid @ModelAttribute Foto foto,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			for(ObjectError err : bindingResult.getAllErrors())
				System.err.println("Errors: " + err.getDefaultMessage());
			model.addAttribute("foto", foto);
			model.addAttribute("errors", bindingResult);
			return "foto/foto-update";
		}
		fotoService.save(foto);
		return "redirect:/admin/foto";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();
		fotoService.delete(foto);
		return "redirect:/admin/foto";
	}


}
