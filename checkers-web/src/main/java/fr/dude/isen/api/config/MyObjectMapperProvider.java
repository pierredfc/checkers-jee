package fr.dude.isen.api.config;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Object mapper provider for JSON mapper
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class MyObjectMapperProvider implements ContextResolver<ObjectMapper> {

    final ObjectMapper defaultObjectMapper;

    public MyObjectMapperProvider() {
        defaultObjectMapper = createDefaultMapper();
    }

    public ObjectMapper getContext() { return defaultObjectMapper; }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return defaultObjectMapper;
    }

    private static ObjectMapper createDefaultMapper() {
        final ObjectMapper result = new ObjectMapper();
        result.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
        result.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        return result;
    }

}
