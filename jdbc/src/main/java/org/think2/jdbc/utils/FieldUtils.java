package org.think2.jdbc.utils;

import org.think2.jdbc.Think2JdbcException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zhoubin on 15/11/30.
 */
public class FieldUtils {

    /**
     * 获取对象指定名称的属性值，如果是map类型则转换为map获取，其他根据字段定义获取
     *
     * @param instance  取值对象
     * @param fieldName 字段名称
     * @return 字段值
     */
    public static Object getFieldValue(Object instance, String fieldName) {
        Object value = null;
        if (HashMap.class == instance.getClass() || LinkedHashMap.class == instance.getClass()) {
            Map map = (Map) instance;
            value = map.get(fieldName);
        } else {
            Field[] fields = instance.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals(fieldName)) {
                    try {
                        value = field.get(instance);
                    } catch (IllegalAccessException e) {
                        throw new Think2JdbcException(e);
                    }
                    break;
                }
            }
        }
        return value;
    }
}
