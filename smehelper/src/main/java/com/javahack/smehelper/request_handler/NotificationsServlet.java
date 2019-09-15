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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

        if (dt.trim().equals("")) {
            DateTime barrierDateTime = DateTime.parse(dt);
        }

        List<Notification> notifications = dao.getAll();
        List<Notification> result = new ArrayList<>();

        if (notifications == null || notifications.size() == 0) {
            loadNotifications();
            notifications = dao.getAll();
        }

        httpServletResponse.setHeader("Content-Type", "application/json; charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        LOG.info("Notifications returned");
        mapper.writeValue(httpServletResponse.getWriter(), notifications);
    }

    private void loadNotifications() {
        File news = new File(getClass().getClassLoader().getResource("news.csv").getFile());
        try(BufferedReader br = new BufferedReader(new FileReader(news))) {
            for(String line; (line = br.readLine()) != null; ) {
                Notification notification = new Notification();
                String title = line.substring(0, line.indexOf(','));
                String text = line.substring(line.indexOf(',')+1, line.length());
                notification.setText(text);
                notification.setTitle(title);
                notification.setDate(new Date().toString());
                notification.setNalog(false);
                dao.create(notification);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
