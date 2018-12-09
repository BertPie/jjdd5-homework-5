package com.infoshareacademy.servlets;

import com.infoshareacademy.freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;

@WebServlet(urlPatterns = "/infoShareAcademy")
public class InfoShareAcademyServlet extends HttpServlet {

    private static final String TEMPLATE_NAME = "infoShareAcademy";

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        Map<String, Object> model = new HashMap<>();
        model.put("name", "Hubert Piesniak");
        model.put("team", "jjdd5-niewiem");
        model.put("now", LocalDateTime.now());

        sendModelToTemplate(writer, model);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        Map<String, String[]> parameters = req.getParameterMap();

        for(Map.Entry entry : parameters.entrySet()){
            Object param = entry.getKey();
            String[] values = parameters.get(param);
            Arrays.stream(values).forEach(v -> writer.print(param + "=" + v + "\n"));
        }
    }

    private void sendModelToTemplate(PrintWriter out, Map<String, Object> model) throws IOException {
        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);

        try{
            template.process(model, out);
        }catch (TemplateException e){
            System.out.println("Error while processing template: " + e);
        }
    }
}
