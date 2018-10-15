package com.example.azerty.feelae

import android.app.*
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.beust.klaxon.Klaxon
import com.example.azerty.feelae.fragments.PrescriptionListFragment
import com.example.azerty.feelae.model.Traitement
import com.example.elgrim.seasonal.http.APIController
import com.example.elgrim.seasonal.http.ServiceVolley
import kotlinx.android.synthetic.main.recap_activity.*
import android.os.Build
import android.content.Intent
import android.app.AlarmManager
import android.os.SystemClock
import android.app.PendingIntent
import android.util.Log
import com.example.azerty.feelae.notification.NOTIFICATION
import com.example.azerty.feelae.notification.NOTIFICATION_ID
import com.example.azerty.feelae.notification.NotificationPublisher
import com.example.azerty.feelae.utils.concat
import java.sql.Time
import java.util.*

class RecapActivity : AppCompatActivity() {
    var traitementData: List<Traitement>? = null
    var traitement : ArrayList<Traitement>? = null

    private val prescriptionfragment: PrescriptionListFragment by lazy {
        PrescriptionListFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSymptome()
        setContentView(R.layout.recap_activity)

        recap_glp.setOnClickListener {
            finish()
        }

        button_home.setOnClickListener {
            finish()
        }

        recap_remind_me.setOnClickListener {
            createNotificationChannel()
            makeNotification()
            finish()
        }

        recap_drug.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getSymptome() {
        val service = ServiceVolley()
        val apiContrller = APIController(service)

        apiContrller.get("/bdd2.json", null) { response ->
            if (response != null) {
                traitementData = Klaxon().parseArray(response.toString())
                traitement = traitementData as ArrayList<Traitement>

                traitement?.map {
                    recap_symptome.text = concat(it.symptome).toString()
                    recap_traitement.text = concat(it.traitement).toString()
                }
            }
        }
    }

    private fun makeNotification () {
        val x = 0
        val dateOnM = 86400
        traitement?.map {
            it.details.map {
                scheduleNotification(getNotification(it.medicament), 5000)
                while (x  > it.day) {
                    val day = x*dateOnM
                    when (it.occurence) {
                        1 -> {
                            scheduleNotification(getNotification(it.medicament), (480+day)*1000)
                        }

                        2 -> {
                            scheduleNotification(getNotification(it.medicament), (480+day)*1000)
                            scheduleNotification(getNotification(it.medicament), (720+day)*1000)
                        }

                        3 -> {
                            scheduleNotification(getNotification(it.medicament), (480+day)*1000)
                            scheduleNotification(getNotification(it.medicament), (720+day)*1000)
                            scheduleNotification(getNotification(it.medicament), (960+day)*1000)
                        }

                        4 -> {
                            scheduleNotification(getNotification(it.medicament), (480+day)*1000)
                            scheduleNotification(getNotification(it.medicament), (720+day)*1000)
                            scheduleNotification(getNotification(it.medicament), (960+day)*1000)
                            scheduleNotification(getNotification(it.medicament), (1200+day)*1000)
                        }
                    }
                }
            }
        }
    }

    private fun scheduleNotification(notification: Notification?, delay: Int) {

        val notificationIntent = Intent(this, NotificationPublisher::class.java)
        notificationIntent.putExtra(NOTIFICATION_ID, 1)
        notificationIntent.putExtra(NOTIFICATION, notification)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val futureInMillis = SystemClock.elapsedRealtime() + delay
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent)
        Log.d("noti", "in  scheduleNotification")
    }

    private fun getNotification(medicament: String): Notification? {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val mBuilder = Notification.Builder(this, "com.example.azerty.feelae")
                .setSmallIcon(R.drawable.ic_warning_black_24dp)
                .setContentTitle("Rappel Prise de $medicament")
                .setContentText(getString(R.string.notification_traitement))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        return mBuilder.build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val description = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("com.example.azerty.feelae", name, importance)
            channel.description = description
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}
