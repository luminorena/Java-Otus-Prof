package ru.otus.artemis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.artemis.services.ArtemisMqProducer;

@Slf4j
@RestController
@RequestMapping("/v1/artemis/")
public class SendController {

    private final ArtemisMqProducer artemisMqProducer;

    @Autowired
    public SendController(ArtemisMqProducer artemisMqProducer) {
        this.artemisMqProducer = artemisMqProducer;
    }

    @PostMapping(value = "send")
    public ResponseEntity<String> send() {
        try {
            artemisMqProducer.sendMessage();
            return ResponseEntity.ok("Message sent");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("\nFailed to send message");
        }
    }

}

