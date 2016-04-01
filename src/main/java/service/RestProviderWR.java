package service;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

/**
 *
 * @author savin
 * @param <T>
 */
public abstract class RestProviderWR<T> implements MessageBodyWriter<T>, MessageBodyReader<T>, ClientOperations<T> {

    private Client client;
    private WebTarget target;

    protected abstract String getPath();

    @PostConstruct
    private void init() {
        client = ClientBuilder.newClient();
        target = client.target(getPath());
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

    public WebTarget getTarget() {
        return target;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type.isAssignableFrom(type);
    }

    @Override
    public long getSize(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(T ob, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        Gson gson = new Gson();
        String json = gson.toJson(ob);
        entityStream.write(json.getBytes());
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type.isAssignableFrom(type);
    }

    @Override
    public T readFrom(Class<T> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        Gson gson = new Gson();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(entityStream));
        return gson.fromJson(reader, type);
    }

    @Override
    public Response editItem(Object obj, String success_msg) {
        Response t=getTarget()
                .register(this)
                .request()
                .put(Entity.entity(obj, MediaType.APPLICATION_JSON));
        if (t.getStatus() == 204) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(success_msg, null));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка обновления "+t.getStatusInfo(), null));
        }
        return t;
    }

    @Override
    public Response deleteItem(Object obj, String success_msg) {
        Response t = getTarget()
                .path("{itemId}")
                .resolveTemplate("itemId", obj)
                .request()
                .delete();
        if (t.getStatus() == 204) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(success_msg, null));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка удаления ", null));
        }
        return t;
    }

    @Override
    public T getItem(Class<T> type, Object value) {
        return getTarget().path("{item}").resolveTemplate("item", value).request().get(type);
    }

    @Override
    public T[] getItems(Class<?> type) {
        return (T[]) getTarget().request().get(type);
    }

    @Override
    public Response addItem(Object obj, String success_msg) {
        Response t
                = getTarget()
                .register(this)
                .request()
                .post(Entity.entity(obj, MediaType.APPLICATION_JSON));
        if (t.getStatus() == 200) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(success_msg, null));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка вставки", null));
        }
        return t;
    }

}
