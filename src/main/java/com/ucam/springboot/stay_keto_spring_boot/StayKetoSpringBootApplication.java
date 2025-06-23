package com.ucam.springboot.stay_keto_spring_boot;
import com.ucam.springboot.stay_keto_spring_boot.entities.FoodItem;
import com.ucam.springboot.stay_keto_spring_boot.persistance.entity.PermissionEntity;
import com.ucam.springboot.stay_keto_spring_boot.persistance.entity.RoleEntity;
import com.ucam.springboot.stay_keto_spring_boot.persistance.entity.RoleEnum;
import com.ucam.springboot.stay_keto_spring_boot.persistance.entity.UserEntity;
import com.ucam.springboot.stay_keto_spring_boot.persistance.entity.repository.UserEntityRepository;
import com.ucam.springboot.stay_keto_spring_boot.repositories.FoodItemRepository;
import com.ucam.springboot.stay_keto_spring_boot.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;


@SpringBootApplication
public class StayKetoSpringBootApplication {

	public static void main(String[] args) {

		SpringApplication.run(StayKetoSpringBootApplication.class, args);
	}
		@Bean
		CommandLineRunner init(UserEntityRepository userEntityRepository) {
			return args -> {
				/* CREATE PERMISSIONS */
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

				/* Create ROLES */
				RoleEntity roleAdmin = RoleEntity.builder()
						.roleEnum(RoleEnum.ADMIN)
						.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
						.build();

				RoleEntity roleUser = RoleEntity.builder()
						.roleEnum(RoleEnum.USER)
						.permissionList(Set.of(createPermission, readPermission))
						.build();

				RoleEntity roleInvited = RoleEntity.builder()
						.roleEnum(RoleEnum.INVITED)
						.permissionList(Set.of(readPermission))
						.build();

				/* Create USERS */

				UserEntity userEliana = UserEntity.builder()
						.username("eliana")
						.password("$2a$10$PujhbkZVeOg.3UWUmQfnA.mSss/I1fx/EcSYo07TkBSepG1A3T2Vm")
						.isEnabled(true) // El usuario esta activo? si
						.accountNoExpired(true)
						.accountNoLocked(true)
						.credentialNoExpired(true)
						.roles(Set.of(roleAdmin))
						.build();

				UserEntity userVictor = UserEntity.builder()
						.username("victor")
						.password("$2a$10$PujhbkZVeOg.3UWUmQfnA.mSss/I1fx/EcSYo07TkBSepG1A3T2Vm")
						.isEnabled(true) // El usuario esta activo? si
						.accountNoExpired(true)
						.accountNoLocked(true)
						.credentialNoExpired(true)
						.roles(Set.of(roleUser))
						.build();

				userEntityRepository.saveAll(List.of(userEliana, userVictor));
			};
		}
}




//	@Bean
//	public CommandLineRunner demo(UserRepository repo) {
//		return args -> {
//			User user = new User();
//			user.setName("Prueba");
//			repo.save(user);
//
//		};
//	}


