package com.javahack.smehelper.dao;

import com.javahack.smehelper.model.UserOrg;
import net.sf.autodao.AutoDAO;
import net.sf.autodao.Dao;
import net.sf.autodao.Finder;
import net.sf.autodao.Named;

import java.util.List;

@AutoDAO
public interface IUserDao extends Dao<UserOrg, Integer> {
    @Finder(query = "from UserOrg")
    List<UserOrg> getAll();

    @Finder(query="from UserOrg where name = :login")
    List<UserOrg> getUserByName(@Named("login")String name);

    @Finder(query = "from UserOrg where job = :type")
    List<UserOrg> getUsersByJob(@Named("type")String job);
}
