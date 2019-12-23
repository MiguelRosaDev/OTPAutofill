package com.muakl.cordova.plugin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class OTPAutofill extends CordovaPlugin {
    
public boolean execute(String action,JSONArray args,
    final CallbackContext callbackContext){
        // Verify that the user sent a 'detect' action
        if(!action.equals("detect")){
            callbackContext.error("\"" + action + "\" is not a recognized action.");
            return false;
        }
        String message;
        try{
            JSONObject options = args.getJSONObject(0);
            message = options.getString("message");
        }  catch(JSONException e){
            callbackContext.error("Error encountered: " + e.getMessage());
            return false;
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
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK,OTP);
                callbackContext.sendPluginResult(pluginResult);
                return true;
            }
            callbackContext.error("Couldn't detect otp");
            return false;
    }
}
