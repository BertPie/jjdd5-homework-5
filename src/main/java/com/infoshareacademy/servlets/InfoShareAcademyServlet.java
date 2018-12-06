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
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/infoShareAcademy")
public class InfoShareAcademyServlet extends HttpServlet {

    private static final String TEMPLATE_NAME = "infoShareAcademy";

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        Template template = templateProvider.getTemplate(
                getServletContext(),
                TEMPLATE_NAME
        );

        Map<String, Object> model = new HashMap<>();
        model.put("name", "Bert Pie");
        model.put("team", "jjdd5-niewiem");
        model.put("now", LocalDateTime.now());

        try{
            template.process(model, out);
        }catch (TemplateException e){
            System.out.println("Error while processing template: " + e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        Template template = templateProvider.getTemplate(
                getServletContext(),
                TEMPLATE_NAME
        );

        Map<String, String[]> parameters = req.getParameterMap();

        StringBuilder sb = new StringBuilder();
        for(Map.Entry entry : parameters.entrySet()){
            out.print(entry.getKey() + "=");
            out.print(entry.getValue().getClass());
        }
    }
}
