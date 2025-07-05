package com.mx.dev.blog.spring_001_blog.utils.dtos.servicesAWS.s3;

import java.net.URL;

public class Object {

	String name;
	String key;
	URL url;

	/**
	 * 
	 */
	public Object() {
	}

	/**
	 * @param name
	 * @param key
	 * @param url
	 */
	public Object(String name, String key, URL url) {
		this.name = name;
		this.key = key;
		this.url = url;
	}

	/**
	 * return the value of the property key
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * return the value of the property name
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * return the value of the property url
	 *
	 * @return the url
	 */
	public URL getUrl() {
		return url;
	}

	/**
	 * set the value of the property key
	 *
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * set the value of the property name
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * set the value of the property url
	 *
	 * @param url the url to set
	 */
	public void setUrl(URL url) {
		this.url = url;
	}

}
