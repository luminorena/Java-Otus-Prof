package ru.otus.serialization.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.boot.jackson.JsonComponent;
import ru.otus.serialization.data.SmsData;

@JsonComponent
public class JsonDeserializer implements Deserializer {

    private static final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public String execute() {
        SmsData smsData = new SmsData();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(smsData.chatSessionDto());
        final JsonNode sample = mapper.readTree(jsonStr);
        return sample.toString();

    }
}
