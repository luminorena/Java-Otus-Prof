package ru.otus.server.requests;

import com.google.gson.Gson;
import ru.otus.server.dao.TimeDTO;
import ru.otus.server.dao.TimeDao;
import ru.otus.server.methods.HttpRequests;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class TimeRequest implements RequestProcessor {
    @Override
    public void execute(HttpRequests httpRequests, OutputStream output) throws IOException {
        Gson gson = new Gson();
        TimeDTO timeDTO = TimeDao.createTimeObject();
        if (timeDTO == null) {
            sendErrorResponse(output, "Ошибка дессериализации, объект " +
                    timeDTO + "равен null");
            return;
        }
        String jsonResponse = gson.toJson(timeDTO);

        String response = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n" + jsonResponse;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }

    private void sendErrorResponse(OutputStream output, String message) throws IOException {
        String response = "HTTP/1.1 400 Bad Request\r\nContent-Type: application/json\r\n\r\n" + message;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}