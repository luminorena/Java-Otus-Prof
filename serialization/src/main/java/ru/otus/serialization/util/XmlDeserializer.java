package ru.otus.serialization.util;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.otus.serialization.data.SmsData;


@Component
public class XmlDeserializer implements Deserializer {
    @SneakyThrows
    @Override
    public String execute() {
        XmlMapper xmlMapper = new XmlMapper();
        SmsData smsData = new SmsData();
        return xmlMapper.writeValueAsString(smsData.chatSessionDto());
    }
}
