package com.github.kirillf.jobschedulerdemo

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listener = ScheduleJobClickListener(this)
        scheduleJobButton.setOnClickListener(listener)
    }

    internal class ScheduleJobClickListener(private val context: Context) : View.OnClickListener {

        override fun onClick(v: View) {
            val serviceComponent = ComponentName(context, ServiceJob::class.java)
            val id = System.currentTimeMillis().toInt()
            val infoBuilder = JobInfo.Builder(id, serviceComponent)
            infoBuilder.setMinimumLatency(1000)
                    .setOverrideDeadline((2 * 1000).toLong())

            val info = infoBuilder.build()

            val scheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            scheduler.schedule(info)
        }

    }
}
