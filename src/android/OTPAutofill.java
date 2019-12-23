package com.muakl.cordova.plugin;

import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class OTPAutofill extends CordovaPlugin {
    
  //  @Override
public boolean execute(String action,JSONArray args,
    final CallbackContext callbackContext){
        // Verify that the user sent a 'detect' action
        if(!action.equals("detect")){
            callbackContext.error("\"" + action + "\" is not a recognized action.");
            return false;//new PluginResult(PluginResult.Status.INVALID_ACTION);
        }
        String message;
        try{
            JSONObject options = args.getJSONObject(0);
            message = options.getString("message");
        }  catch(JSONException e){
            callbackContext.error("Error encountered: " + e.getMessage());
            return false;//new PluginResult(PluginResult.Status.ERROR);
        }
            Pattern p = Pattern.compile("\\d{3,10}");
            Matcher m = p.matcher(message);
            String OTP ;
            
	    	if(m.find()) {
	    		OTP =m.group();
	    	}
	    	else {
	    		OTP="";
            }
            
            if(OTP !=""){
                Toast toast = Toast.makeText(cordova.getActivity(), OTP,Toast.LENGTH_LONG);
                toast.show();
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK,OTP);
                callbackContext.sendPluginResult(pluginResult);
                return true;//new PluginResult(PluginResult.Status.OK, OTP);
            }
            callbackContext.error("Couldn't detect otp");
            return false;
    }
}