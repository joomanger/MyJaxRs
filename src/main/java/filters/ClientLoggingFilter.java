/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.util.Map.Entry;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;

/**
 *
 * @author savin
 */
public class ClientLoggingFilter implements ClientRequestFilter, ClientResponseFilter{

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        for(Entry e: requestContext.getHeaders().entrySet()){
            System.out.println("requestContext => "+e.getKey()+" : "+e.getValue());
        }
        System.out.println("requestContext.getEntityType().getTypeName() => "+requestContext.getEntityType().getTypeName());
        System.out.println("requestContext.getMethod() => "+requestContext.getMethod());
        System.out.println("requestContext.getEntityStream().toString() => "+requestContext.getEntityStream().toString());
        for(String prop:requestContext.getPropertyNames()){
            System.out.println("PropName: "+prop);
        }
        
    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        for(Entry e: responseContext.getHeaders().entrySet()){
            System.out.println("responseContext => "+e.getKey()+" : "+e.getValue());
        }
        System.out.println("responseContext.getStatusInfo() =>"+responseContext.getStatusInfo().toString());
        
    }
    
}
