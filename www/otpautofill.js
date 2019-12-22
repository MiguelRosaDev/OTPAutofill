
function OTPAutofill(){}

// The function that passes work along to native shells
//Message is the whole message containing the OTP recieved by the server 
OTPAutofill.prototype.detect = function(message,successCallback,errorCallback){
    var options={};
    options.message=message;
    cordova.exec(successCallback,errorCallback,'OTPAutofill','detect',[options]);

}


OTPAutofill.install=function(){
    if(!window.plugins){
        window.plugins={};
    }
    window.plugins.otpAutofill = new OTPAutofill();
    return window.plugins.otpAutofill;
};
cordova.addConstructor(OTPAutofill.install);