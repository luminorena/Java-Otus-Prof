package ru.otus.server.requests;

import ru.otus.server.methods.HttpRequests;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ShutdownRequest implements RequestProcessor {
    private static final String MESSAGE = "Сервис завершает свою работу. Код ошибки 0";

    @Override
    public void execute(HttpRequests httpRequests, OutputStream output) throws IOException {
        String response = "HTTP/1.1 202 Accepted\r\nContent-Type: text/plain\r\n\r\n" + MESSAGE;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
