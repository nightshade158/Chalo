package com.example.chalo.data.worker

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.chalo.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class BookingReminderWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val listingTitle = inputData.getString("listingTitle") ?: "Your booking"

        val notification = androidx.core.app.NotificationCompat.Builder(applicationContext, "booking_reminders")
            .setContentTitle("Upcoming Booking Reminder")
            .setContentText("$listingTitle is coming up soon!")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(androidx.core.app.NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val manager = NotificationManagerCompat.from(applicationContext)
        try {
            manager.notify(1001, notification)
        } catch (e: SecurityException) {
            // Notification permission not granted - fail silently for now
        }

        return Result.success()
    }
}