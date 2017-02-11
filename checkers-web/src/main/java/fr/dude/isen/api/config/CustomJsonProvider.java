package fr.dude.isen.api.config;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/**
 * JSON Provider
 */
public class CustomJsonProvider extends JacksonJaxbJsonProvider {

    private static ObjectMapper objectMapperAtRest = new ObjectMapper();

    static {
        objectMapperAtRest.configure(SerializationConfig.Feature.INDENT_OUTPUT, true); // Different from default so you can test it :)
        objectMapperAtRest.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
    }

    public CustomJsonProvider() {
        super();
        setMapper(objectMapperAtRest);
    }
}
