package application.myhi.com.wasteapp.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by root on 6/14/19.
 */

public class SharedPreferenceHelper {

    private static String User="USER";

  public  static void storeUser(Context context,String userJson){
      SharedPreferences sharedPreferences = context.getSharedPreferences(User,Context.MODE_PRIVATE);
      SharedPreferences.Editor userEditor=sharedPreferences.edit();
      userEditor.clear();
      userEditor.putString(User,userJson);
      userEditor.apply();
      userEditor.commit();

  }

  public static User getUser(Context context){
      User user=null;
      SharedPreferences sharedPreferences=context.getSharedPreferences(User,0);
      String userjson=sharedPreferences.getString(User,"");
      user=new Gson().fromJson(userjson, application.myhi.com.wasteapp.Storage.User.class);
      return user;


  }
}
