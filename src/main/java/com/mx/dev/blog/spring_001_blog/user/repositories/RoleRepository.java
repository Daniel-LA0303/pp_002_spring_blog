package com.mx.dev.blog.spring_001_blog.user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mx.dev.blog.spring_001_blog.user.entities.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	@Query("select re from RoleEntity re where re.roleName = :name")
	Optional<RoleEntity> findRoleByName(String name);

}
