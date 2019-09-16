package com.javahack.smehelper.request_handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javahack.smehelper.dao.INotificationDao;
import com.javahack.smehelper.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class NotificationLoaderServlet implements HttpRequestHandler {
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

        String json = buffer.toString();
        LOG.info("Get notifications by date: " + json);

        ObjectMapper mapper = new ObjectMapper();
        Notification notification = mapper.readValue(json, Notification.class);
        dao.create(notification);
        LOG.info("Notification created");
    }
}
