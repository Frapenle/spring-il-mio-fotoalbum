package org.java.foto.repository;

import org.java.foto.pojo.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FotoRepository  extends JpaRepository<Foto, Integer>{
	public List<Foto> findByTitleContaining(String title);
	List<Foto> findByUserUsername(String username);
	List<Foto> findByTitleContainingAndUserUsername(String title, String username);
	Optional<Foto> findByIdAndUserUsername(Integer id, String username);
}
