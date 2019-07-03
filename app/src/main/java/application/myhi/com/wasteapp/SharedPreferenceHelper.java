
package application.myhi.com.wasteapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

/**
 * Created by root on 6/14/19.
 */

public class SharedPreferenceHelper {

    private static String User="USER";
    private static String LOGGED_IN_PREF="USER";

  static SharedPreferences getPreferences(Context context){
      return PreferenceManager.getDefaultSharedPreferences(context);
  }

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
      user=new Gson().fromJson(userjson,User.class);
      return user;


  }
public static void setLoggedIn(Context context,boolean loggedIn){


      SharedPreferences.Editor  editor=getPreferences(context).edit();
      editor.clear();
      editor.putBoolean(LOGGED_IN_PREF,loggedIn);
      editor.apply();
}

public static boolean getLoggedStatus(Context context){
    return getPreferences(context).getBoolean(LOGGED_IN_PREF,false);
}
}
