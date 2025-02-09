package ru.otus.serialization.service;

import org.springframework.stereotype.Service;
import ru.otus.serialization.util.JsonDeserializer;
import ru.otus.serialization.util.XmlDeserializer;
import ru.otus.serialization.util.YamlDeserializer;


@Service
public class DeserializationService {

    JsonDeserializer jsonDeserializer;
    XmlDeserializer xmlDeserializer;
    YamlDeserializer yamlDeserializer;


    public DeserializationService(JsonDeserializer jsonDeserializer, XmlDeserializer xmlDeserializer, YamlDeserializer yamlDeserializer) {
        this.jsonDeserializer = jsonDeserializer;
        this.xmlDeserializer = xmlDeserializer;
        this.yamlDeserializer = yamlDeserializer;

    }

    public String getJsonDeserialize() {
        return jsonDeserializer.execute();
    }

    public String getXmlDeserialize() {
        return xmlDeserializer.execute();
    }

    public String getYamlDeserialize() {
        return yamlDeserializer.execute();
    }


}

