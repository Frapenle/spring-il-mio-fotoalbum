package org.java.foto.pojo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.URL;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Foto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NonNull
	@Size(min = 1, max = 40, message = "Il nome deve essere compreso tra 1 e 40 caratteri.")
	@Column(nullable = false)
	private String title;
	
	@Size(min = 5, max = 500, message = "La descrizione deve avere tra 5 e 500 caratteri.")
	@Column(nullable = false)
	private String description;
	
	@NotBlank(message = "Inserisci un url valido.")
	@URL(message = "Url non valido.")
	private String imageUrl;
	
	private boolean isVisible = true;
	private boolean isNewFoto;
	
    private LocalDateTime createdAt;
    
    @ManyToMany
    private Set<Category> categories;
	
	
    public Foto () {}
	public Foto(String title, String description, String imageUrl,
			boolean isVisible, Category...categories) {
		setTitle(title);
		setDescription(description);
		setImageUrl(imageUrl);
		setCreatedAt(createdAt);
		setCategories(categories);
		setVisible(isVisible);
	}

	public boolean isNewFoto() {
		return isNewFoto;
	}
	public void setNewFoto(boolean isNewFoto) {
		this.isNewFoto = isNewFoto;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = LocalDateTime.now();
	}
	public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return createdAt.format(formatter);
    }
	
	public Set<Category> getCategories() {
		return categories;
	}
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	@JsonSetter
	public void setCategories(Category[] categories) {
		setCategories(new HashSet<>(Arrays.asList(categories)));
	}
	public void addCategory(Category category) {
		getCategories().add(category);
	}
	public void removeCategory(Category category) {
		getCategories().remove(category);
	}
	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Foto)) return false;
		Foto fotoObject = (Foto) object;
		return getId() == fotoObject.getId();
	}
	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public String toString() {
		return "\nFoto Id: " + getId()
			+ "\nDescription: " + getDescription()
			+ "\nUrl: " + getImageUrl()
			+ "\nCategories: " + getCategories()
			+ "\nCreated: " + getCreatedAt();
	}
	
}
