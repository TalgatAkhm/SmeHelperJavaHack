package com.javahack.smehelper.dao;

import com.javahack.smehelper.model.Notification;
import com.javahack.smehelper.model.UserOrg;
import net.sf.autodao.AutoDAO;
import net.sf.autodao.Dao;
import net.sf.autodao.Finder;

import java.util.List;

@AutoDAO
public interface INotificationDao extends Dao<Notification, Integer> {
    @Finder(query = "from Notification")
    List<Notification> getAll();
}
