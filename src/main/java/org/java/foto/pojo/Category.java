package org.java.foto.pojo;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
    private LocalDateTime createdAt;
    
    public Category() { }

	public Category(String name) {
		setName(name);
		setCreatedAt(createdAt);
	}
	
	@ManyToMany(mappedBy = "categories")
	@JsonBackReference
	private Set<Foto> fotos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = LocalDateTime.now();
	}
	
    public Set<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(Set<Foto> fotos) {
		this.fotos = fotos;
	}
	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Category)) return false;
		Category categoryObject = (Category) object;
		return getId() == categoryObject.getId();
	}
	@Override
	public int hashCode() {
		return getId();
	}

	@Override
    public String toString() {
    	return "\nCategory Id: " + getId()
		+ "\nName: " + getName()
		+ "\nCreated: " + getCreatedAt();
    }

}
