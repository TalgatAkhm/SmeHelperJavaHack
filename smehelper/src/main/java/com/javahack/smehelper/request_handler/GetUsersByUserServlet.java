package com.javahack.smehelper.request_handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javahack.smehelper.dao.IJobDao;
import com.javahack.smehelper.dao.IUserDao;
import com.javahack.smehelper.model.JobAndDependencies;
import com.javahack.smehelper.model.UserOrg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/get")
public class GetUsersByUserServlet implements HttpRequestHandler {

    @Resource
    private IJobDao jobDao;

    @Resource
    private IUserDao userDao;

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

        ObjectMapper m = new ObjectMapper();
        UserOrg userOrg = userDao.getUserByName(name).get(0);

        String job = userOrg.getJob();
        JobAndDependencies jobAndDependencies = jobDao.getByJobName(job);

        List<Integer> deps = jobAndDependencies.getDependencies();
        List<UserOrg> child = new ArrayList<>();

        for (Integer i : deps) {
            JobAndDependencies j = jobDao.getByAvitoId(i).get(0);
            if (j != null){
                // TODO: find child with those job
                List<UserOrg> usersChild = userDao.getUsersByJob(j.getJob());
                child.addAll(usersChild);
            }
        }

        String result = m.writeValueAsString(child);
        LOG.info(result);
        httpServletResponse.setHeader("Content-Type", "application/json; charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(result);
    }
}
