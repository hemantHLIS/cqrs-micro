package com.hlis.common.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class ApplicationUtils {

	public static String generateJSONFromObject(final Object object) {


        final ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = null;

        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            jsonString = objectMapper.writeValueAsString(object);

        } catch (final Exception exception) {

        }

        return jsonString;
    }
    
    public static <T> T generateObjectFromJSON(final String jsonString, final Class<T> clazz, final boolean isList) {


        T object = null;

        if (!jsonString.isEmpty()) {

            try {

                final ObjectMapper objectMapper = new ObjectMapper();

                if (isList) {

                    object = objectMapper.readValue(jsonString,
                            TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));
                } else {
                    object = clazz.getDeclaredConstructor().newInstance();
                    object = objectMapper.readValue(jsonString, clazz);
                }

            } catch (final Exception exception) {

            }
        }

        return object;
    }
	
}
