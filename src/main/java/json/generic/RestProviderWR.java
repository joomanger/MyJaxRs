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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;

import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import static javax.json.stream.JsonParser.Event.END_ARRAY;
import static javax.json.stream.JsonParser.Event.START_ARRAY;
import static javax.json.stream.JsonParser.Event.START_OBJECT;
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

    private JsonGenerator genz(JsonGenerator gena, String getMethodName, Object rez) {
        JsonGenerator gen = gena;
        String fieldName = getMethodName.substring(3).toLowerCase();
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
            fieldName = getMethodName.substring(2).toLowerCase();
            gen.write(fieldName, Boolean.parseBoolean(rez.toString()));
        } else if (rez instanceof Short) {
            gen.write(fieldName, Short.parseShort(rez.toString()));
        } else if (rez instanceof Byte) {
            gen.write(fieldName, Byte.parseByte(rez.toString()));
        }
        return gen;
    }

    @Override
    public void writeTo(T ob, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        JsonGenerator gen = Json.createGenerator(entityStream);
        gen.writeStartObject();
        Object rez;
        for (Method mm : getObj().getDeclaredMethods()) {
            if ((mm.getName().startsWith("get")) || (mm.getName().startsWith("is"))) {
                try {
                    rez = getObj().getDeclaredMethod(mm.getName()).invoke(ob);
                    gen = genz(gen, mm.getName(), rez);
                    if (rez instanceof List) {
                        gen.writeStartArray("lines");
                        for (Object item : ((List) rez)) {
                            gen.writeStartObject();
                            for (Method m : ((List) rez).get(0).getClass().getDeclaredMethods()) {
                                if ((m.getName().startsWith("get")) || (m.getName().startsWith("is"))) {
                                    gen = genz(gen, m.getName(), item.getClass().getDeclaredMethod(m.getName()).invoke(item));
                                }
                            }
                            gen.writeEnd();
                        }
                        gen.writeEnd();
                        System.out.println(((List) rez).size());
                    }

                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NullPointerException ex) {
                    System.out.println("json.item.ItemWriterGen.writeTo().Exception: " + ex.getMessage());
                }
            }
        }
        gen.writeEnd();
        gen.flush();
        gen.close();
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
                            Event e = parser.next();

                            for (Method mm : m) {
                                if ((mm.getName().startsWith("set"))) {

                                    String fieldName = mm.getName().substring(3).toLowerCase();

                                    if (mm.getParameterCount() > 1) {
                                        throw new WebApplicationException("XX: More one parameter into setter method");
                                    }

                                    String tp = mm.getParameters()[0].getParameterizedType().toString();
                                    try {
                                        if (fieldName.equals(key)) {
                                            if ((tp.endsWith("java.lang.String")) || (tp.endsWith("java.lang.Boolean")) || (tp.endsWith("java.util.Date")) || (tp.endsWith("boolean"))) {
                                                mm.invoke(t, parser.getString());
                                            } else if ((tp.endsWith("java.lang.Double")) || (tp.endsWith("double"))) {
                                                mm.invoke(t, Double.valueOf(parser.getString()));
                                            } else if ((tp.endsWith("java.lang.Float")) || (tp.endsWith("float"))) {
                                                mm.invoke(t, Float.valueOf(parser.getString()));
                                            } else if ((tp.endsWith("java.lang.Short")) || (tp.endsWith("short"))) {
                                                mm.invoke(t, Short.valueOf(parser.getString()));
                                            } else if ((tp.endsWith("java.lang.Byte")) || (tp.endsWith("byte"))) {
                                                mm.invoke(t, Byte.valueOf(parser.getString()));
                                            } else if ((tp.endsWith("java.lang.Integer")) || (tp.endsWith("int"))) {
                                                mm.invoke(t, Integer.valueOf(parser.getString()));
                                            } else if ((tp.endsWith("java.lang.Long")) || (tp.endsWith("long"))) {
                                                mm.invoke(t, Long.valueOf(parser.getString()));
                                            } else if (tp.startsWith("java.util.List<")) {
                                                if (e == START_ARRAY) {
                                                    String str = tp;
                                                    str = str.replace("java.util.List<", "");
                                                    str = str.substring(0, str.length() - 1);
//                                                    System.out.println("!!!!!!!!!!!!!START_ARRAY!!!!!!!!!!!!!!");
                                                    List l = new ArrayList();
                                                    while (e != END_ARRAY) {
                                                        e = parser.next();
                                                        if (e == START_OBJECT) {
                                                            Class cl;
                                                            try {
                                                                cl = Class.forName(str);
                                                                Object obj = cl.newInstance();
                                                                while (e != Event.END_OBJECT) {
                                                                    e = parser.next();
                                                                    switch (e) {
                                                                        case KEY_NAME:
//                                                                            System.out.println("KEY_NAME=" + parser.getString());
                                                                            String v = parser.getString();
                                                                            e = parser.next();
                                                                            for (Method pp : cl.getDeclaredMethods()) {
                                                                                if (pp.getName().startsWith("set")) {
                                                                                    if (pp.getName().replaceFirst("set", "").toLowerCase().equals(v)) {
                                                                                        String tp2 = pp.getParameters()[0].getParameterizedType().toString();
//                                                                                        System.out.println(e.toString());

                                                                                        switch (e) {
                                                                                            case VALUE_STRING:
                                                                                                pp.invoke(obj, parser.getString());
                                                                                                break;
                                                                                            case VALUE_NUMBER:
                                                                                                if ((tp2.endsWith("java.lang.Double")) || (tp2.endsWith("double"))) {
                                                                                                    pp.invoke(obj, Double.valueOf(parser.getString()));
                                                                                                } else if ((tp2.endsWith("java.lang.Float")) || (tp2.endsWith("float"))) {
                                                                                                    pp.invoke(obj, Float.valueOf(parser.getString()));
                                                                                                } else if ((tp2.endsWith("java.lang.Short")) || (tp2.endsWith("short"))) {
                                                                                                    pp.invoke(obj, Short.valueOf(parser.getString()));
                                                                                                } else if ((tp2.endsWith("java.lang.Byte")) || (tp2.endsWith("byte"))) {
                                                                                                    pp.invoke(obj, Byte.valueOf(parser.getString()));
                                                                                                } else if ((tp2.endsWith("java.lang.Integer")) || (tp2.endsWith("int"))) {
                                                                                                    pp.invoke(obj, Integer.valueOf(parser.getString()));
                                                                                                } else if ((tp2.endsWith("java.lang.Long")) || (tp2.endsWith("long"))) {
                                                                                                    pp.invoke(obj, Long.valueOf(parser.getString()));
                                                                                                }
                                                                                                break;
                                                                                            case VALUE_TRUE:
                                                                                                pp.invoke(obj, Boolean.TRUE);
                                                                                                break;
                                                                                            case VALUE_FALSE:
                                                                                                pp.invoke(obj, Boolean.FALSE);
                                                                                                break;
                                                                                            default:
                                                                                                break;
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                            break;
                                                                    }
                                                                }
                                                                l.add(obj);

                                                            } catch (ClassNotFoundException ex) {
                                                                Logger.getLogger(RestProviderWR.class.getName()).log(Level.SEVERE, null, ex);
                                                                throw new WebApplicationException("Class is not found");
                                                            }

                                                        }

                                                    }
                                                    mm.invoke(t, l);
                                                }
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
