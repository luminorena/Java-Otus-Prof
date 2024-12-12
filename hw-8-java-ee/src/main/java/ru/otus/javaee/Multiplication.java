package ru.otus.javaee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "multiplication", urlPatterns = "/multiply")
public class Multiplication extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        String firstParam = req.getParameter("first");
        String secondParam = req.getParameter("second");
        int result = Integer.parseInt(firstParam) * Integer.parseInt(secondParam);
        out.println("<html><body><h1>" + firstParam + " * " + secondParam + " = " + result + "</h1></body></html>");
        out.close();
    }
}
