package com.mobilecore.phonegap;

import java.util.ArrayList;
import java.util.List;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;

import com.ironsource.mobilcore.AdUnitEventListener;
import com.ironsource.mobilcore.CallbackResponse;
import com.ironsource.mobilcore.MobileCore;
import com.ironsource.mobilcore.MobileCore.AD_UNITS;
import com.ironsource.mobilcore.MobileCore.EStickeezPosition;
import com.ironsource.mobilcore.MobileCore.LOG_TYPE;
import com.ironsource.mobilcore.OnReadyListener;

public class MobilecorePlugin extends CordovaPlugin implements CallbackResponse, OnReadyListener, AdUnitEventListener  {
	public static final String TAG = "MCPhoneGap";
	private static final boolean DEBUG = true;

	public static final String ACTION_INIT = "initMobilecore";
	public static final String ACTION_SHOW_INTERSTITIAL = "showInterstitial";
	public static final String ACTION_OPEN_URL = "openUrl";
	public static final String ACTION_REFRESH_OFFERS = "refreshOffers";
	public static final String ACTION_IS_INTERSTITIAL_READY = "isInterstitialReady";

	public static final String ACTION_SET_INTERSTITIAL_READY_LISTENER = "setInterstitialReadyListener";
	public static final String ACTION_SET_AD_UNIT_EVENT_LISTENER = "setAdUnitEventListener";

	// Stickeez
	public static final String ACTION_STICKEEZ_SHOW_STICKEE = "showStickee";
	public static final String ACTION_STICKEEZ_HIDE_STICKEE = "hideStickee";
	public static final String ACTION_STICKEEZ_IS_STICKEE_READY = "isStickeeReady";
	public static final String ACTION_STICKEEZ_IS_STICKEE_SHOWING = "isStickeeShowing";
	public static final String ACTION_STICKEEZ_IS_STICKEE_SHOWING_OFFERS = "isStickeeShowingOffers";
	public static final String ACTION_STICKEEZ_SET_STICKEEZ_READY_LISTENER = "setStickeezReadyListener";
	public static final String ACTION_STICKEEZ_SET_POSITION = "setStickeezPosition";

	// Direct to Market
	public static final String ACTION_DIRECT_TO_MARKET = "directToMarket";
	public static final String ACTION_DIRECT_TO_MARKET_IS_READY = "isDirectToMarketReady";
	public static final String ACTION_DIRECT_TO_MARKET_SET_READY_LISTENER = "setDirectToMarketReadyListener";

	// AD_UNITS consts
	public static final String INTERSTITIAL = MobileCore.AD_UNITS.INTERSTITIAL.toString();
	public static final String STICKEEZ = MobileCore.AD_UNITS.STICKEEZ.toString();
	public static final String DIRECT_TO_MARKET = MobileCore.AD_UNITS.DIRECT_TO_MARKET.toString();

	// LOG_TYPE consts
	public static final String LOG_DEBUG = MobileCore.LOG_TYPE.DEBUG.toString();
	public static final String LOG_PRODUCTION = MobileCore.LOG_TYPE.PRODUCTION.toString();

	// stickeez positions
	public static final int STICKEEZ_POSITION_TOP_LEFT = 10;
	public static final int STICKEEZ_POSITION_TOP_RIGHT = 11;
	public static final int STICKEEZ_POSITION_MIDDLE_LEFT = 12;
	public static final int STICKEEZ_POSITION_MIDDLE_RIGHT = 13;
	public static final int STICKEEZ_POSITION_BOTTOM_LEFT = 14;
	public static final int STICKEEZ_POSITION_BOTTOM_RIGHT = 15;

	// events
	public static final String AD_UNIT_READY = AdUnitEventListener.EVENT_TYPE.AD_UNIT_READY.toString();
	public static final String AD_UNIT_NOT_READY = AdUnitEventListener.EVENT_TYPE.AD_UNIT_NOT_READY.toString();
	public static final String AD_UNIT_DISMISSED = AdUnitEventListener.EVENT_TYPE.AD_UNIT_DISMISSED.toString();	

	private CallbackContext mCallbackCtx;

	private CallbackContext mInterstitialReadyCtx;
	
	private CallbackContext mAdUnitEventCtx;

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (DEBUG) {
			Log.d(TAG, "execute | action=" + action);
		}
		try {

			// General:

			if (ACTION_INIT.equals(action)) {
				JSONObject arg_object = args.getJSONObject(0);
				final String devHash = arg_object.getString("devHash");
				if (DEBUG) {
					Log.d(TAG, "devHash=" + devHash);
				}

				final String logLevel = arg_object.getString("logLevel");
				LOG_TYPE logType;
				if (!TextUtils.isEmpty(logLevel) && LOG_DEBUG.equals(logLevel)) {
					logType = LOG_TYPE.DEBUG;
				} else {
					logType = LOG_TYPE.PRODUCTION;
				}
				final LOG_TYPE finalLogType = logType;

				JSONArray adUnitsArray = arg_object.optJSONArray("adUnits");
				if (DEBUG) {
					Log.d(TAG, "adUnitsArray=" + adUnitsArray);
				}
				AD_UNITS[] mCAdUnits;
				if (adUnitsArray != null && adUnitsArray.length() > 0) {
					List<AD_UNITS> units = new ArrayList<AD_UNITS>();
					for (int i = 0; i < adUnitsArray.length(); i++) {
						String s = adUnitsArray.optString(i);
						if (!TextUtils.isEmpty(s)) {
							if (INTERSTITIAL.equals(s)) {
								units.add(AD_UNITS.INTERSTITIAL);
							} else if (STICKEEZ.equals(s)) {
								units.add(AD_UNITS.STICKEEZ);
							} else if (DIRECT_TO_MARKET.equals(s)) {
								units.add(AD_UNITS.DIRECT_TO_MARKET);
							} else {
								continue;
							}
						}
					}
					mCAdUnits = new AD_UNITS[units.size()];
					mCAdUnits = units.toArray(mCAdUnits);
				} else {
					mCAdUnits = new AD_UNITS[] { AD_UNITS.INTERSTITIAL, AD_UNITS.STICKEEZ, AD_UNITS.DIRECT_TO_MARKET };
				}
				final AD_UNITS[] finalMCAdUnits = mCAdUnits;
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						if (DEBUG) {
							Log.d(TAG, "finalLogType=" + finalLogType);
							for (int j = 0; j < finalMCAdUnits.length; j++) {
								Log.d(TAG, "finalMCAdUnits[" + j + "]=" + finalMCAdUnits[j]);

							}
						}
						MobileCore.init(cordova.getActivity(), devHash, finalLogType, finalMCAdUnits);
					}
				});
				callbackContext.success();
				return true;
			} else if (ACTION_SHOW_INTERSTITIAL.equals(action)) {
				this.mCallbackCtx = callbackContext;
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						MobileCore.showInterstitial(cordova.getActivity(), MobilecorePlugin.this);
					}
				});
				return true;
			} else if (ACTION_REFRESH_OFFERS.equals(action)) {
				this.mCallbackCtx = callbackContext;
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						MobileCore.refreshOffers();
					}
				});
				callbackContext.success();
				return true;
			} else if (ACTION_OPEN_URL.equals(action)) {
				JSONObject arg_object = args.getJSONObject(0);
				final String url = arg_object.getString("url");
				final boolean internal = arg_object.getBoolean("internal");
				if (DEBUG) {
					Log.d(TAG, "url=" + url + " | internal=" + internal);
				}
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						MobileCore.openUrl(cordova.getActivity(), url, internal);
					}
				});
				callbackContext.success();
				return true;
			} else if (ACTION_SET_INTERSTITIAL_READY_LISTENER.equals(action)) {
				mInterstitialReadyCtx = callbackContext;
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						MobileCore.setInterstitialReadyListener(MobilecorePlugin.this);
					}
				});
				return true;
			} else if (ACTION_IS_INTERSTITIAL_READY.equals(action)) {
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						boolean isInterstitialReady = MobileCore.isInterstitialReady();
						if (DEBUG) {
							Log.d(TAG, "isInterstitialReady=" + isInterstitialReady);
						}
						PluginResult result = new PluginResult(PluginResult.Status.OK, isInterstitialReady);
						result.setKeepCallback(false);
						mCallbackCtx.sendPluginResult(result);
					}
				});
				return true;

			}

			// Stickeez:
			else if (ACTION_STICKEEZ_IS_STICKEE_READY.equals(action)) {
				this.mCallbackCtx = callbackContext;
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						boolean isStickeeReady = MobileCore.isStickeeReady();
						if (DEBUG) {
							Log.d(TAG, "isStickeeReady=" + isStickeeReady);
						}
						PluginResult result = new PluginResult(PluginResult.Status.OK, isStickeeReady);
						result.setKeepCallback(false);
						mCallbackCtx.sendPluginResult(result);
					}
				});
				return true;
			} else if (ACTION_STICKEEZ_SHOW_STICKEE.equals(action)) {
				this.mCallbackCtx = callbackContext;
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						MobileCore.showStickee(cordova.getActivity());
					}
				});
				return true;
			} else if (ACTION_STICKEEZ_HIDE_STICKEE.equals(action)) {
				this.mCallbackCtx = callbackContext;
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						MobileCore.hideStickee();
					}
				});
				return true;
			} else if (ACTION_STICKEEZ_IS_STICKEE_SHOWING.equals(action)) {
				this.mCallbackCtx = callbackContext;
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						boolean isStickeeShowing = MobileCore.isStickeeShowing();
						if (DEBUG) {
							Log.d(TAG, "isStickeeShowing=" + isStickeeShowing);
						}
						PluginResult result = new PluginResult(PluginResult.Status.OK, isStickeeShowing);
						result.setKeepCallback(false);
						mCallbackCtx.sendPluginResult(result);
					}
				});
				return true;
			} else if (ACTION_STICKEEZ_IS_STICKEE_SHOWING_OFFERS.equals(action)) {
				this.mCallbackCtx = callbackContext;
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						boolean isStickeeShowingOffers = MobileCore.isStickeeShowingOffers();
						if (DEBUG) {
							Log.d(TAG, "isStickeeShowingOffers=" + isStickeeShowingOffers);
						}
						PluginResult result = new PluginResult(PluginResult.Status.OK, isStickeeShowingOffers);
						result.setKeepCallback(false);
						mCallbackCtx.sendPluginResult(result);
					}
				});
				return true;
			} else if (ACTION_STICKEEZ_SET_STICKEEZ_READY_LISTENER.equals(action)) {
				mInterstitialReadyCtx = callbackContext;
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						MobileCore.setStickeezReadyListener(MobilecorePlugin.this);
					}
				});
				return true;
			} else if (ACTION_STICKEEZ_SET_POSITION.equals(action)) {
				mInterstitialReadyCtx = callbackContext;
				JSONObject arg_object = args.getJSONObject(0);
				final int position = arg_object.getInt("position");
				if (DEBUG) {
					Log.d(TAG, "position=" + position);
				}
				final EStickeezPosition ePosition = getPosition(position);
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						MobileCore.setStickeezPosition(ePosition);
					}
				});
				return true;
			}

			// Direct to market
			else if (ACTION_DIRECT_TO_MARKET.equals(action)) {
				this.mCallbackCtx = callbackContext;
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						MobileCore.directToMarket(cordova.getActivity());
					}
				});
				return true;
			} else if (ACTION_DIRECT_TO_MARKET_IS_READY.equals(action)) {
				this.mCallbackCtx = callbackContext;
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						boolean isDirectToMarketReady = MobileCore.isDirectToMarketReady();
						if (DEBUG) {
							Log.d(TAG, "isDirectToMarketReady=" + isDirectToMarketReady);
						}
						PluginResult result = new PluginResult(PluginResult.Status.OK, isDirectToMarketReady);
						result.setKeepCallback(false);
						mCallbackCtx.sendPluginResult(result);
					}
				});
				return true;
			} else if (ACTION_DIRECT_TO_MARKET_SET_READY_LISTENER.equals(action)) {
				mInterstitialReadyCtx = callbackContext;
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						MobileCore.setDirectToMarketReadyListener(MobilecorePlugin.this);
					}
				});
				return true;
			} else if (ACTION_SET_AD_UNIT_EVENT_LISTENER.equals(action)) {
				mAdUnitEventCtx = callbackContext;
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						MobileCore.setAdUnitEventListener(MobilecorePlugin.this);
					}
				});
				return true;
			}
			callbackContext.error("Invalid action");
			return false;
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			callbackContext.error(e.getMessage());
			return false;
		}
	}

	private EStickeezPosition getPosition(int position) {
		switch (position) {
		case STICKEEZ_POSITION_TOP_LEFT:
			return EStickeezPosition.TOP_LEFT;
		case STICKEEZ_POSITION_TOP_RIGHT:
			return EStickeezPosition.TOP_RIGHT;
		case STICKEEZ_POSITION_MIDDLE_LEFT:
			return EStickeezPosition.MIDDLE_LEFT;
		case STICKEEZ_POSITION_MIDDLE_RIGHT:
			return EStickeezPosition.MIDDLE_RIGHT;
		case STICKEEZ_POSITION_BOTTOM_LEFT:
			return EStickeezPosition.BOTTOM_LEFT;
		case STICKEEZ_POSITION_BOTTOM_RIGHT:
			return EStickeezPosition.BOTTOM_RIGHT;
		default:
			return EStickeezPosition.BOTTOM_LEFT;
		}
	}

	@Override
	public void onConfirmation(TYPE type) {
		String callbackString = type.toString();
		if (DEBUG) {
			Log.d(TAG, "onConfirmation | callbackString=" + callbackString);
		}
		PluginResult result = new PluginResult(PluginResult.Status.OK, callbackString);
		result.setKeepCallback(true);
		mCallbackCtx.sendPluginResult(result);
	}

	public void onInterstitialReady() {
		if (DEBUG) {
			Log.d(TAG, "onInterstitialReady");
		}
		PluginResult result = new PluginResult(PluginResult.Status.OK, "onInterstitialReady");
		result.setKeepCallback(true);
		mInterstitialReadyCtx.sendPluginResult(result);
	}

	public void onStickeezReady() {
		if (DEBUG) {
			Log.d(TAG, "onStickeezReady");
		}
		PluginResult result = new PluginResult(PluginResult.Status.OK, "onStickeezReady");
		result.setKeepCallback(true);
		mInterstitialReadyCtx.sendPluginResult(result);
	}

	@Override
	public void onReady(AD_UNITS adUnit) {
		if (adUnit == AD_UNITS.INTERSTITIAL) {
			onInterstitialReady();
		} else if (adUnit == AD_UNITS.STICKEEZ) {
			onStickeezReady();
		}
	}

	@Override
	public void onAdUnitEvent(AD_UNITS adUnit, EVENT_TYPE eventType) {
		if (mAdUnitEventCtx == null || adUnit == AD_UNITS.NATIVE_ADS) {
			return;
		}

		try {
			JSONObject resultJson = new JSONObject();
			resultJson.put("ad_unit", adUnit.toString());
			resultJson.put("event_type", eventType.toString());
			PluginResult result = new PluginResult(PluginResult.Status.OK, resultJson);
			result.setKeepCallback(true);
			mAdUnitEventCtx.sendPluginResult(result);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
