package org.java.foto.service;

import java.util.List;
import java.util.Optional;

import org.java.foto.pojo.Foto;
import org.java.foto.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoService {
	@Autowired
	private FotoRepository fotoRepository;
	
	public List<Foto> findAll() {
		return fotoRepository.findAll();
	}
	
	public Optional<Foto> findById(int id) {
		return fotoRepository.findById(id);
	}
	
	public List<Foto> findByTitleContaining(String title) {
		return fotoRepository.findByTitleContaining(title);
	}
	
	public Foto save(Foto foto) {
		return fotoRepository.save(foto);
	}
	
	public void delete(Foto foto) {
		fotoRepository.delete(foto);
	}
	
	public List<Foto> findByUser(String username){
		return fotoRepository.findByUserUsername(username);
	}
	public List<Foto> findByTitleAndUser(String title, String username){
		return fotoRepository.findByTitleContainingAndUserUsername(title, username);
	}
	public Optional<Foto> findByIdAndUser(Integer id, String username){
		return fotoRepository.findByIdAndUserUsername(id, username);
	}
	
}
