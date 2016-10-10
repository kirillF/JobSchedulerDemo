package com.github.kirillf.jobschedulerdemo;

import android.app.job.JobParameters;
import android.app.job.JobService;

/**
 * Created by kirillf on 10/11/16.
 */

public class ServiceJob extends JobService {
    private static final String MESSAGE = "Started job with id %s";

    @Override
    public boolean onStartJob(JobParameters params) {
        ScheduledService.startScheduledJob(getApplicationContext(),
                String.format(MESSAGE, params.getJobId()));
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
