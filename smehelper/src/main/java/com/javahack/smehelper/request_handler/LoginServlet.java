package com.javahack.smehelper.request_handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javahack.smehelper.dao.IJobDao;
import com.javahack.smehelper.dao.IUserDao;
import com.javahack.smehelper.model.UserOrg;
import com.javahack.smehelper.model.UserOrgAndJobs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private IUserDao userDao;

    @Resource
    private IJobDao jobDao;

    private static final Logger LOG = LoggerFactory.getLogger(TestServlet.class);

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = httpServletRequest.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        String name = buffer.toString().replaceAll("\"", "");
        LOG.info("login user: " + name);

        List<UserOrg> users = userDao.getUserByName(name);

        ObjectMapper mapper = new ObjectMapper();
//        users.get(0).setJob(String.encode(users.get(0).getJob(), ""));
        String result = mapper.writeValueAsString(users.get(0));

        LOG.info("login is successful, its: " + users.get(0).getJob());
        httpServletResponse.setHeader("Content-Type", "application/json; charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(result/*new String(result.getBytes("ISO-8859-1"), "UTF-16")*/);
    }
}
