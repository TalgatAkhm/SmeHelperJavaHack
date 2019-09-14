package com.javahack.smehelper.request_handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javahack.smehelper.dao.IUserDao;
import com.javahack.smehelper.model.UserOrg;
import com.javahack.smehelper.model.UserOrgAndJobs;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.HttpRequestHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class LoginServlet implements HttpRequestHandler{

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

        String name = buffer.toString();

        List<UserOrg> users = userDao.getUserByName(name);

        ObjectMapper mapper = new ObjectMapper();
        // TODO: find all job types
//        mapper.writeValueAsString(new UserOrgAndJobs(users.get(0)));

        httpServletResponse.getWriter().write(mapper.writeValueAsString(users.get(0)));
    }
}
