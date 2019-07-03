package application.myhi.com.wasteapp


import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import application.myhi.com.wasteapp.NotificationAdapter.ButtonClickListener
import application.myhi.com.wasteapp.Storage.Notification
import com.google.gson.Gson
import com.loopj.android.http.RequestParams
import com.loopj.android.http.TextHttpResponseHandler
import cz.msebera.android.httpclient.Header

import kotlinx.android.synthetic.main.notification_list_layout.*
import kotlinx.android.synthetic.main.progress_bar_layout.*
import kotlinx.android.synthetic.main.request_failed_layout.*
import org.json.JSONObject


import kotlin.collections.ArrayList

/**
 * Created by root on 6/18/19.
 */


class NotificationListClass:AppCompatActivity(),ButtonClickListener{
    override fun doneButtonClicked(id: Int) {
        clearNotifictaion(id)
    }


    lateinit var user:User
    private var mNotificationList:ArrayList<Notification> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notification_list_layout)
//        Log.e("........list",mNotificationList.toString()+"inafikaa....")
        getReport()

        tryaAgainBtn.setOnClickListener {
            getReport()
        }

//
//
//         Log.e("........list",mNotificationList.toString()+"haifiki....")






    }



    fun getReport(){


        user=SharedPreferenceHelper.getUser(this@NotificationListClass);

        var params=RequestParams()
        params.put("user_id",user.user_id)
        params.put("token",user.token)




        ServerConnection.get(Url.driver_notification,params,object:TextHttpResponseHandler(){

            override fun onStart() {
                super.onStart()
                wProgressBar.show()
                failedView.visibility= GONE
                mNotificationList.clear()
            }


            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseString: String?) {

                //Log.e("NotificationListClass",responseString.toString())
                wProgressBar.hide()

                val response=JSONObject(responseString)
                val NotificatioJson=response.getJSONArray("data")

                if (NotificatioJson.length()<1){
                    Log.e("No data",NotificatioJson.toString())
                    failedView.visibility=VISIBLE
                    failedMsg.text="No Notification Try Again Later"

                }else{
                    failedView.visibility= GONE
                    for (i in 0 until NotificatioJson.length() ){

                        val mNotification=Gson().fromJson(NotificatioJson.get(i).toString(),Notification::class.java)

                        Log.e("NotificationClass",mNotification.toString())
                        mNotificationList.add(mNotification)

                    }
                    Log.e("Notifictaion list",mNotificationList.toString())


                }
                recylerView.apply {
                    layoutManager=LinearLayoutManager(context)


                    adapter= NotificationAdapter(requestList = mNotificationList,context=this@NotificationListClass)

                }

            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {
                failedView.visibility=VISIBLE

                Log.e("Notification failed",responseString.toString())
                wProgressBar.hide()


            }
        })
    }

    fun clearNotifictaion(id:Int){
        Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show()

        var params= RequestParams()
        params.put("notification_id",id)
        params.put("token",user.token)
        ServerConnection.patch(Url.clear_notification,params,object: TextHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseString: String?) {

                Log.e("Sucess solve problem",responseString.toString())
                getReport()


            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {
                Log.e("Failed solve problem",responseString.toString())

            }
        })
    }
}