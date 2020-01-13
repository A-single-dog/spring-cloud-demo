package com.spring.cloud.user.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;


/**
 * 复制工具类
 * @user zds
 * 2020年1月13日下午3:41:35
 */
@SuppressWarnings("unchecked")
public class BeanUtil
{
	private static Logger log = LoggerFactory.getLogger(BeanUtil.class);  

    @SuppressWarnings("serial")
    private final static List<Class<?>> PrimitiveClasses = new ArrayList<Class<?>>()
    {
        {
            add(Long.class);
            add(Double.class);
            add(Integer.class);
            add(String.class);
            add(Boolean.class);
            add(Date.class);
            add(java.sql.Date.class);
        }
    };
     
    private final static boolean _IsPrimitive(Class<?> cls)
    {
        return cls.isPrimitive() || PrimitiveClasses.contains(cls);
    }
     
    /**
     * jjf
     * @param fromObj
     * @param toObjClazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T copyObject(Object fromObj, Class<T> toObjClazz)
    {
        try
        {
             
            Class<?> fromObjClazz = fromObj.getClass();
            if (_IsPrimitive(toObjClazz))
                return (T)fromObj;
             
            T toObj = toObjClazz.newInstance();
             
            Field[] fields = toObjClazz.getDeclaredFields();
             
            for (Field toF : fields)
            {
                try
                {
                     
                    int mod = toF.getModifiers();
                    if (Modifier.isFinal(mod) || Modifier.isStatic(mod))
                        continue;
                     
                    String toFieldName = toF.getName();
                    String fromFieldName;
                    Mapping mapping = toF.getAnnotation(Mapping.class);
                     
                    if (mapping == null || mapping.name() == null || mapping.name().trim().equals(""))
                        fromFieldName = toFieldName;
                    else
                        fromFieldName = mapping.name();
                     
                    toF.setAccessible(true);
                    Field fromF = fromObjClazz.getDeclaredField(fromFieldName);
                    fromF.setAccessible(true);
                    toF.set(toObj, fromF.get(fromObj));
                }
                catch (Exception e)
                {
                    if (e instanceof IllegalArgumentException)
                        e.printStackTrace();
                }
            }
            return toObj;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
         
    }
     
    /**
     * jjf
     * @param fromObjList
     * @param toObjClazz
     * @return
     */
    public static <T> List<T> copyList(List<?> fromObjList, Class<T> toObjClazz)
    {
        List<T> toObjList = new ArrayList<T>(fromObjList.size());
         
        for (int i = 0; i < fromObjList.size(); i++)
        {
            toObjList.add(copyObject(fromObjList.get(i), toObjClazz));
        }
        return toObjList;
    }
     
    public static <T> Map<String, T> copyMap(Map<String, ?> fromObjMap, Class<T> toObjClazz)
    {
        Map<String, T> toObjMap = new HashMap<String, T>(fromObjMap.size());
        Iterator<String> iter = fromObjMap.keySet().iterator();
        while (iter.hasNext())
        {
            String key = iter.next();
            Object fromObj = fromObjMap.get(key);
            toObjMap.put(key, copyObject(fromObj, toObjClazz));
        }
        return toObjMap;
    }
    public static <T> List<T> copyListCastMap(List<?> mapList, Class<T> toObjClass)
    {
        List<T> toObjList = new ArrayList<T>(mapList.size());
        for (Object map : mapList)
        {
        	Map<String,Object> newMap=(Map<String,Object>)map;
            toObjList.add(copyMapToBean(newMap, toObjClass));
        }
        return toObjList;
    }
    public static <T> List<T> copyListMap(List<Map<String, ?>> mapList, Class<T> toObjClass)
    {
        List<T> toObjList = new ArrayList<T>(mapList.size());
        for (Map<String, ?> map : mapList)
        {
            toObjList.add(copyMapToBean(map, toObjClass));
        }
        return toObjList;
    }
    public static <T> T copyMapToBean(Map<String, ?> map, Class<T> toObjClass)
    {
        try
        {
            Set<String> set = map.keySet();
            T objT = toObjClass.newInstance();
            for (String key : set)
            {
                try
                {
                    Object value = map.get(key);
                    Field toF = toObjClass.getDeclaredField(key);
                    toF.setAccessible(true);
                    toF.set(objT, value);
                }
                catch(Exception e)
                {
                    //鍚冩帀杩欎釜寮傚父
                }
            }
            return objT;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public static Map<String, Object> beanToMap(Object bean){
    	Class<?> clazz = bean.getClass();  
    	Field[] fields = clazz.getDeclaredFields();
        Class<?> super_clazz = clazz.getSuperclass();
        Field[] super_fields = super_clazz.getDeclaredFields();
        Field[] all_fields= new Field[fields.length+super_fields.length];  
        System.arraycopy(fields, 0, all_fields, 0, fields.length);  
        System.arraycopy(super_fields, 0, all_fields, fields.length, super_fields.length);  
        Map<String, Object> returnMap = new HashMap<String, Object>();
        
    	for(int i = 0 ; i < all_fields.length; i++){  
            Field field = all_fields[i];  
            field.setAccessible(true);   
			try {
				Object val = field.get(bean);
				String name = field.getName();
	            if(val!=null){
	                returnMap.put(name,val);
	            }
			} catch (IllegalArgumentException | IllegalAccessException e) {
				log.error(e.getMessage(), e);
			}   
        }  
		return returnMap;  
    }
}
