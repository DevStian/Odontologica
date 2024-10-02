package com.clinica.Odontologica;

import com.clinica.Odontologica.Entity.PermissionEntity;
import com.clinica.Odontologica.Entity.RoleEntity;
import com.clinica.Odontologica.Entity.RoleEnum;
import com.clinica.Odontologica.Entity.UserEntity;
import com.clinica.Odontologica.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class OdontologicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OdontologicaApplication.class, args);
		System.out.println("Hola, se ha iniciado mi App");
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			// Crear permisos
			PermissionEntity createPermission = PermissionEntity.builder() // Corregido
					.name("CREATE")
					.build(); // Corregido

			PermissionEntity readPermission = PermissionEntity.builder() // Corregido
					.name("READ")
					.build(); // Corregido

			PermissionEntity deletePermission = PermissionEntity.builder() // Corregido
					.name("DELETE")
					.build(); // Corregido

			PermissionEntity copyPermission = PermissionEntity.builder() // Corregido
					.name("COPY")
					.build(); // Corregido

			// Crear roles con permisos
			RoleEntity roleAdmin = RoleEntity.builder() // Corregido
					.rol(RoleEnum.ADMIN)
					.permissionsList(Set.of(createPermission, readPermission, deletePermission))
					.build(); // Corregido

			RoleEntity roleUser = RoleEntity.builder() // Corregido
					.rol(RoleEnum.USER)
					.permissionsList(Set.of(createPermission, readPermission))
					.build(); // Corregido

			RoleEntity roleDeveloper = RoleEntity.builder() // Corregido
					.rol(RoleEnum.DEVELOPER)
					.permissionsList(Set.of(createPermission, readPermission, deletePermission, copyPermission))
					.build(); // Corregido

			RoleEntity roleInvited = RoleEntity.builder() // Corregido
					.rol(RoleEnum.INVITED)
					.permissionsList(Set.of(readPermission))
					.build(); // Corregido

			UserEntity userSebas = UserEntity.builder()
					.username("Seba")
					.password("1234")
					.isEnabled(true)
					.accountNoExpired(true)
					.credentialNoExpired(true)
					.accountNoLocked(true)
					.roles(Set.of(roleDeveloper))
					.build();

			UserEntity userAgus = UserEntity.builder()
					.username("Agus")
					.password("1234")
					.isEnabled(true)
					.accountNoExpired(true)
					.credentialNoExpired(true)
					.accountNoLocked(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userNahuel = UserEntity.builder()
					.username("Nahuel")
					.password("1234")
					.isEnabled(true)
					.accountNoExpired(true)
					.credentialNoExpired(true)
					.accountNoLocked(true)
					.roles(Set.of(roleInvited))
					.build();

			UserEntity userEsteban = UserEntity.builder()
					.username("Esteban")
					.password("1234")
					.isEnabled(true)
					.accountNoExpired(true)
					.credentialNoExpired(true)
					.accountNoLocked(true)
					.roles(Set.of(roleUser))
					.build();

			userRepository.saveAll(List.of(userAgus, userEsteban, userNahuel, userSebas));
		};
	}
}
