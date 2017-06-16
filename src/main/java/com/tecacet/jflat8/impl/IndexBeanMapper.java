package com.tecacet.jflat8.impl;

import java.util.function.Supplier;

import com.tecacet.jflat8.BeanFactory;
import com.tecacet.jflat8.BeanMapper;
import com.tecacet.jflat8.RowRecord;

import jodd.bean.BeanUtil;

/**
 * Access Record properties by index
 * 
 * @author dimitri
 *
 * @param <T>
 */
public class IndexBeanMapper<T> implements BeanMapper<T> {

	private final String[] properties;
	private final Supplier<T> beanFactory;
	private final BeanUtil beanUtil = BeanUtil.declaredForced;

	public IndexBeanMapper(Class<T> type, String[] properties) {
		this(new BeanFactory<>(type), properties);
	}

	public IndexBeanMapper(Supplier<T> beanFactory, String[] properties) {
		super();
		this.beanFactory = beanFactory;
		this.properties = properties;
	}

	@Override
	public T apply(RowRecord record) {
		T bean = beanFactory.get();

		for (int i = 0; i < properties.length; i++) {
			String property = properties[i];
			if (property == null) {
				continue;
			}
			String token = record.get(i);
			beanUtil.setProperty(bean, property, token);
		}
		return bean;
	}

}
