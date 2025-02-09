package ru.otus.serialization.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.serialization.service.DeserializationService;

import java.io.IOException;
import java.util.Objects;


@RestController
@RequestMapping("/v1/sms")
public class DeserializationController {

    private static final Logger log = LoggerFactory.getLogger(DeserializationController.class);
    DeserializationService deserializationService;

    @Autowired
    public DeserializationController(DeserializationService deserializationService) {
        this.deserializationService = deserializationService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<String> getSmsData(@RequestHeader(HttpHeaders.ACCEPT) String accept)  {
        log.info("accept header {}", accept);
        String resultData = null;
        if (accept.equals("application/json")) {
            resultData = deserializationService.getJsonDeserialize();
        }
        if (accept.equals("application/xml")) {
            resultData = deserializationService.getXmlDeserialize();
        }
        if (accept.equals("application/x-yaml")) {
            resultData = deserializationService.getYamlDeserialize();
        }

        return new ResponseEntity<>(Objects.requireNonNull(resultData), HttpStatus.OK);
    }
}




