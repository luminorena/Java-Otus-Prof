package ru.otus.javaee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "division", urlPatterns = "/div")
public class Division extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(Division.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        double result;
        String firstParam = req.getParameter("first");
        String secondParam = req.getParameter("second");
        if (Integer.parseInt(secondParam) != 0) {
            result = Double.parseDouble(firstParam) / Double.parseDouble(secondParam);
        } else {
            logger.error("Division by zero");
            throw new ServletException("Division by zero is prohibited!");
        }

        out.println("<html><body><h1>" + firstParam + " / " + secondParam + " = " + result + "</h1></body></html>");
        out.close();
    }
}
