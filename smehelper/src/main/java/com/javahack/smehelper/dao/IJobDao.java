package com.javahack.smehelper.dao;

import com.javahack.smehelper.model.JobAndDependencies;
import net.sf.autodao.AutoDAO;
import net.sf.autodao.Dao;
import net.sf.autodao.Finder;
import net.sf.autodao.Named;

import java.util.List;

@AutoDAO
public interface IJobDao extends Dao<JobAndDependencies, Integer> {
    @Finder(query = "from JobAndDependencies")
    List<JobAndDependencies> getAll();

    @Finder(query = "from JobAndDependencies where job = :name")
    JobAndDependencies getByJobName(@Named("name") String job);

    @Finder(query = "from JobAndDependencies where avitoId = :aid")
    List<JobAndDependencies> getByAvitoId(@Named("aid")Integer avitoId);
}
