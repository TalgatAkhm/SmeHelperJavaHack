package com.javahack.smehelper.request_handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javahack.smehelper.dao.IUserDao;
import com.javahack.smehelper.model.UserOrg;
import org.springframework.web.HttpRequestHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class RegisterServlet implements HttpRequestHandler {

    @Resource
    IUserDao userDao;

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

        UserOrg userOrg = mapper.readValue(json, UserOrg.class);
        userDao.create(userOrg);
        System.out.println(userOrg.getName());
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }

}
