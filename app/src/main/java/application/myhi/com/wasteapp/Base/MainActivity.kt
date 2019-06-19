package application.myhi.com.wasteapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reportBtn.setOnClickListener {
            startActivity(Intent(
                    this@MainActivity,LoginActicity::class.java
            ))
        }

        viewReportBtn.setOnClickListener {
            startActivity(
                    Intent(
                            this@MainActivity,NotificationListClass::class.java
                    )
            )
        }


    }
}
