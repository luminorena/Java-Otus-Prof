package ru.otus.server.methods;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpRequests {
    private String rawRequest;
    private String uri;
    private HttpMethods method;
    private Map<String, String> parameters;
    private Map<String, String> map = new HashMap<>();
    private String body;

    public HttpRequests(String rawRequest) {
        this.rawRequest = rawRequest;
        this.parseRequestLine();
        this.tryToParseBody();
        this.parameters = new HashMap<>();


    }

    public String getRouteKey() {
        return String.format("%s %s", method, uri);
    }

    public String printBody() {
        return body;
    }

    public int contentLength() {
        return Integer.parseInt(map.get("Content-Length"));
    }


    public void tryToParseBody() {
        if (method == HttpMethods.POST) {
            List<String> lines = rawRequest.lines().collect(Collectors.toList());
            int splitLine = -1;
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).isEmpty()) {
                    splitLine = i;
                    break;
                }
            }
            if (splitLine > -1) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = splitLine + 1; i < lines.size(); i++) {
                    stringBuilder.append(lines.get(i));
                }
                this.body = stringBuilder.toString();
            }
        }
    }

    public void parseRequestLine() {
        String str = rawRequest.lines().collect(Collectors.toList()).toString();
        String[] headerPairs = str.split(",");
        for (String pair : headerPairs) {
            String[] parts = pair.split(":");
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();
                map.put(key, value);
            }
        }

        int startIndex = rawRequest.indexOf(' ');
        int endIndex = rawRequest.indexOf(' ', startIndex + 1);
        this.uri = rawRequest.substring(startIndex + 1, endIndex);
        this.method = HttpMethods.valueOf(rawRequest.substring(0, startIndex));
        this.parameters = new HashMap<>();
        if (uri.contains("?")) {
            String[] elements = uri.split("[?]");
            this.uri = elements[0];
            String[] keysValues = elements[1].split("&");
            for (String o : keysValues) {
                String[] keyValue = o.split("=");
                this.parameters.put(keyValue[0], keyValue[1]);
            }
        }
    }


    public void info() {
        if (contentLength() < 5120) {
            System.out.println("URI: " + uri);
            System.out.println("HTTP-method: " + method);
            System.out.println("Parameters: " + parameters);
            System.out.println("Body: " + body);
            System.out.println("Headers: ");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }

    }


}
