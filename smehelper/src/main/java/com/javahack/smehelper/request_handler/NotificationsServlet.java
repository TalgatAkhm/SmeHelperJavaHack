package com.javahack.smehelper.request_handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javahack.smehelper.dao.INotificationDao;
import com.javahack.smehelper.model.Notification;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotificationsServlet implements HttpRequestHandler{

    @Resource
    private INotificationDao dao;

    private static final Logger LOG = LoggerFactory.getLogger(TestServlet.class);

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = httpServletRequest.getReader();
        String line;

        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        String dt = buffer.toString();

        LOG.info("Get notifications by date: " + dt);

        DateTime barrierDateTime = DateTime.parse(dt);

        List<Notification> notifications = dao.getAll();
        List<Notification> result = new ArrayList<>();

        for(Notification n: notifications){
            if(DateTime.parse(n.getDate()).isBefore(barrierDateTime))
                result.add(n);
        }

        ObjectMapper mapper = new ObjectMapper();
        LOG.info("Notifications returned");
        mapper.writeValueAsString(result);
    }
}
