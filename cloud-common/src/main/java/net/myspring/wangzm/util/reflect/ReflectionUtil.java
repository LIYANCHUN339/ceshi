/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package net.myspring.wangzm.util.reflect;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.myspring.wangzm.util.collection.CollectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

public class ReflectionUtil  extends org.springside.modules.utils.reflect.ReflectionUtil{

	//递归获取所有Field
	public static void getFields(List<Field> fields, Class clazz) {
		for(Field field:clazz.getDeclaredFields()) {
			fields.add(field);
		}
		clazz = clazz.getSuperclass();
		if (!clazz.getName().equals(Object.class.getName())) {
			getFields(fields,clazz);
		}
	}

	public  static Map<String,Object> getPropertyMap(Object object){
		Map<String,Object> map= Maps.newHashMap();
		List<Field> fieldList=Lists.newArrayList();
		getFields(fieldList,object.getClass());
		for(Field field:fieldList){
			map.put(field.getName(),getFieldValue(object,field));
		}
		return map;
	}

	public static void getMethods(List<Method> methods, Class clazz) {
		for(Method method:clazz.getDeclaredMethods()) {
			methods.add(method);
		}
		clazz = clazz.getSuperclass();
		if (!clazz.getName().equals(Object.class.getName())) {
			getMethods(methods,clazz);
		}
	}

	public static void copyProperties(Object from,Object to){
		List<Field> formFieldList= Lists.newArrayList();
        List<Field> toFieldList= Lists.newArrayList();
		getFields(formFieldList,from.getClass());
        getFields(toFieldList,to.getClass());
        List<String> toFileName= CollectionUtil.extractToList(toFieldList,"name");
		for(Field field:formFieldList){
		    if(CollectionUtil.isNotEmpty(toFileName)&&toFileName.contains(field.getName())&&!Modifier.isFinal(field.getModifiers())){
                field.setAccessible(true);
		        Object value=getProperty(from,field.getName());
		        setFieldValue(to,field.getName(),value);
            }
        }
	}
}
