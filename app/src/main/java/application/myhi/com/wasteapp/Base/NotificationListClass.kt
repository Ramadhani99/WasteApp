package application.myhi.com.wasteapp

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.notification_list_layout.*
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 * Created by root on 6/18/19.
 */


class NotificationListClass:AppCompatActivity(){

    @RequiresApi(Build.VERSION_CODES.O)
    private val requuestlist=listOf(
            Report("mikumi street", DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneOffset.UTC)
                    .format(Instant.now())),
            Report("Mwananyamala street", DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneOffset.UTC)
                    .format(Instant.now())),
            Report("Kinondoni street", DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneOffset.UTC)
                    .format(Instant.now())),
            Report("Manyanya street", DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneOffset.UTC)
                    .format(Instant.now())),
            Report("Tandale street", DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneOffset.UTC)
                    .format(Instant.now())),
            Report("Togo street", DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneOffset.UTC)
                    .format(Instant.now())),
            Report("Sikukuu street", DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneOffset.UTC)
                    .format(Instant.now())),
            Report("Nyamwezi street", DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneOffset.UTC)
                    .format(Instant.now())),
            Report("Kinondoni street", DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneOffset.UTC)
                    .format(Instant.now()))


    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notification_list_layout)

        recylerView.apply {
            layoutManager=LinearLayoutManager(context)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                adapter= NotificationAdapter(requestList = requuestlist)
            }
        }
    }
}