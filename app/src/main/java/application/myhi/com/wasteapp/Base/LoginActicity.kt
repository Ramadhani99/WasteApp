
package application.myhi.com.wasteapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.loopj.android.http.RequestParams
import com.loopj.android.http.TextHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.login.*
import org.json.JSONObject


/**

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
 * Created by root on 6/3/19.
 */

class LoginActicity: AppCompatActivity(){

    lateinit var mUsername:String
    lateinit var mPassword:String
    lateinit var mProgressBar:ProgressDialog


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

                processLogin(username.text.toString(),password.text.toString())
            }
        }
    }

    private fun processLogin(username:String,password:String){

        var params= RequestParams()
        params.put("email",username)
        params.put("password",password)

        Log.e("Login Activity...",username+password)
        Log.e("Login Activity...","my log")



       ServerConnection.post(Url.Login,params,object: TextHttpResponseHandler(){

           override fun onStart() {
               super.onStart()
               mProgressBar= ProgressDialog(this@LoginActicity)
               mProgressBar.setTitle("Login")
               mProgressBar.setMessage("Please wait...............")
               mProgressBar.show()
           }

           override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseString: String?) {
               Log.e("Login page",responseString)
               mProgressBar.hide()

               var jsonResult=JSONObject(responseString)
               var userdetail=jsonResult.getJSONObject("user")
               Log.e("LOGIN ACTIVITY",jsonResult.getString("token")+"String");

               var token=jsonResult.getString("token")
               var user_id=userdetail.getInt("user_id")
               var is_driver=userdetail.getInt("is_driver")
               var location=userdetail.getString("location_name")+" "
               var lat=userdetail.getString("latitude")
               var long=userdetail.getString("longitude")




              try {
                  var user=User(token,user_id,is_driver,location,lat,long)

                  val json= Gson().toJson(user)


                  SharedPreferenceHelper.storeUser(this@LoginActicity,json)
                  SharedPreferenceHelper.setLoggedIn(this@LoginActicity,true)


              }catch (exp:Exception){
                  Log.e("LoginActivity",exp.message)
              }

              Toast.makeText(this@LoginActicity,"Login Successfully",Toast.LENGTH_SHORT).show();
               startActivity(Intent(
                       this@LoginActicity,MainActivity::class.java
               ))


           }

           override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {
               Log.e("Login page failed",responseString.toString())
               mProgressBar.hide()
               Snackbar.make(loginLayout,"Wrong Username and Password",Snackbar.LENGTH_SHORT).show()

           }



       })
    }
}