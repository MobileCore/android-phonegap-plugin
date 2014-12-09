package com.mobilecore.phonegap;

import java.lang.reflect.Method;

import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaWebView;

import android.os.Bundle;

import com.ironsource.mobilcore.MobileCore;
import com.ironsource.mobilcore.MobileCore.AD_UNITS;
import com.ironsource.mobilcore.UserProperties.Gender;
import com.ironsource.mobilcore.UserProperties;

public class MobilecoreActivity extends CordovaActivity {

	String TAG = "MainActivity CordovaInterface";

	CordovaWebView mainView;

	private final String MOBILECORE_DEV_HASH = "3FCONBTOP58OJE0UTVFCE947OC88D"; // REPLACE THIS WITH YOUR OWN DEV TOKEN

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Use this if you want to provide us information about your users:
		UserProperties userProperties = new UserProperties().setAgeRange(20, 40)
															.setGender(Gender.FEMALE);		
		MobileCore.init(this,
						MOBILECORE_DEV_HASH,
						userProperties,
						MobileCore.LOG_TYPE.DEBUG,
						AD_UNITS.INTERSTITIAL,
						AD_UNITS.STICKEEZ,
						AD_UNITS.DIRECT_TO_MARKET);
		
		/*MobileCore.init(this,
				MOBILECORE_DEV_HASH,
				MobileCore.LOG_TYPE.DEBUG,
				AD_UNITS.INTERSTITIAL,
				AD_UNITS.STICKEEZ,
				AD_UNITS.DIRECT_TO_MARKET);*/

		try {
			Method method = MobileCore.class.getDeclaredMethod("setMediationParams", String.class);
			method.setAccessible(true);
			@SuppressWarnings("unused")
            Object o = method.invoke(null, "{\"plugin\":\"phonegap\"}");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		super.init();

		loadUrl("file:///android_asset/www/index.html");
	}
}
