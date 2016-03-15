/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json.generic;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    private JsonGenerator writeSimpleType(JsonGenerator gena, String getMethodName, Object rez) {
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
        Gson gson = new Gson();
        String json = gson.toJson(ob);
        entityStream.write(json.getBytes());
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return getObj().isAssignableFrom(type);
    }

    @Override
    public T readFrom(Class<T> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        T t;
        Gson gson = new Gson();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(entityStream));
        t = gson.fromJson(reader, type);
        return t;
    }

}
