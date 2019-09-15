package com.javahack.smehelper.request_handler;

import com.javahack.smehelper.dao.IJobDao;
import com.javahack.smehelper.dao.IUserDao;
import com.javahack.smehelper.model.JobAndDependencies;
import com.javahack.smehelper.model.UserOrg;
import org.springframework.web.HttpRequestHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Pusher implements HttpRequestHandler {

    @Resource
    private IJobDao dao;

    @Resource
    private IUserDao userDao;

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        List<JobAndDependencies> list = new ArrayList<>();

        // Auto
        list.add(new JobAndDependencies(9, "Автомобили", Arrays.asList(10, 19, 85, 81)));
        list.add(new JobAndDependencies(81, "Грузовики и спецтехника", Arrays.asList(9, 10, 19, 85)));
        list.add(new JobAndDependencies(10, "Запчасти и аксессуары", Collections.emptyList()));
        list.add(new JobAndDependencies(19, "Ремонт и строительство", Collections.emptyList()));
        list.add(new JobAndDependencies(85, "Гаражи и машиноместа", Collections.emptyList()));

        // House
        list.add(new JobAndDependencies(24, "Квартиры", Arrays.asList(19, 85, 81, 20, 21)));
        list.add(new JobAndDependencies(20, "Мебель и интерьер", Collections.emptyList()));
        list.add(new JobAndDependencies(21, "Бытовая техника", Collections.emptyList()));

        // Clothes
        list.add(new JobAndDependencies(27, "Одежда, обувь, аксессуары", Arrays.asList(28, 29, 30, 81)));
        list.add(new JobAndDependencies(28, "Часы и украшения", Collections.emptyList()));
        list.add(new JobAndDependencies(29, "Детская одежда и обувь", Collections.emptyList()));
        list.add(new JobAndDependencies(30, "Товары для детей и игрушки", Collections.emptyList()));

        // Computer
        list.add(new JobAndDependencies(10, "Запчасти и аксессуары", Arrays.asList(9, 19, 21, 81, 84)));
        list.add(new JobAndDependencies(84, "Телефоны", Collections.emptyList()));

        for(JobAndDependencies j:list){
            dao.create(j);
        }

/*        UserOrg u = new UserOrg();
        u.setJob("Автомобили");
        u.setName("admin");
        u.setOrgName(null);
        u.setAmount(10000.0);
        userDao.create(u);*/
    }
}
