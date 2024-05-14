package com.app;

import com.app.persistence.entity.PermissionEntity;
import com.app.persistence.entity.RoleEntity;
import com.app.persistence.entity.RoleEnum;
import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.UserRepository;
import lombok.Builder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.security.Permission;
import java.util.List;
import java.util.Set;

import static com.app.persistence.entity.PermissionEntity.*;

@SpringBootApplication
public abstract class SpringSecurityAppApplication {

	public static void main(String[] args) {SpringApplication.run(SpringSecurityAppApplication.class, args);}

	@Bean
    CommandLineRunner init(UserRepository userRepository){
		return args -> {
		PermissionEntity createPermission = PermissionEntity.builder()
				.name("CREATE")
				.build();


		PermissionEntity readPermission = PermissionEntity.builder()
				.name("READ")
				.build();

		PermissionEntity updatePermission = PermissionEntity.builder()
			.name("UPDATE")
			.build();


		PermissionEntity deletePermission = PermissionEntity.builder()
			.name("DELETE")
			.build();

		/*CREAR ROLES*/
			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission ))
					.build();

			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission, readPermission))
					.build();

			RoleEntity roleInvited = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITER)
					.permissionList(Set.of( readPermission ))
					.build();

			RoleEntity roleDeveloper = RoleEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission ))
					.build();


			/*CREATE USER*/

			UserEntity userSantiago = UserEntity.builder()
					.username("santiago")
					.password("1234")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userManu = UserEntity.builder()
					.username("Manuel")
					.password("1234567")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			UserEntity userAna = UserEntity.builder()
					.username("ana")
					.password("123498765")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleInvited))
					.build();

			UserEntity userAndre = UserEntity.builder()
					.username("Andres")
					.password("1235678")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();
			
			userRepository.saveAll(List.of(userManu, userAna, userSantiago, userAndre));
		};
				}
}
