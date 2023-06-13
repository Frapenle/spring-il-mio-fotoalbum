package org.java.foto.controller;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.java.foto.pojo.Category;
import org.java.foto.pojo.Foto;
import org.java.foto.pojo.auth.User;
import org.java.foto.service.CategoryService;
import org.java.foto.service.FotoService;
import org.java.foto.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String getHome(Model model, Authentication authentication) {
		String user = authentication.getName();
		
//		List<Foto> fotos = fotoService.findAll();
		List<Foto> fotos = fotoService.findByUser(user);
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("fotos", fotos);
		return "foto/index";
	}
	
	@PostMapping
	public String getFotoByTitle(Model model,
			@RequestParam(required = false) String title,
			Authentication authentication) {
		String user = authentication.getName();
		
//		List<Foto> fotos = fotoService.findByTitleContaining(title);
		List<Foto> fotos = fotoService.findByTitleAndUser(title, user);
		List<Category> categories = categoryService.findAll();
		model.addAttribute("fotos", fotos);
		model.addAttribute("categories", categories);
		model.addAttribute("title", title);
		return "foto/index";
	}
	
	@GetMapping("/show/{id}")
	public String show(Model model, @PathVariable("id") Integer id,
			Authentication authentication) {
		String user = authentication.getName();
		
//		Optional<Foto> optFoto = fotoService.findById(id);
		Optional<Foto> optFoto = fotoService.findByIdAndUser(id, user);
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
		model.addAttribute("isNewFoto", true);
		model.addAttribute("categories", categories);
		return "foto/foto-create";
	}
	
	@PostMapping("/create")
	public String storeFoto(Model model,
			Authentication authentication,
			@Valid @ModelAttribute Foto foto,
			BindingResult bindingResult) {
		User user = (User) authentication.getPrincipal();
		
		if(bindingResult.hasErrors()) {
			for(ObjectError err : bindingResult.getAllErrors())
				System.err.println("Error: " + err.getDefaultMessage());
			List<Category> categories = categoryService.findAll();
			model.addAttribute("foto", foto);
			model.addAttribute("errors", bindingResult);
			model.addAttribute("categories", categories);
			return "foto/foto-create";
		}
		foto.setUser(user);
		fotoService.save(foto);
		return "redirect:/admin/foto";
	}
	
	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model,
			Authentication authentication) {
		String user = authentication.getName();
		
//		Optional<Foto> optFoto = fotoService.findById(id);
		Optional<Foto> optFoto = fotoService.findByIdAndUser(id, user);
		List<Category> categories = categoryService.findAll();
		Foto foto = optFoto.get();
		model.addAttribute("isNewFoto", false);
		model.addAttribute("categories", categories);
		model.addAttribute("foto", foto);
		return "foto/foto-update";
	}
	
	@PostMapping("edit/{id}")
	public String update(Model model,
			@PathVariable Integer id,
			@Valid @ModelAttribute Foto foto,
			BindingResult bindingResult,
			Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		if(bindingResult.hasErrors()) {
			for(ObjectError err : bindingResult.getAllErrors())
				System.err.println("Errors: " + err.getDefaultMessage());
			List<Category> categories = categoryService.findAll();
			model.addAttribute("foto", foto);
			model.addAttribute("errors", bindingResult);
			model.addAttribute("categories", categories);
			return "foto/foto-update";
		}
		foto.setUser(user);
		fotoService.save(foto);
		return "redirect:/admin/foto";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, Authentication authentication) {
		String user = authentication.getName();
//		Optional<Foto> optFoto = fotoService.findById(id);
		Optional<Foto> optFoto = fotoService.findByIdAndUser(id, user);
		Foto foto = optFoto.get();
		fotoService.delete(foto);
		return "redirect:/admin/foto";
	}


}
