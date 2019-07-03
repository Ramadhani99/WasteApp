package application.myhi.com.wasteapp


import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import application.myhi.com.wasteapp.Storage.Notification
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by root on 6/18/19.
 */
class NotificationAdapter(private val requestList:ArrayList<Notification>,val context:ButtonClickListener): RecyclerView.Adapter<NotificationAdapter.viewHolder>() {



    lateinit var mButtonClickListener:ButtonClickListener
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewHolder {
        val inflater=LayoutInflater.from(p0.context);
        return viewHolder(inflater,p0)


    }


    override fun getItemCount(): Int =requestList.size

    override fun onBindViewHolder(p0: viewHolder, p1: Int) {
      val report:Notification=requestList[p1]
        this.mButtonClickListener=context
       p0.bind(report,mButtonClickListener)
    }


    class viewHolder(inflater: LayoutInflater,parent:ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.notification_layout,parent,false)){

        private var mPlaceName:TextView?=null
        private var mRequestTiem:TextView?=null
        private var mLocationBtn:TextView?=null
        private var mDOneBtn:TextView?=null

        init {
            mPlaceName=itemView.findViewById(R.id.placeNmae)
            mRequestTiem=itemView.findViewById(R.id.requestTime)
            mLocationBtn=itemView.findViewById(R.id.locationBtn)
            mDOneBtn=itemView.findViewById(R.id.doneBtn)
        }

        fun bind(report:Notification,mButtonClickListener:ButtonClickListener){
          mPlaceName?.text=report.street
          mRequestTiem?.text=report.time

         mLocationBtn?.setOnClickListener {
             showMap(report)
             Toast.makeText(itemView.context,"Location view",Toast.LENGTH_SHORT).show()

         }
            mDOneBtn?.setOnClickListener {



                mButtonClickListener.doneButtonClicked(report.id)

            }
        }

        fun showMap(notifictaion:Notification){

            val uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)",
                    notifictaion.latitude,
                    notifictaion.longitude, notifictaion.street)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            intent.`package` = "com.google.android.apps.maps"
            if (intent.resolveActivity(itemView.context.packageManager) != null) {
                itemView.context.startActivity(intent)
            }
            else{
                val urimap = "http://maps.google.com/maps?&daddr="+notifictaion.latitude+"," +notifictaion.longitude

                val intentMap = Intent(Intent.ACTION_VIEW, Uri.parse(urimap))
                itemView.context.startActivity(intentMap)

            }



        }








    }

    interface ButtonClickListener{
        fun doneButtonClicked(id:Int)
    }
}