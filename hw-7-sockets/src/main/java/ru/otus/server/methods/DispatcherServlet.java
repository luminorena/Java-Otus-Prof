package ru.otus.server.methods;

import ru.otus.server.requests.FileRequest;
import ru.otus.server.requests.RequestProcessor;
import ru.otus.server.requests.ShutdownRequest;
import ru.otus.server.requests.TimeRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet {
    private Map<String, RequestProcessor> router;


    public DispatcherServlet() {
        this.router = new HashMap<>();
        this.router.put("GET /current_time", new TimeRequest());
        this.router.put("GET /shutdown", new ShutdownRequest());
        this.router.put("POST /upload", new FileRequest());
    }

    public void execute(HttpRequests httpRequests, OutputStream outputStream) throws IOException {
        router.get(httpRequests.getRouteKey()).execute(httpRequests, outputStream);
    }
}
