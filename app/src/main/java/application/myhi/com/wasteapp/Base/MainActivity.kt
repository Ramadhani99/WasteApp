package application.myhi.com.wasteapp

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.widget.ProgressBar
import android.widget.Toast
import com.loopj.android.http.RequestParams
import com.loopj.android.http.TextHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var user:User
    lateinit var mProgressBar:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!SharedPreferenceHelper.getLoggedStatus(this@MainActivity)){
            startActivity(Intent(
                    this@MainActivity,LoginActicity::class.java
            ))
        }
        else{
            user=SharedPreferenceHelper.getUser(this@MainActivity);

            if (user.is_driver==1){
                reportBtn.visibility=GONE
            }
            else{
                viewReportBtn.visibility= GONE
            }

        }

        logoutBtn.setOnClickListener {
            SharedPreferenceHelper.setLoggedIn(this@MainActivity,false)
            startActivity(Intent(
                    this@MainActivity,LoginActicity::class.java
            ))
        }

        reportBtn.setOnClickListener {

            var params=RequestParams()
            params.put("user_id",user.user_id);
            params.put("location_name",user.location_name)
            params.put("token",user.token)
            params.put("lat",user.latitude)
            params.put("lng",user.longtude)

            Log.e("Token",user.token+user.toString())



            ServerConnection.post(Url.notification,params, object : TextHttpResponseHandler() {

                override fun onStart() {
                    super.onStart()
                    mProgressBar=ProgressDialog(this@MainActivity)
                    mProgressBar.setTitle("Submit Report")
                    mProgressBar.setMessage("Please wait...............")
                    mProgressBar.show()
                }

                override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {

                     mProgressBar.hide()
                    var jsonResult= JSONObject(responseString)
                    if (!jsonResult.getBoolean("success")){
                        Toast.makeText(this@MainActivity,jsonResult.getString("message")+ ".... Contact Adminstrator",Toast.LENGTH_SHORT).show()
                        return
                    }
                    Toast.makeText(this@MainActivity,"failed to submit check Internet connection try again",Toast.LENGTH_SHORT).show()
                     Log.e("Main Acticity",responseString.toString())
                }

                override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseString: String?) {
                    mProgressBar.hide()
                    var jsonResult= JSONObject(responseString)
                    if (!jsonResult.getBoolean("success")){
                        Toast.makeText(this@MainActivity,jsonResult.getString("message")+ ".... Contact Adminstrator",Toast.LENGTH_SHORT).show()
                        return
                    }

                    Toast.makeText(this@MainActivity,"successfully submit the report",Toast.LENGTH_SHORT).show()
                    Log.e("Main Acticity","................................................")

                    Log.e("Main Acticity",responseString.toString())

                }

            })
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
