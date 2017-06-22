package com.tecacet.jflat8.impl;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.tecacet.jflat8.BeanFactory;
import com.tecacet.jflat8.BeanMapper;
import com.tecacet.jflat8.RowRecord;

import jodd.bean.BeanUtil;

/**
 * Maps properties based on the file's header 
 * 
 * @author dimitri
 *
 * @param <T>
 */
public class HeaderBeanMapper<T> implements BeanMapper<T> {

	private final BeanUtil beanUtil = BeanUtil.declaredForcedSilent;
	private final Map<String, String> properties;
	private final Supplier<T> beanFactory;

	public HeaderBeanMapper(Class<T> type, Map<String, String> properties) {
		this(new BeanFactory<>(type), properties);
	}

	public HeaderBeanMapper(Class<T> type, String[] header, String[] properties) {
		super();
		this.beanFactory = new BeanFactory<>(type);
		this.properties = toMap(header, properties);
	}

	public HeaderBeanMapper(Supplier<T> beanFactory, Map<String, String> properties) {
		this.beanFactory = beanFactory;
		this.properties = properties;
	}

	private Map<String, String> toMap(String[] header, String[] properties) {
		return IntStream.range(0, header.length).mapToObj(i -> Integer.valueOf(i))
				.collect(Collectors.toMap(i -> header[i], i -> properties[i]));
	}

	@Override
	public T apply(RowRecord record) {
		T bean = beanFactory.get();
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			String header = entry.getKey();
			String property = entry.getValue();
			String token = record.get(header);
			beanUtil.setProperty(bean, property, token);
		}
		return bean;
	}

}
