package com.shizy.utils.bean;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class BeanUtil {

    private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    /***********************************************************/

    /**
     * 复制map中的参数到实体类中，以entity中成员变量是否存在为准
     * 注：两个类的成员变量(或get方法)的名字、类型需一致
     * 成员变量建议定义成包装类，基本类型没有值默认是0，null与0是不同的
     *
     * @param paramMap 键值对与Entity成员变量对应的map
     * @param entity   被填充内容的Entity
     * @return entity 被填充内容的Entity 可以不处理这个返回，参数中的引用类型entity，其值已经被改变
     */
    public static <T> T copyMapParam2Entity(Map paramMap, T entity) {
        if (paramMap == null) {
            return entity;
        }
        Class<?> aClass = entity.getClass();
        //迭代set方法
        for (Method method : aClass.getMethods()) {
            if (method.getName().indexOf("set") != 0) {
                continue;
            }
            String field = method.getName().substring(3, 4).toLowerCase() +
                    method.getName().substring(4);
            try {
                Object value = paramMap.get(field);
                if (value == null) {
                    continue;
                }
                method.invoke(entity, value);
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.error("反射调用copyMapParam2Entity方法失败[paramMap={}],[entity={}]", paramMap, entity);
                throw new RuntimeException(e);
            }
        }
        return entity;
    }

    public static <T> T copyMapParam2Entity(Map paramMap, Class<T> entityClass) {
        try {
            return copyMapParam2Entity(paramMap, entityClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("反射调用newInstance方法失败[class={}],[field={}]", entityClass);
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> copyMapParam2EntityList(List<Map> paramList, Class<T> entityClass) {
        List<T> list = new ArrayList<>();
        for (Map map : paramList) {
            try {
                list.add(copyMapParam2Entity(map, entityClass.newInstance()));
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("反射调用copyMapParam2EntityList方法失败[List={}],[entityClass={}]", paramList, entityClass);
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    /***********************************************************/

    public static <S, T> T copyProperties(S source, T target, String... ignoreFields) {
        if (source == null || target == null) {
            return target;
        }
        Class<?> aClass = target.getClass();
        //迭代set
        for (Method method : aClass.getMethods()) {
            if (method.getName().indexOf("set") != 0) {
                continue;
            }
            String field = method.getName().substring(3, 4).toLowerCase() +
                    method.getName().substring(4);
            if (isIgnoredField(ignoreFields, field)) {
                continue;
            }
            Object sourceFieldValue = null;
            try {
                Method crtMethod = source.getClass()
                        .getMethod("get" + field.substring(0, 1).toUpperCase() + field.substring(1));
                crtMethod.setAccessible(true);
                sourceFieldValue = crtMethod.invoke(source);
                if (sourceFieldValue == null) {
                    continue;
                }
                if (!method.getParameterTypes()[0].isAssignableFrom(sourceFieldValue.getClass())) {
                    continue;
                }
                method.invoke(target, sourceFieldValue);
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.error("反射调用copyProperties方法失败[source={}],[target={}]", source, target);
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                //do nothing
//                e.printStackTrace();//
            }
        }
        return target;
    }

    /**
     * 复制entity中的参数到other entity中，以target中成员属性是否存在为准
     *
     * @param source 提供数据的entity 参数命名需与target一致
     * @param target 被填充内容的entity
     * @return target 被填充内容的target 可以不处理这个返回，参数中的引用类型target，其值已经被改变
     */
    public static <S, T> T copyProperties(S source, T target) {
        return copyProperties(source, target, (String[]) null);
    }

    public static <S, T> T copyProperties(S source, Class<T> targetClass) {
        try {
            return copyProperties(source, targetClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("反射调用newInstance方法失败[class={}],[field={}]", targetClass);
            throw new RuntimeException(e);
        }
    }

    private static boolean isIgnoredField(String[] ignoreFields, String field) {

        if (ignoreFields == null) {
            return false;
        }

        for (String ignoreField : ignoreFields) {
            if (ignoreField.equals(field)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 复制泛型S的List数据到泛型T的List中，以target中成员属性是否存在为准
     *
     * @param sourceList  泛型S的List
     * @param targetClass 用于确定结果List的泛型T
     * @return 泛型T的结果List
     */
    public static <S, T> List<T> copyPropertiesList(List<S> sourceList, Class<T> targetClass) {
        if (sourceList == null) {
            return null;
        }
        List<T> targetList = new ArrayList<>();
        for (S source : sourceList) {
            try {
                targetList.add(copyProperties(source, targetClass.newInstance()));
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("反射调用newInstance方法失败[class={}],[field={}]", targetClass);
                throw new RuntimeException(e);
            }
        }
        return targetList;
    }

    /**
     * 获取值为空的字段
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 将src不为空的值，覆盖到target
     * 这里是使用的BeanUtils方法实现忽略null。结果同copyProperties
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    /***********************************************************/

    public static <T> Map<String, Object> genMapFromEntity(T entity) {
        return genMapFromEntity(entity, new HashMap<String, Object>());
    }

    public static <T> Map<String, Object> genMapFromEntity(T entity, Map<String, Object> existMap) {
        return genMapFromEntity(entity, existMap, true);
    }

    public static <T> Map<String, Object> genMapFromEntity(T entity, Map<String, Object> existMap, boolean ignoreNull) {
        Class<?> aClass = entity.getClass();
        //迭代get方法
        for (Method method : aClass.getMethods()) {
            if (method.getName().indexOf("get") != 0 || method.getName().indexOf("getClass") == 0) {
                //若不为get或为getClass
                continue;
            }
            String field = method.getName().substring(3, 4).toLowerCase() +
                    method.getName().substring(4);
            try {
                Object value = method.invoke(entity);
                if (ignoreNull && (value == null || StringUtils.isBlank(value.toString()))) {
                    continue;
                }
                existMap.put(field, value);
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.error("反射调用genMapFromEntity方法失败[entity={}]", entity);
                throw new RuntimeException(e);
            }
        }
        return existMap;
    }


    /***********************************************************/

    /**
     * 反射调用实例的get方法
     *
     * @param obj        被调用的实例对象
     * @param field      字段名
     * @param fieldClass 字段类型
     * @return get方法返回的数据
     */
    public static <S, T> T get(S obj, String field, Class<T> fieldClass) {
        try {
            return (T) obj.getClass().getMethod(
                    "get" + field.substring(0, 1).toUpperCase() + field.substring(1)
            ).invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error("反射调用get方法失败[obj={}],[field={}]", obj, field);
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    /**
     * 反射调用set
     */
    public static <S, V> void set(S obj, String field, V value, Class fieldClass) {
        Method m = null;
        //get Method
        try {
            m = obj.getClass().getMethod(
                    "set" + field.substring(0, 1).toUpperCase() + field.substring(1),
                    fieldClass
            );
        } catch (NoSuchMethodException e) {
            return;
        }
        //invoke
        try {
            m.invoke(obj, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error("反射调用set方法失败[obj={}],[field={}]", obj, field);
            throw new RuntimeException(e);
        }
    }


}












