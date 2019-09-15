package com.javahack.smehelper.model;

import java.util.List;

public final class UserOrgAndJobs {

    private final UserOrg org;
    private final List<JobAndDependencies> allJobs;

    public UserOrgAndJobs(UserOrg org, List<JobAndDependencies> allJobs) {
        this.org = org;
        this.allJobs = allJobs;
    }

    public UserOrg getOrg() {
        return org;
    }

    public List<JobAndDependencies> getAllJobs() {
        return allJobs;
    }
}
