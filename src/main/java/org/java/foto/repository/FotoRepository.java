package org.java.foto.repository;

import org.java.foto.pojo.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FotoRepository  extends JpaRepository<Foto, Integer>{
	public List<Foto> findByTitleContaining(String title);
}
