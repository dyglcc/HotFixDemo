package hotfix.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


/**
 * Created by dongyuangui on 15-5-4.
 */
public class ReflectionUtil {

    /**
     * 返回指定字段的Field
     *
     * @param clazz
     * @param fieldName
     * @return
     * @throws NoSuchFieldException
     */
    public static Field getField(Class clazz, String fieldName) throws NoSuchFieldException {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class superClass = clazz.getSuperclass();
            if (superClass == null) {
                throw e;
            } else {
                return getField(superClass, fieldName);
            }
        }
    }

    /**
     * 设置可以访问
     */
    public static void makeAccessible(Field field) {
        if (!Modifier.isPublic(field.getModifiers()) ||
                !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            field.setAccessible(true);
        }
    }

    public static Object getFieldValue(Field field, Object obj) throws IllegalAccessException {
        if (field != null) {
            makeAccessible(field);
        }
        if (field != null) {
            return field.get(obj);
        } else {
            return null;
        }
    }

}
