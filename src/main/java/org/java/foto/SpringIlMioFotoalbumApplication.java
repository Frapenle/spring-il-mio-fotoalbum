package org.java.foto;

import java.util.Arrays;
import java.util.List;

import org.java.foto.pojo.Category;
import org.java.foto.pojo.Foto;
import org.java.foto.pojo.Message;
import org.java.foto.pojo.auth.Role;
import org.java.foto.pojo.auth.User;
import org.java.foto.service.CategoryService;
import org.java.foto.service.FotoService;
import org.java.foto.service.MessageService;
import org.java.foto.service.auth.RoleService;
import org.java.foto.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Autowired
	private FotoService fotoService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MessageService messageService;

	@Override
	public void run(String... args) throws Exception {

		final String pass = new BCryptPasswordEncoder().encode("pass");
//		ROLES
		Role roleUser = new Role("USER");
		Role roleAdmin = new Role("ADMIN");
		Role roleSuperAdmin = new Role("SUPERADMIN");
		roleService.save(roleUser);
		roleService.save(roleAdmin);
		roleService.save(roleSuperAdmin);
//		USERS
		User userUser = new User("user", pass, roleUser);
		User userAdmin = new User("admin", pass, roleAdmin);
		User superAdmin = new User("superAdmin", pass, roleSuperAdmin);
		User marco = new User("marco", pass, roleAdmin);
		userService.save(userUser);
		userService.save(userAdmin);
		userService.save(superAdmin);
		userService.save(marco);
		// CATEGORIES
		Category paesaggio = new Category("paesaggio");
		Category matrimonio = new Category("matrimonio");
		Category ritratto = new Category("ritratto");
		Category street = new Category("street");
		categoryService.save(street);
		categoryService.save(paesaggio);
		categoryService.save(matrimonio);
		categoryService.save(ritratto);

//		FOTO
		List<Foto> foto = Arrays.asList(new Foto("Tramonto.", "Incantevole tramonto in una calda giornata di Luglio.",
				"https://images.unsplash.com/photo-1685435467091-68fb9e8d5381?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2670&q=80",
				false, marco, matrimonio),
				new Foto("Matrimonio 1.", "Foto di matrimonio.",
						"https://images.unsplash.com/photo-1609151162377-794faf68b02f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2671&q=80",
						true, userAdmin, matrimonio),
				new Foto("Anziano", "Street photography.",
						"https://images.unsplash.com/photo-1556477631-78689fffc8f0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2145&q=80",
						true, userAdmin, ritratto, street),
				new Foto("Cabina telefonica londinese.",
						"Una cabina telefonica rossa a Londra, simbolo dell'iconica cultura britannica.",
						"https://images.unsplash.com/photo-1599557041284-7e2a15610388?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2787&q=80",
						true, marco, street),
				new Foto("Street", "Attraversamento pedonale.",
						"https://images.unsplash.com/photo-1525095182007-3874c4e2b38b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=687&q=80",
						true, marco, paesaggio, street),
				new Foto("Matrimonio 2.", "Coppia di sposi.",
						"https://images.unsplash.com/photo-1604846832591-5aea9d89096b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2670&q=80",
						true, userAdmin, matrimonio),
				new Foto("Strisce pedonali.", "Strisce pedonali in una notte piovosa.",
						"https://images.unsplash.com/photo-1620259570543-31964aa22586?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=687&q=80",
						false, street),
				new Foto("Street 2.", "Grattacieli notturni.",
						"https://images.unsplash.com/photo-1604585591073-445b86c4e1b5?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=687&q=80",
						true, street));
		for (Foto f : foto) {
			fotoService.save(f);
		}

//		Test messagge
			Message mess = new Message("email@mail.com", "Testo del messaggio");
			messageService.save(mess);
			System.out.println(mess);

	}
}
