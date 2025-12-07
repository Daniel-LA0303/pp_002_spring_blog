package com.mx.dev.blog.spring_001_blog.blog.services.dashboard.factory;

import java.util.EnumMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.blog.services.dashboard.strategy.BlogsByUserStrategy;
import com.mx.dev.blog.spring_001_blog.blog.services.dashboard.strategy.BlogsLikedByUserStrategy;
import com.mx.dev.blog.spring_001_blog.blog.services.dashboard.strategy.BlogsReadLaterByUserStrategy;
import com.mx.dev.blog.spring_001_blog.blog.services.dashboard.strategy.CategoriesFollowedByUserStrategy;
import com.mx.dev.blog.spring_001_blog.blog.services.dashboard.strategy.DashboardQueryStrategy;
import com.mx.dev.blog.spring_001_blog.blog.services.dashboard.strategy.FollowedsByUserStrategy;
import com.mx.dev.blog.spring_001_blog.blog.services.dashboard.strategy.FollowersByUserStrategy;
import com.mx.dev.blog.spring_001_blog.blog.utils.enums.DashboardQueryType;

@Service
public class DashboardStrategyFactory {

	private final Map<DashboardQueryType, DashboardQueryStrategy> strategies = new EnumMap<>(DashboardQueryType.class);

	public DashboardStrategyFactory(FollowedsByUserStrategy followedsByUserStrategy,
			FollowersByUserStrategy followersByUserStrategy, BlogsLikedByUserStrategy blogsLikedByUserStrategy,
			BlogsReadLaterByUserStrategy blogsReadLaterByUserStrategy, BlogsByUserStrategy blogsByUserStrategy,
			CategoriesFollowedByUserStrategy categoriesFollowedByUserStrategy) {
		strategies.put(DashboardQueryType.FOLLOWERS, followersByUserStrategy);
		strategies.put(DashboardQueryType.FOLLOWING, followedsByUserStrategy);
		strategies.put(DashboardQueryType.BLOGS_PUBLISHED, blogsByUserStrategy);
		strategies.put(DashboardQueryType.BLOGS_LIKED, blogsLikedByUserStrategy);
		strategies.put(DashboardQueryType.BLOGS_READ_LATER, blogsReadLaterByUserStrategy);
		strategies.put(DashboardQueryType.CATEGORIES_FOLLOWED, categoriesFollowedByUserStrategy);
	}

	public DashboardQueryStrategy getStrategy(String type) {
		if (type == null) {
			return null;
		}

		DashboardQueryType enumType;
		try {
			enumType = DashboardQueryType.valueOf(type.toUpperCase());
		} catch (IllegalArgumentException e) {
			return null;
		}

		return strategies.get(enumType);
	}

}
