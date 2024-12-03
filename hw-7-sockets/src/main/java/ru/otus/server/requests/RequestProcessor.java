package ru.otus.server.requests;

import ru.otus.server.methods.HttpRequests;

import java.io.IOException;
import java.io.OutputStream;


public interface RequestProcessor {
    void execute(HttpRequests httpRequests, OutputStream output) throws IOException;
}
