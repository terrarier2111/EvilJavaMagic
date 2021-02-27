package de.terrarier.eviljavamagic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import sun.misc.Unsafe;

public final class ReflectionMagic {

    public static Class<?> getGenericParam(Class<?> clazz, int index) {
        final ParameterizedType pt = (ParameterizedType) clazz.getGenericSuperclass();
        // You may need this split or not, use logging to check
        final String parameterClassName = pt.getActualTypeArguments()[index].toString().split("\\s")[1];
        try {
            return Class.forName(parameterClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Unsafe getUnsafe() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        final Field access = Unsafe.class.getDeclaredField("theUnsafe");
        access.setAccessible(true);
        return (Unsafe) access.get(null);
    }

    public static void forceOpenField(Field field) {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        if ((field.getModifiers() & Modifier.FINAL) != 0) {
            try {
                final Field modifiers = Field.class.getDeclaredField("modifiers");
                modifiers.setAccessible(true);
                modifiers.set(field, field.getModifiers() & ~Modifier.FINAL);
            } catch (NoSuchFieldException | IllegalAccessException ignored) {

            }
        }
    }

}
