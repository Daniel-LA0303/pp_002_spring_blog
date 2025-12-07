package com.mx.dev.blog.spring_001_blog.blog.repositories;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.dev.blog.spring_001_blog.blog.entities.BlogEntity;
import com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogEngagementDTO;
import com.mx.dev.blog.spring_001_blog.category.entities.CategoryEntity;

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {

	// delete like from table
	@Transactional
	@Modifying
	@Query("DELETE FROM BlogUserLikeEntity bul WHERE bul.id.userId = :userId AND bul.id.blogId = :blogId")
	void deleteByUserIdAndBlogId(@Param("userId") Long userId, @Param("blogId") Long blogId);

	// delete read later from table
	@Transactional
	@Modifying
	@Query("DELETE FROM BlogUserReadEntity bur WHERE bur.id.userId = :userId AND bur.id.blogId = :blogId")
	void deleteByUserIdAndBlogIdRead(@Param("userId") Long userId, @Param("blogId") Long blogId);

	// exists blog by slug
	@Query("SELECT COUNT(b) > 0 FROM BlogEntity b WHERE b.slug = :slug")
	boolean existsBySlug(String slug);

	// check if exists like
	@Query("SELECT COUNT(bul) > 0 FROM BlogUserLikeEntity bul WHERE bul.id.userId = :userId AND bul.id.blogId = :blogId")
	boolean existsByUserIdAndBlogId(@Param("userId") Long userId, @Param("blogId") Long blogId);

	// check if exists read later
	@Query("SELECT COUNT(bur) > 0 FROM BlogUserReadEntity bur WHERE bur.id.userId = :userId AND bur.id.blogId = :blogId")
	boolean existsByUserIdAndBlogIdRead(@Param("userId") Long userId, @Param("blogId") Long blogId);

	@Query(value = """
			SELECT
			    b.blog_id,
			    b.title,
			    b.description,
			    b.created_at,
			    b.status,
			    b.slug,
			    b.blog_img_url,

			    u.user_id AS owner_id,
			    u.username AS owner_username,

			    COUNT(DISTINCT bult.user_id) AS likes,
			    COUNT(DISTINCT c.comment_id) AS comments,
			    COUNT(DISTINCT burt.user_id) AS reads,

			    CAST(
			        COALESCE(
			            json_agg(DISTINCT bult.user_id)
			            FILTER (WHERE bult.user_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS like_user_ids,

			    CAST(
			        COALESCE(
			            json_agg(DISTINCT burt.user_id)
			            FILTER (WHERE burt.user_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS read_user_ids,

			    CAST(
			        COALESCE(
			            json_agg(
			                DISTINCT jsonb_build_object(
			                    'category_id', cat.category_id,
			                    'name', cat.name,
			                    'description', cat.description,
			                    'color', cat.color
			                )
			            ) FILTER (WHERE cat.category_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS categories

			FROM blog_tbl b
			JOIN user_tbl u ON u.user_id = b.user_id
			LEFT JOIN blog_user_like_tbl bult ON b.blog_id = bult.blog_id
			LEFT JOIN comment_tbl c ON b.blog_id = c.blog_id
			LEFT JOIN blog_user_reada_tbl burt ON b.blog_id = burt.blog_id
			LEFT JOIN blog_category_tbl bc ON b.blog_id = bc.blog_id
			LEFT JOIN category_tbl cat ON bc.category_id = cat.category_id

			WHERE b.deleted = false
			AND b.status = 'PUBLISHED'

			GROUP BY b.blog_id, u.user_id, u.username
			ORDER BY b.created_at DESC
			""", countQuery = """
			SELECT COUNT(DISTINCT b.blog_id)
			FROM blog_tbl b
			WHERE b.deleted = false
			AND b.status = 'PUBLISHED'
			""", nativeQuery = true)
	Page<Object[]> findAllBlogCards(Pageable pageable);

	@Query(value = """
			SELECT
			    b.blog_id,
			    b.title,
			    b.description,
			    b.created_at,
			    b.status,
			    b.slug,
				b.blog_img_url,

			    u.user_id AS owner_id,
			    u.username AS owner_username,

			    COUNT(DISTINCT bult.user_id) AS likes,
			    COUNT(DISTINCT c.comment_id) AS comments,
			    COUNT(DISTINCT burt.user_id) AS reads,

			    CAST(
			        COALESCE(
			            json_agg(DISTINCT bult.user_id)
			            FILTER (WHERE bult.user_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS like_user_ids,

			    CAST(
			        COALESCE(
			            json_agg(DISTINCT burt.user_id)
			            FILTER (WHERE burt.user_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS read_user_ids,

			    CAST(
			        COALESCE(
			            json_agg(
			                DISTINCT jsonb_build_object(
			                    'category_id', cat.category_id,
			                    'name', cat.name,
			                    'description', cat.description,
			                    'color', cat.color
			                )
			            ) FILTER (WHERE cat.category_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS categories

			FROM blog_tbl b
			JOIN user_tbl u ON u.user_id = b.user_id
			JOIN blog_category_tbl bc_cat ON b.blog_id = bc_cat.blog_id
			JOIN category_tbl cat_filter ON bc_cat.category_id = cat_filter.category_id
			    AND cat_filter.name = :categoryName

			LEFT JOIN blog_user_like_tbl bult ON b.blog_id = bult.blog_id
			LEFT JOIN comment_tbl c ON b.blog_id = c.blog_id
			LEFT JOIN blog_user_reada_tbl burt ON b.blog_id = burt.blog_id
			LEFT JOIN blog_category_tbl bc ON b.blog_id = bc.blog_id
			LEFT JOIN category_tbl cat ON bc.category_id = cat.category_id

			WHERE b.deleted = false
			AND b.status = 'PUBLISHED'

			GROUP BY b.blog_id, u.user_id, u.username
			ORDER BY b.created_at DESC
			""", countQuery = """
			SELECT COUNT(DISTINCT b.blog_id)
			FROM blog_tbl b
			JOIN blog_category_tbl bc_cat ON b.blog_id = bc_cat.blog_id
			JOIN category_tbl cat_filter ON bc_cat.category_id = cat_filter.category_id
			WHERE cat_filter.name = :categoryName
			AND b.deleted = false
			AND b.status = 'PUBLISHED'
			""", nativeQuery = true)
	Page<Object[]> findBlogCardsByCategory(@Param("categoryName") String categoryName, Pageable pageable);

	@Query(value = """
			SELECT
			    b.blog_id,
			    b.title,
			    b.description,
			    b.created_at,
			    b.status,
			    b.slug,
			    b.blog_img_url,

			    u.user_id AS owner_id,
			    u.username AS owner_username,

			    COUNT(DISTINCT bult.user_id) AS likes,
			    COUNT(DISTINCT c.comment_id) AS comments,
			    COUNT(DISTINCT burt.user_id) AS reads,

			    CAST(
			        COALESCE(
			            json_agg(DISTINCT bult.user_id)
			            FILTER (WHERE bult.user_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS like_user_ids,

			    CAST(
			        COALESCE(
			            json_agg(DISTINCT burt.user_id)
			            FILTER (WHERE burt.user_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS read_user_ids,

			    CAST(
			        COALESCE(
			            json_agg(
			                DISTINCT jsonb_build_object(
			                    'category_id', cat.category_id,
			                    'name', cat.name,
			                    'description', cat.description,
			                    'color', cat.color
			                )
			            ) FILTER (WHERE cat.category_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS categories

			FROM blog_tbl b
			JOIN user_tbl u ON u.user_id = b.user_id

			LEFT JOIN blog_user_like_tbl bult ON b.blog_id = bult.blog_id
			LEFT JOIN comment_tbl c ON b.blog_id = c.blog_id
			LEFT JOIN blog_user_reada_tbl burt ON b.blog_id = burt.blog_id
			LEFT JOIN blog_category_tbl bc ON b.blog_id = bc.blog_id
			LEFT JOIN category_tbl cat ON bc.category_id = cat.category_id

			WHERE b.user_id = :userId

			GROUP BY b.blog_id, u.user_id, u.username
			ORDER BY b.created_at DESC
			""", countQuery = """
			SELECT COUNT(DISTINCT b.blog_id)
			FROM blog_tbl b
			WHERE b.user_id = :userId
			""", nativeQuery = true)
	Page<Object[]> findBlogCardsByOwnerId(@Param("userId") Long userId, Pageable pageable);

	@Query(value = """
			SELECT
			    b.blog_id,
			    b.title,
			    b.description,
			    b.created_at,
			    b.status,
			    b.slug,
			    b.blog_img_url,

			    u.user_id AS owner_id,
			    u.username AS owner_username,

			    COUNT(DISTINCT bult.user_id) AS likes,
			    COUNT(DISTINCT c.comment_id) AS comments,
			    COUNT(DISTINCT burt.user_id) AS reads,

			    CAST(
			        COALESCE(
			            json_agg(DISTINCT bult.user_id)
			            FILTER (WHERE bult.user_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS like_user_ids,

			    CAST(
			        COALESCE(
			            json_agg(DISTINCT burt.user_id)
			            FILTER (WHERE burt.user_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS read_user_ids,

			    CAST(
			        COALESCE(
			            json_agg(
			                DISTINCT jsonb_build_object(
			                    'category_id', cat.category_id,
			                    'name', cat.name,
			                    'description', cat.description,
			                    'color', cat.color
			                )
			            ) FILTER (WHERE cat.category_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS categories

			FROM blog_tbl b
			JOIN user_tbl u ON u.user_id = b.user_id

			LEFT JOIN blog_user_like_tbl bult ON b.blog_id = bult.blog_id
			LEFT JOIN comment_tbl c ON b.blog_id = c.blog_id
			LEFT JOIN blog_user_reada_tbl burt ON b.blog_id = burt.blog_id
			LEFT JOIN blog_category_tbl bc ON b.blog_id = bc.blog_id
			LEFT JOIN category_tbl cat ON bc.category_id = cat.category_id

			WHERE b.user_id = :userId

			GROUP BY b.blog_id, u.user_id, u.username
			ORDER BY b.created_at DESC
			""", countQuery = """
			SELECT COUNT(DISTINCT b.blog_id)
			FROM blog_tbl b
			WHERE b.user_id = :userId
			""", nativeQuery = true)
	Page<Object[]> findBlogCardsByUserId(@Param("userId") Long userId, Pageable pageable);

	@Query(value = """
			SELECT
			    b.blog_id,
			    b.title,
			    b.description,
			    b.created_at,
			    b.status,
			    b.slug,
			    b.blog_img_url,

			    u.user_id AS owner_id,
			    u.username AS owner_username,
			    COUNT(DISTINCT bult.user_id) AS likes,
			    COUNT(DISTINCT c.comment_id) AS comments,
			    COUNT(DISTINCT burt.user_id) AS reads,

			    CAST(
			        COALESCE(
			            json_agg(DISTINCT bult.user_id)
			            FILTER (WHERE bult.user_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS like_user_ids,

			    CAST(
			        COALESCE(
			            json_agg(DISTINCT burt.user_id)
			            FILTER (WHERE burt.user_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS read_user_ids,

			    CAST(
			        COALESCE(
			            json_agg(
			                DISTINCT jsonb_build_object(
			                    'category_id', cat.category_id,
			                    'name', cat.name,
			                    'description', cat.description,
			                    'color', cat.color
			                )
			            ) FILTER (WHERE cat.category_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS categories
			FROM blog_tbl b
			JOIN user_tbl u ON u.user_id = b.user_id
			JOIN blog_user_like_tbl bult_filter ON b.blog_id = bult_filter.blog_id

			LEFT JOIN blog_user_like_tbl bult ON b.blog_id = bult.blog_id
			LEFT JOIN comment_tbl c ON b.blog_id = c.blog_id
			LEFT JOIN blog_user_reada_tbl burt ON b.blog_id = burt.blog_id
			LEFT JOIN blog_category_tbl bc ON b.blog_id = bc.blog_id
			LEFT JOIN category_tbl cat ON bc.category_id = cat.category_id

			WHERE bult_filter.user_id = :userId
			AND b.deleted = false
			AND b.status = 'PUBLISHED'

			GROUP BY b.blog_id, u.user_id, u.username
			ORDER BY b.created_at DESC
			""", countQuery = """
			SELECT COUNT(DISTINCT b.blog_id)
			FROM blog_tbl b
			JOIN blog_user_like_tbl bult_filter ON b.blog_id = bult_filter.blog_id
			WHERE bult_filter.user_id = :userId
			AND b.deleted = false
			AND b.status = 'PUBLISHED'
			""", nativeQuery = true)
	Page<Object[]> findBlogCardsLikedByUser(@Param("userId") Long userId, Pageable pageable);

	//

	@Query(value = """
			SELECT
			    b.blog_id,
			    b.title,
			    b.description,
			    b.created_at,
			    b.status,
			    b.slug,
			    b.blog_img_url,

			    u.user_id AS owner_id,
			    u.username AS owner_username,

			    COUNT(DISTINCT bult.user_id) AS likes,
			    COUNT(DISTINCT c.comment_id) AS comments,
			    COUNT(DISTINCT burt.user_id) AS reads,

			    CAST(
			        COALESCE(
			            json_agg(DISTINCT bult.user_id)
			            FILTER (WHERE bult.user_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS like_user_ids,

			    CAST(
			        COALESCE(
			            json_agg(DISTINCT burt.user_id)
			            FILTER (WHERE burt.user_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS read_user_ids,

			    CAST(
			        COALESCE(
			            json_agg(
			                DISTINCT jsonb_build_object(
			                    'category_id', cat.category_id,
			                    'name', cat.name,
			                    'description', cat.description,
			                    'color', cat.color
			                )
			            ) FILTER (WHERE cat.category_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS categories

			FROM blog_tbl b
			JOIN user_tbl u ON u.user_id = b.user_id

			JOIN blog_user_reada_tbl burt_filter ON b.blog_id = burt_filter.blog_id

			LEFT JOIN blog_user_like_tbl bult ON b.blog_id = bult.blog_id
			LEFT JOIN comment_tbl c ON b.blog_id = c.blog_id
			LEFT JOIN blog_user_reada_tbl burt ON b.blog_id = burt.blog_id
			LEFT JOIN blog_category_tbl bc ON b.blog_id = bc.blog_id
			LEFT JOIN category_tbl cat ON bc.category_id = cat.category_id

			WHERE burt_filter.user_id = :userId
			AND b.deleted = false
			AND b.status = 'PUBLISHED'

			GROUP BY b.blog_id, u.user_id, u.username

			ORDER BY b.created_at DESC
			""",

			countQuery = """
					SELECT COUNT(DISTINCT b.blog_id)
					FROM blog_tbl b
					JOIN blog_user_reada_tbl burt_filter ON b.blog_id = burt_filter.blog_id
					WHERE burt_filter.user_id = :userId
					AND b.deleted = false
					AND b.status = 'PUBLISHED'
					""", nativeQuery = true)
	Page<Object[]> findBlogCardsReadLaterByUser(@Param("userId") Long userId, Pageable pageable);

	// search
	@Query("SELECT b FROM BlogEntity b " + "WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%'))")
	Page<BlogEntity> findByTitleContainingIgnoreCase(@Param("query") String query, Pageable pageable);

	// get categories by user followed pageables
	@Query("""
				SELECT c
			    FROM CategoryEntity c
			    JOIN CategoryUserFollowEntity cufe ON c.categoryId = cufe.id.categoryId
			    WHERE cufe.id.userId = :userId
			    ORDER BY cufe.createdAt DESC
			""")
	Page<CategoryEntity> findCategoriesByFollowedUser(@Param("userId") Long userId, Pageable pageable);

	@Query("SELECT b.id.userId FROM BlogUserLikeEntity b WHERE b.id.blogId = :blogId")
	List<Long> findUserIdsLikeByBlogId(@Param("blogId") Long blogId);

	// get a list of ids from likes

	// find ids
	@Query("SELECT b.id.blogId, b.id.userId FROM BlogUserLikeEntity b WHERE b.id.blogId IN :blogIds")
	List<Object[]> findUserIdsLikeByBlogIds(@Param("blogIds") List<Long> blogIds);

	// get a list of ids from read later
	@Query("SELECT b.id.userId FROM BlogUserReadEntity b WHERE b.id.blogId = :blogId")
	List<Long> findUserIdsReadByBlogId(@Param("blogId") Long blogId);

	// find ids
	@Query("SELECT b.id.blogId, b.id.userId FROM BlogUserReadEntity b WHERE b.id.blogId IN :blogIds")
	List<Object[]> findUserIdsReadByBlogIds(@Param("blogIds") List<Long> blogIds);

	// get engagement data from one blog by id
	@Query("SELECT new com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogEngagementDTO(" + "COALESCE(b.id, 0), "
			+ "COALESCE(COUNT(DISTINCT bult.id.userId), 0), " + "COALESCE(COUNT(DISTINCT c.commentId), 0), "
			+ "COALESCE(COUNT(DISTINCT burt.id.userId), 0)) "
			+ "FROM com.mx.dev.blog.spring_001_blog.blog.entities.BlogEntity b "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.comment.entities.CommentEntity c ON b.id = c.blogId "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.blog.entities.BlogUserLikeEntity bult ON b.id = bult.id.blogId "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.blog.entities.BlogUserReadEntity burt ON b.id = burt.id.blogId "
			+ "WHERE b.id = :blogId " + "GROUP BY b.id")
	BlogEngagementDTO getBlogEngagementData(@Param("blogId") Long blogId);

	// get engagement from multiples blogs by ids
	@Query("SELECT new com.mx.dev.blog.spring_001_blog.blog.utils.dto.BlogEngagementDTO(" + "b.blogId, "
			+ "COUNT(DISTINCT bult.id.userId), " + "COUNT(DISTINCT c.commentId), " + "COUNT(DISTINCT burt.id.userId)) "
			+ "FROM com.mx.dev.blog.spring_001_blog.blog.entities.BlogEntity b "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.comment.entities.CommentEntity c ON b.blogId = c.blogId "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.blog.entities.BlogUserLikeEntity bult ON b.blogId = bult.id.blogId "
			+ "LEFT JOIN com.mx.dev.blog.spring_001_blog.blog.entities.BlogUserReadEntity burt ON b.blogId = burt.id.blogId "
			+ "WHERE b.blogId IN :blogIds " + "GROUP BY b.blogId")
	List<BlogEngagementDTO> getBlogEngagementDataForBlogs(@Param("blogIds") List<Long> blogIds);

	// blog like
	@Query(value = "INSERT INTO blog_user_like_tbl (user_id, blog_id, created_at) VALUES (:userId, :blogId, :createdAt)", nativeQuery = true)
	@Modifying
	void insertBlogLike(@Param("userId") Long userId, @Param("blogId") Long blogId,
			@Param("createdAt") LocalDateTime createdAt);

	// blog read later
	@Query(value = "INSERT INTO blog_user_reada_tbl (user_id, blog_id, created_at) VALUES (:userId, :blogId, :createdAt)", nativeQuery = true)
	@Modifying
	void insertBlogRead(@Param("userId") Long userId, @Param("blogId") Long blogId,
			@Param("createdAt") LocalDateTime createdAt);

	@Query(value = """
			SELECT
			    b.blog_id,
			    b.title,
			    b.description,
			    b.created_at,
			    b.status,
			    b.slug,

			    u.user_id AS owner_id,
			    u.username AS owner_username,

			    COUNT(DISTINCT bult.user_id) AS likes,
			    COUNT(DISTINCT c.comment_id) AS comments,
			    COUNT(DISTINCT burt.user_id) AS reads,

			    CAST(
			        COALESCE(
			            json_agg(DISTINCT bult.user_id)
			            FILTER (WHERE bult.user_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS like_user_ids,

			    CAST(
			        COALESCE(
			            json_agg(DISTINCT burt.user_id)
			            FILTER (WHERE burt.user_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS read_user_ids,

			    CAST(
			        COALESCE(
			            json_agg(
			                DISTINCT jsonb_build_object(
			                    'category_id', cat.category_id,
			                    'name', cat.name,
			                    'description', cat.description,
			                    'color', cat.color
			                )
			            ) FILTER (WHERE cat.category_id IS NOT NULL),
			            '[]'
			        )
			    AS TEXT) AS categories

			FROM blog_tbl b
			JOIN user_tbl u ON u.user_id = b.user_id
			LEFT JOIN blog_user_like_tbl bult ON b.blog_id = bult.blog_id
			LEFT JOIN comment_tbl c ON b.blog_id = c.blog_id
			LEFT JOIN blog_user_reada_tbl burt ON b.blog_id = burt.blog_id
			LEFT JOIN blog_category_tbl bc ON b.blog_id = bc.blog_id
			LEFT JOIN category_tbl cat ON bc.category_id = cat.category_id

			WHERE b.deleted = false
			AND b.status = 'PUBLISHED'
			AND LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%'))

			GROUP BY b.blog_id, u.user_id, u.username
			ORDER BY b.created_at DESC
			""", countQuery = """
			SELECT COUNT(DISTINCT b.blog_id)
			FROM blog_tbl b
			WHERE b.deleted = false
			AND b.status = 'PUBLISHED'
			AND LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%'))
			""", nativeQuery = true)
	Page<Object[]> searchBlogCards(@Param("query") String query, Pageable pageable);

}
