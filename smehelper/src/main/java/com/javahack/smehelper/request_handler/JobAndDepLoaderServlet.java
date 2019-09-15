package com.javahack.smehelper.request_handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javahack.smehelper.dao.IJobDao;
import com.javahack.smehelper.model.JobAndDependencies;
import org.springframework.web.HttpRequestHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class JobAndDepLoaderServlet implements HttpRequestHandler {

    @Resource
    private IJobDao dao;

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = httpServletRequest.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        String json = buffer.toString();

        ObjectMapper mapper = new ObjectMapper();
        JobAndDependencies j = mapper.readValue(json, JobAndDependencies.class);
        dao.create(j);
    }
}
