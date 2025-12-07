package com.mx.dev.blog.spring_001_blog.category.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.dev.blog.spring_001_blog.category.entities.CategoryEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryFullInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryTopInfoDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserSimpleResponseDTO;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

	@Transactional
	@Modifying
	@Query("DELETE FROM CategoryUserFollowEntity cuf WHERE cuf.id.userId = :userId AND cuf.id.categoryId = :categoryId")
	void deleteFollowCategoryUser(@Param("userId") Long userId, @Param("categoryId") Long categoryId);

	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CategoryEntity c WHERE c.name = :name")
	boolean existsByName(@Param("name") String name);

	@Query("SELECT CASE WHEN COUNT(cuf) > 0 THEN TRUE ELSE FALSE END FROM CategoryUserFollowEntity cuf WHERE cuf.id.userId = :userId AND cuf.id.categoryId = :categoryId")
	boolean existsByUserIdAndCategoryFollowId(@Param("userId") Long userId, @Param("categoryId") Long categoryId);

	@Query("SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryFullInfoDTO( "
			+ "c.categoryId, c.name, c.description, c.color, COUNT(cb.id.blogId), c.longDescription ,c.createdAt) "
			+ "FROM CategoryEntity c " + "LEFT JOIN CategoryBlogEntity cb ON c.categoryId = cb.id.categoryId "
			+ "GROUP BY c.categoryId, c.name, c.description, c.color, c.createdAt")
	Page<CategoryFullInfoDTO> findAllCategoryFullInfo(Pageable pageable);

	// search category
	@Query("SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryFullInfoDTO( "
			+ "c.categoryId, c.name, c.description, c.color, COUNT(cb.id.blogId), c.longDescription, c.createdAt) "
			+ "FROM CategoryEntity c " + "LEFT JOIN CategoryBlogEntity cb ON c.categoryId = cb.id.categoryId "
			+ "WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%')) "
			+ "GROUP BY c.categoryId, c.name, c.description, c.color, c.longDescription, c.createdAt")
	Page<CategoryFullInfoDTO> findByNameContainingIgnoreCase(@Param("query") String query, Pageable pageable);

	@Query("SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryFullInfoDTO( "
			+ "c.categoryId, c.name, c.description, c.color, COUNT(cb.id.blogId), c.longDescription ,c.createdAt) "
			+ "FROM CategoryEntity c " + "LEFT JOIN CategoryBlogEntity cb ON c.categoryId = cb.id.categoryId "
			+ "JOIN CategoryUserFollowEntity cufe ON c.categoryId = cufe.id.categoryId "
			+ "WHERE cufe.id.userId = :userId "
			+ "GROUP BY c.categoryId, c.name, c.description, c.color, c.longDescription, c.createdAt")
	Page<CategoryFullInfoDTO> findCategoriesByFollowedUser(@Param("userId") Long userId, Pageable pageable);

	/**
	 * query to get category by name
	 * 
	 * @param name
	 * @return
	 */
	@Query("select ce from CategoryEntity ce where ce.name = :name")
	Optional<CategoryEntity> findCategoryByName(String name);

	@Query("SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryFullInfoDTO( " + "c.categoryId, "
			+ "c.name, " + "c.description, " + "c.color, " + "COUNT(cb.id.blogId), "
			+ " c.longDescription, c.createdAt) " + "FROM CategoryEntity c "
			+ "LEFT JOIN CategoryBlogEntity cb ON c.categoryId = cb.id.categoryId " + "WHERE c.name = :categoryName "
			+ "GROUP BY c.categoryId, c.name, c.description, c.color, c.createdAt")
	CategoryFullInfoDTO findCategoryFullInfoByName(@Param("categoryName") String categoryName);

	@Query("select ce from CategoryEntity ce where ce.categoryId in :ids")
	List<CategoryEntity> findListCategories(List<Long> ids);

	@Query("""
			    SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.category.CategoryTopInfoDTO(
			        c.categoryId,
			        c.name,
			        c.color,
			        COUNT(cuf.id.userId)
			    )
			    FROM CategoryEntity c
			    LEFT JOIN CategoryUserFollowEntity cuf ON cuf.id.categoryId = c.categoryId
			    GROUP BY c.categoryId, c.name, c.color
			    ORDER BY COUNT(cuf.id.userId) DESC
			""")
	Page<CategoryTopInfoDTO> findTopCategories(Pageable pageable);

	@Query("""
			SELECT new com.mx.dev.blog.spring_001_blog.utils.dtos.user.UserSimpleResponseDTO(
			    u.userId, u.username, u.email, ui.profilePicture, u.createdAt
			)
			FROM UserEntity u
			JOIN UserInfoEntity ui ON u.userId = ui.userId
			WHERE u.userId IN (
			    SELECT cuf.id.userId
			    FROM CategoryUserFollowEntity cuf
			    JOIN CategoryEntity c ON cuf.id.categoryId = c.categoryId
			    WHERE c.name = :categoryName
			)
			ORDER BY u.createdAt DESC
			""")
	List<UserSimpleResponseDTO> findTopUsersByCategory(@Param("categoryName") String categoryName, Pageable pageable);

	@Query("""
			    SELECT u.userId
			    FROM UserEntity u
			    JOIN UserInfoEntity ui ON u.userId = ui.userId
			    WHERE u.userId IN (
			        SELECT cuf.id.userId
			        FROM CategoryUserFollowEntity cuf
			        JOIN CategoryEntity c ON cuf.id.categoryId = c.categoryId
			        WHERE c.name = :categoryName
			    )
			    ORDER BY u.createdAt DESC
			""")
	List<Long> findUserFollowersIdsByCategory(@Param("categoryName") String categoryName);

	@Modifying
	@Query(value = "INSERT INTO category_user_follow_tbl (user_id, category_id, created_at) VALUES (:userId, :categoryId, :createdAt)", nativeQuery = true)
	void saveFollowCategoryUser(@Param("userId") Long userId, @Param("categoryId") Long categoryId,
			@Param("createdAt") LocalDateTime createdAt);

}
