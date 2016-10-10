package com.github.kirillf.jobschedulerdemo;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.schedule_job_button);
        View.OnClickListener listener = new ScheduleJobClickListener(this);
        button.setOnClickListener(listener);
    }

    static class ScheduleJobClickListener implements View.OnClickListener {
        private final Context context;

        ScheduleJobClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            ComponentName serviceComponent = new ComponentName(context, ServiceJob.class);
            int id = (int) System.currentTimeMillis();
            JobInfo.Builder infoBuilder = new JobInfo.Builder(id, serviceComponent);
            infoBuilder.setMinimumLatency(1000)
                       .setOverrideDeadline(2 * 1000);

            JobInfo info = infoBuilder.build();

            JobScheduler scheduler = (JobScheduler) context.getSystemService(JOB_SCHEDULER_SERVICE);
            scheduler.schedule(info);
        }

    }
}
