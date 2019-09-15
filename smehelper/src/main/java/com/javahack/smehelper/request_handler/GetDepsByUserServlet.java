package com.javahack.smehelper.request_handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javahack.smehelper.dao.IJobDao;
import com.javahack.smehelper.dao.IUserDao;
import com.javahack.smehelper.model.JobAndDependencies;
import com.javahack.smehelper.model.UserOrg;
import org.springframework.web.HttpRequestHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class GetDepsByUserServlet implements HttpRequestHandler {

    @Resource
    private IJobDao jobDao;

    @Resource
    private IUserDao userDao;

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = httpServletRequest.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        String name = buffer.toString().replaceAll("\"", "");

        ObjectMapper m = new ObjectMapper();
        UserOrg userOrg = userDao.getUserByName(name).get(0);

        String job = userOrg.getJob();
        JobAndDependencies jobAndDependencies = jobDao.getByJobName(job);

        List<Integer> deps = jobAndDependencies.getDependencies();

        StringBuilder sb = new StringBuilder("{\"ids\":\"");

        for(Integer i: deps){
            sb.append(i).append(" ");
        }
        sb.append("\"}");
        httpServletResponse.getWriter().write(sb.toString());
    }
}
