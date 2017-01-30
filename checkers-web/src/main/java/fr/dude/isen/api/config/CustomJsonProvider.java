package fr.dude.isen.api.config;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/*import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;*/

//@Provider
//@Produces(MediaType.APPLICATION_JSON)
public class CustomJsonProvider extends JacksonJaxbJsonProvider {

    private static ObjectMapper objectMapperAtRest = new ObjectMapper();

    static {
        //objectMapperAtRest.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //objectMapperAtRest.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapperAtRest.configure(SerializationConfig.Feature.INDENT_OUTPUT, true); // Different from default so you can test it :)
        //objectMapperAtRest.setSerializationInclusion(JsonInclude.Include.ALWAYS);
    }

    public CustomJsonProvider() {
        super();
        setMapper(objectMapperAtRest);
    }
}
