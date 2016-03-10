/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.generic;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

/**
 *
 * @author savin
 * @param <T>
 */
public abstract class RestProviderWR<T> implements MessageBodyWriter<T>, MessageBodyReader<T> {

    protected abstract Class<T> getObj();

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return getObj().isAssignableFrom(type); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getSize(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeTo(T ob, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        try (JsonGenerator gen = Json.createGenerator(entityStream)) {
            gen.writeStartObject();
            Object rez;
            String fieldName;
            for (Method mm : getObj().getDeclaredMethods()) {
                if ((mm.getName().startsWith("get")) || (mm.getName().startsWith("is"))) {
                    try {
                        rez = getObj().getDeclaredMethod(mm.getName()).invoke(ob);
                        fieldName = mm.getName().substring(3).toLowerCase();
                        if (rez instanceof Integer) {
                            gen.write(fieldName, Integer.parseInt(rez.toString()));
                        } else if (rez instanceof java.lang.String) {
                            gen.write(fieldName, rez.toString());
                        } else if (rez instanceof Float) {
                            gen.write(fieldName, Float.parseFloat(rez.toString()));
                        } else if (rez instanceof Double) {
                            gen.write(fieldName, Double.parseDouble(rez.toString()));
                        } else if (rez instanceof Long) {
                            gen.write(fieldName, Long.parseLong(rez.toString()));
                        } else if (rez instanceof Boolean) {
                            fieldName = mm.getName().substring(2).toLowerCase();
                            gen.write(fieldName, Boolean.parseBoolean(rez.toString()));
                        } else if (rez instanceof Short) {
                            gen.write(fieldName, Short.parseShort(rez.toString()));
                        } else if (rez instanceof Byte) {
                            gen.write(fieldName, Byte.parseByte(rez.toString()));
                        }

                    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        System.out.println("json.item.ItemWriterGen.writeTo().Exception: " + ex.getMessage());
                    }
                }
            }
            gen.writeEnd();
            gen.flush();
        }

    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return getObj().isAssignableFrom(type);
    }

    @Override
    public T readFrom(Class<T> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        T t;

        try {
            t = type.newInstance();
            Method[] m = t.getClass().getDeclaredMethods();
            try (JsonParser parser = Json.createParser(entityStream)) {
                while (parser.hasNext()) {
                    switch (parser.next()) {
                        case KEY_NAME:
                            String key = parser.getString();
                            parser.next();

                            for (Method mm : m) {
                                if ((mm.getName().startsWith("set"))) {

                                    String fieldName = mm.getName().substring(3).toLowerCase();

                                    if (mm.getParameterCount() > 1) {
                                        throw new WebApplicationException("XX: More one parameter into setter method");
                                    }

                                    String tp = mm.getParameters()[0].getParameterizedType().toString();

                                    try {
//                                        System.out.println("json.generic.RestProviderWR.readFrom().tp=" + tp);
                                        if (fieldName.equals(key)) {
                                            if ((tp.endsWith("java.lang.String")) || (tp.endsWith("java.lang.Boolean")) || (tp.endsWith("java.util.Date")) || (tp.endsWith("boolean"))) {
                                                mm.invoke(t, parser.getString());
                                            } else if ((tp.endsWith("java.lang.Double")) || (tp.endsWith("double"))) {
                                                mm.invoke(t, Double.valueOf(parser.getString()));
                                            } else if ((tp.endsWith("java.lang.Float")) || (tp.endsWith("float"))) {
                                                mm.invoke(t, Float.valueOf(parser.getString()));
                                            } else if ((tp.endsWith("java.lang.Shor")) || (tp.endsWith("short"))) {
                                                mm.invoke(t, Short.valueOf(parser.getString()));
                                            } else if ((tp.endsWith("java.lang.Byte")) || (tp.endsWith("byte"))) {
                                                mm.invoke(t, Byte.valueOf(parser.getString()));
                                            } else if ((tp.endsWith("java.lang.Integer")) || (tp.endsWith("int"))) {
                                                mm.invoke(t, Integer.valueOf(parser.getString()));
                                            } else if ((tp.endsWith("java.lang.Long")) || (tp.endsWith("long"))) {
                                                mm.invoke(t, Long.valueOf(parser.getString()));
                                            }
                                        }
                                    } catch (IllegalAccessException | InvocationTargetException ex) {
                                        throw new WebApplicationException("XX: Invoking error the getter method");
                                    }
                                }
                            }
                    }
                }
            }
            return t;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(RestProviderWR.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
