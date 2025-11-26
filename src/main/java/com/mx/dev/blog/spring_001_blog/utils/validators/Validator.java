package com.mx.dev.blog.spring_001_blog.utils.validators;

import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

/**
 * general validator
 * 
 * @param <T>
 */
public interface Validator<T> {
	public void validate(T t) throws ServiceException;
}
