
package application.myhi.com.wasteapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.loopj.android.http.TextHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.login.*


/**

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
 * Created by root on 6/3/19.
 */

class LoginActicity: AppCompatActivity(){

    lateinit var mUsername:String
    lateinit var mPassword:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        mUsername=username.text.toString()
        mPassword=password.text.toString()




        loginBtn.setOnClickListener {
            if (username.text.isNullOrEmpty()){
                Snackbar.make(loginLayout,"Username required",Snackbar.LENGTH_SHORT).show()


            }
            else if(password.text.isNullOrEmpty()){
                Snackbar.make(loginLayout,"Password required",Snackbar.LENGTH_SHORT).show()

            }


            else{
                processLogin(mUsername,mPassword)
            }
        }
    }

    private fun processLogin(username:String,passwors:String){

       ServerConnection.get(Url.Login,null,object: TextHttpResponseHandler(){
           override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseString: String?) {

           }

           override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {

           }

       })
    }
}