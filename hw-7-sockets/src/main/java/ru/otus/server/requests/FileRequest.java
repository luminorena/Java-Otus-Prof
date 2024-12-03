package ru.otus.server.requests;

import ru.otus.server.methods.HttpRequests;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


public class FileRequest implements RequestProcessor {

    @Override
    public void execute(HttpRequests httpRequests, OutputStream output) throws IOException {
        String file = httpRequests.printBody();
        int checkQueryLength = httpRequests.contentLength();
        if (checkQueryLength > 5120) {
            sendErrorResponse(output, "Размер запроса не должен превышать 5МБ");
        } else {
            String response = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\n" + file;
            output.write(response.getBytes(StandardCharsets.UTF_8));
        }
    }

    private void sendErrorResponse(OutputStream output, String message) throws IOException {
        String response = "HTTP/1.1 400 Bad Request\r\nContent-Type: text/plain\r\n\r\n" + message;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
