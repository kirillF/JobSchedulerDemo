package com.github.kirillf.jobschedulerdemo

import android.app.job.JobParameters
import android.app.job.JobService

/**
 * Created by kirillf on 10/11/16.
 */

class ServiceJob : JobService() {

    override fun onStartJob(params: JobParameters): Boolean {
        ScheduledService.startScheduledJob(applicationContext,
                String.format(MESSAGE, params.jobId))
        return false
    }

    override fun onStopJob(params: JobParameters): Boolean {
        return false
    }

    companion object {
        private const val MESSAGE = "Started job with id %s"
    }
}
