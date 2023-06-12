package org.java.foto.api.controller;

import java.util.List;

import org.java.foto.pojo.Foto;
import org.java.foto.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class FotoApiController {
	@Autowired
	private FotoService fotoService;
	
	@GetMapping("/fotos")
	public ResponseEntity<List<Foto>> getFotos(){
		List<Foto> fotos = fotoService.findAll();
		if(fotos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(fotos, HttpStatus.OK);
	}

	@GetMapping("fotos/search")
	public ResponseEntity<List<Foto>> getFotoByTitle(@RequestParam(required = false) String title){
		List<Foto> fotos = fotoService.findByTitleContaining(title);
		if(fotos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(fotos, HttpStatus.OK);
	}
}