package ru.otus.serialization.util;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.otus.serialization.data.SmsData;

@Component
public class YamlDeserializer implements Deserializer {
    @SneakyThrows
    @Override
    public String execute() {
        SmsData smsData = new SmsData();
        YAMLMapper yamlMapper = new YAMLMapper();
        return yamlMapper.writeValueAsString(smsData.chatSessionDto());

    }
}
