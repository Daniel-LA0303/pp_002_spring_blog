package com.mx.dev.blog.spring_001_blog.blog.services.dashboard.orchestator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mx.dev.blog.spring_001_blog.blog.services.dashboard.factory.DashboardStrategyFactory;
import com.mx.dev.blog.spring_001_blog.blog.services.dashboard.strategy.DashboardQueryStrategy;
import com.mx.dev.blog.spring_001_blog.user.services.UserService;
import com.mx.dev.blog.spring_001_blog.utils.enums.MethodEnum;
import com.mx.dev.blog.spring_001_blog.utils.enums.ResponseStatus;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

@Service
public class DashboardOrchestratorService {

	private final DashboardStrategyFactory factory;
	private final UserService userService;

	public DashboardOrchestratorService(DashboardStrategyFactory factory, UserService userService) {
		this.factory = factory;
		this.userService = userService;
	}

	public Page<?> executeDashboardQuery(Long userId, String type, Pageable pageable) throws ServiceException {

		// 1. valid user
		userService.getOneUserOrThrow(userId);

		// 2. get strategy
		DashboardQueryStrategy strategy = factory.getStrategy(type);

		if (strategy == null) {
			throw new ServiceException(String.format("Param type with value %s not valid", type),
					ResponseStatus.NOT_FOUND.getHttpStatusCode(), "/api/blog", MethodEnum.GET);
		}

		return strategy.execute(userId, pageable);
	}
}
