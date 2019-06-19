package application.myhi.com.wasteapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast


/**
 * Created by root on 6/18/19.
 */
class NotificationAdapter(private val requestList:List<Report>): RecyclerView.Adapter<NotificationAdapter.viewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): viewHolder {
        val inflater=LayoutInflater.from(p0.context);
        return viewHolder(inflater,p0)

    }

    override fun getItemCount(): Int =requestList.size

    override fun onBindViewHolder(p0: viewHolder, p1: Int) {
      val report:Report=requestList[p1]
       p0.bind(report)
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

        fun bind(report:Report){
          mPlaceName?.text=report.streeName
          mRequestTiem?.text=report.time

         mLocationBtn?.setOnClickListener {
             Toast.makeText(itemView.context,"Location view",Toast.LENGTH_SHORT).show()

         }
            mDOneBtn?.setOnClickListener {
                Toast.makeText(itemView.context,"activity complete",Toast.LENGTH_SHORT).show()
            }
        }



    }
}