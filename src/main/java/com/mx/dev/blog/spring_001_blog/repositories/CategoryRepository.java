package com.mx.dev.blog.spring_001_blog.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.dev.blog.spring_001_blog.entities.ctaegory.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CategoryEntity c WHERE c.name = :name")
	boolean existsByName(@Param("name") String name);

	/**
	 * query to get category by name
	 * 
	 * @param name
	 * @return
	 */
	@Query("select ce from CategoryEntity ce where ce.name = :name")
	Optional<CategoryEntity> findCategoryByName(String name);

	@Query("select ce from CategoryEntity ce where ce.categoryId in :ids")
	List<CategoryEntity> findListCategories(List<Long> ids);

}
