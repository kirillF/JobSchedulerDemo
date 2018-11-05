package com.github.kirillf.jobschedulerdemo

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.widget.Toast

class ScheduledService : IntentService("ScheduledService") {

    override fun onHandleIntent(intent: Intent?) {
        if (intent!!.action == ACTION) {
            val message = intent.getStringExtra(EXTRA_MESSAGE)
            Log.i("Scheduled service", message)
            Handler(mainLooper).post { Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show() }
        }
    }

    companion object {
        private const val EXTRA_MESSAGE = "extra_message"
        private const val ACTION = "scheduled_action"

        fun startScheduledJob(context: Context, message: String) {
            val intent = Intent(context, ScheduledService::class.java)
            intent.action = ACTION
            intent.putExtra(EXTRA_MESSAGE, message)
            context.startService(intent)
        }
    }
}
