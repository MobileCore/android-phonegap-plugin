cordova.define("com.mobilecore.phonegap.MobilecorePlugin", function(require, exports, module) { var MobilecorePlugin = {


/**
 * Function to init Mobilecore
 * @param successcallback
 * @param errorcallback
 * @param devHash 
 */
initMobilecore: function(successCallback, errorCallback, devHash, logLevel, adUnits) {
   	if (errorCallback == null) { errorCallback = function() {}}
       if (typeof errorCallback != "function")  {
           console.log("error callback parameter must be a function");
           return
       }
       if (typeof successCallback != "function") {
           console.log("success callback parameter must be a function");
           return
       }
       
   	cordova.exec(
               successCallback, // success callback function
               errorCallback, // error callback function
               'MobilecorePlugin', // mapped to our native Java class called "MobilecorePlugin"
               'initMobilecore',   // with this action name
               [				   // and this array of custom arguments to create our entry
				{                
                   "devHash": devHash,
				   "logLevel": logLevel,
				   "adUnits": adUnits
				}
			   ]
       ); 
},
   
/**
* Function to Show Interstitial
* @param successcallback
* @param errorcallback
*/
showInterstitial: function(successCallback, errorCallback, trigger) {
	if (errorCallback == null) { errorCallback = function() {}}
       if (typeof errorCallback != "function")  {
           console.log("error callback parameter must be a function");
           return
       }
       if (typeof successCallback != "function") {
           console.log("success callback parameter must be a function");
           return
       }
       
	cordova.exec(
               successCallback, // success callback function
               errorCallback, // error callback function
               'MobilecorePlugin', // mapped to our native Java class called
								// "MobilecorePlugin"
               'showInterstitial', // with this action name
               [{                  // and this array of custom arguments to
								// create our entry
                   "trigger" : trigger
               }]
       ); 
},

/**
 * Function to show Stickee
 * @param successcallback
 * @param errorcallback
 */
showStickee: function(successCallback, errorCallback, trigger) {
		if (errorCallback == null) { errorCallback = function() {}}
        if (typeof errorCallback != "function")  {
            console.log("error callback parameter must be a function");
            return
        }
        if (typeof successCallback != "function") {
            console.log("success callback parameter must be a function");
            return
        }
        
		cordova.exec(
                successCallback, // success callback function
                errorCallback, // error callback function
                'MobilecorePlugin', // mapped to our native Java class called
									// "MobilecorePlugin"
                'showStickee', // with this action name
                [{                  // and this array of custom arguments to
									// create our entry
                   "trigger" : trigger
                }]
        );
},

/**
 * Function to hide Stickee
 * @param successcallback
 * @param errorcallback
 */
hideStickee: function(successCallback, errorCallback) {
		if (errorCallback == null) { errorCallback = function() {}}
        if (typeof errorCallback != "function")  {
            console.log("error callback parameter must be a function");
            return
        }
        if (typeof successCallback != "function") {
            console.log("success callback parameter must be a function");
            return
        }
        
		cordova.exec(
                successCallback, // success callback function
                errorCallback, // error callback function
                'MobilecorePlugin', // mapped to our native Java class called
									// "MobilecorePlugin"
                'hideStickee', // with this action name
                [{                  // and this array of custom arguments to
									// create our entry
                }]
        );
},

/**
 * Function to check if Stickee is showing
 * @param successcallback
 * @param errorcallback
 */
isStickeeShowing: function(successCallback, errorCallback) {
		if (errorCallback == null) { errorCallback = function() {}}
        if (typeof errorCallback != "function")  {
            console.log("error callback parameter must be a function");
            return
        }
        if (typeof successCallback != "function") {
            console.log("success callback parameter must be a function");
            return
        }
        
		cordova.exec(
                successCallback, // success callback function
                errorCallback, // error callback function
                'MobilecorePlugin', // mapped to our native Java class called
									// "MobilecorePlugin"
                'isStickeeShowing', // with this action name
                [{                  // and this array of custom arguments to
									// create our entry
                }]
        );
},

/**
 * Function to check if Stickee is showing offers
 * @param successcallback
 * @param errorcallback
 */
isStickeeShowingOffers: function(successCallback, errorCallback) {
		if (errorCallback == null) { errorCallback = function() {}}
        if (typeof errorCallback != "function")  {
            console.log("error callback parameter must be a function");
            return
        }
        if (typeof successCallback != "function") {
            console.log("success callback parameter must be a function");
            return
        }
        
		cordova.exec(
                successCallback, // success callback function
                errorCallback, // error callback function
                'MobilecorePlugin', // mapped to our native Java class called
									// "MobilecorePlugin"
                'isStickeeShowingOffers', // with this action name
                [{                  // and this array of custom arguments to
									// create our entry
                }]
        );
},

/**
 * Function to set Stickeez position
 * @param successcallback
 * @param errorcallback
 * @param position
 */
setStickeezPosition: function(successCallback, errorCallback, position) {
		if (errorCallback == null) { errorCallback = function() {}}
        if (typeof errorCallback != "function")  {
            console.log("error callback parameter must be a function");
            return
        }
        if (typeof successCallback != "function") {
            console.log("success callback parameter must be a function");
            return
        }
        
		cordova.exec(
                successCallback, // success callback function
                errorCallback, // error callback function
                'MobilecorePlugin', // mapped to our native Java class called
									// "MobilecorePlugin"
                'setStickeezPosition', // with this action name
                [{                  // and this array of custom arguments to
									// create our entry
                    "position": position
                }]
        );
},

/**
 * Function to show Direct to Market
 * @param successcallback
 * @param errorcallback
 */
directToMarket: function(successCallback, errorCallback, trigger) {
		if (errorCallback == null) { errorCallback = function() {}}
        if (typeof errorCallback != "function")  {
            console.log("error callback parameter must be a function");
            return
        }
        if (typeof successCallback != "function") {
            console.log("success callback parameter must be a function");
            return
        }
        
		cordova.exec(
                successCallback, // success callback function
                errorCallback, // error callback function
                'MobilecorePlugin', // mapped to our native Java class called
									// "MobilecorePlugin"
                'directToMarket', // with this action name
                [{                  // and this array of custom arguments to
									// create our entry
                    "trigger" : trigger
                }]
        );
},

/**
 * Function to register Ad Units Event Listener
 * @param successcallback
 * @param errorcallback
 */
setAdUnitEventListener: function(successCallback, errorCallback) {
        if (errorCallback == null) { errorCallback = function() {}}
        if (typeof errorCallback != "function")  {
            console.log("error callback parameter must be a function");
            return
        }
        if (typeof successCallback != "function") {
            console.log("success callback parameter must be a function");
            return
        }
        
        cordova.exec(
                successCallback, // success callback function
                errorCallback, // error callback function
                'MobilecorePlugin', // mapped to our native Java class called
                                    // "MobilecorePlugin"
                'setAdUnitEventListener', // with this action name
                [{                  // and this array of custom arguments to
                                    // create our entry
                }]
        );
},

/**
 * Function to check if Ad Unit is ready with particular Trigger
 */
isReady: function(successCallback, errorCallback, adUnit, trigger) {
        if (errorCallback == null) { errorCallback = function() {}}
        if (typeof errorCallback != "function")  {
            console.log("error callback parameter must be a function");
            return
        }
        if (typeof successCallback != "function") {
            console.log("success callback parameter must be a function");
            return
        }
        
        cordova.exec(
                successCallback, // success callback function
                errorCallback, // error callback function
                'MobilecorePlugin', // mapped to our native Java class called
                                    // "MobilecorePlugin"
                'isReady', // with this action name
                [{                  // and this array of custom arguments to
                                    // create our entry
                   "ad_unit": adUnit,
                   "trigger": trigger
                }]
        );
},

/**
 * Function to load Ad Unit with particular Trigger
 */
loadAdUnit: function(successCallback, errorCallback, adUnit, triggers) {
        if (errorCallback == null) { errorCallback = function() {}}
        if (typeof errorCallback != "function")  {
            console.log("error callback parameter must be a function");
            return
        }
        if (typeof successCallback != "function") {
            console.log("success callback parameter must be a function");
            return
        }
        
        cordova.exec(
                successCallback, // success callback function
                errorCallback, // error callback function
                'MobilecorePlugin', // mapped to our native Java class called
                                    // "MobilecorePlugin"
                'loadAdUnit', // with this action name
                [{                  // and this array of custom arguments to
                                    // create our entry
                   "ad_unit": adUnit,
                },
                triggers]
        );
},

}

MobilecorePlugin.AD_UNIT_INIT_SUCCEEDED = "AD_UNIT_INIT_SUCCEEDED";
MobilecorePlugin.AD_UNIT_INIT_FAILED = "AD_UNIT_INIT_FAILED";
MobilecorePlugin.AD_UNIT_READY = "AD_UNIT_READY";
MobilecorePlugin.AD_UNIT_NOT_READY = "AD_UNIT_NOT_READY";
MobilecorePlugin.AD_UNIT_DISMISSED = "AD_UNIT_DISMISSED";
MobilecorePlugin.AD_UNIT_ALREADY_SHOWING = "AD_UNIT_ALREADY_SHOWING";
MobilecorePlugin.AD_UNIT_SHOW_ERROR = "AD_UNIT_SHOW_ERROR";
MobilecorePlugin.AD_UNIT_SHOW = "AD_UNIT_SHOW";
MobilecorePlugin.AD_UNIT_LOAD_ERROR = "AD_UNIT_LOAD_ERROR";
MobilecorePlugin.AD_UNIT_CLICK = "AD_UNIT_CLICK";
MobilecorePlugin.AD_UNIT_SENT_TO_STORE = "AD_UNIT_SENT_TO_STORE";
MobilecorePlugin.AD_UNIT_SENT_TO_STORE_FAILED = "AD_UNIT_SENT_TO_STORE_FAILED";
MobilecorePlugin.AD_UNIT_TRIGGER_DISABLED = "AD_UNIT_TRIGGER_DISABLED";
MobilecorePlugin.AD_UNIT_ALREADY_LOADING = "AD_UNIT_ALREADY_LOADING";

MobilecorePlugin.STICKEEZ_POSITION_TOP_LEFT = 10;
MobilecorePlugin.STICKEEZ_POSITION_TOP_RIGHT = 11;
MobilecorePlugin.STICKEEZ_POSITION_MIDDLE_LEFT = 12;
MobilecorePlugin.STICKEEZ_POSITION_MIDDLE_RIGHT = 13;
MobilecorePlugin.STICKEEZ_POSITION_BOTTOM_LEFT = 14;
MobilecorePlugin.STICKEEZ_POSITION_BOTTOM_RIGHT = 15;

MobilecorePlugin.AD_UNIT_INTERSTITIAL = "AD_UNIT_INTERSTITIAL";
MobilecorePlugin.AD_UNIT_STICKEEZ = "AD_UNIT_STICKEEZ";
MobilecorePlugin.AD_UNIT_DIRECT_TO_MARKET = "AD_UNIT_DIRECT_TO_MARKET";

MobilecorePlugin.AD_UNIT_TRIGGER_DEFAULT = "DEFAULT";
MobilecorePlugin.AD_UNIT_TRIGGER_APP_START = "APP_START";
MobilecorePlugin.AD_UNIT_TRIGGER_APP_EXIT = "APP_EXIT";
MobilecorePlugin.AD_UNIT_TRIGGER_BUTTON_CLICK = "BUTTON_CLICK";
MobilecorePlugin.AD_UNIT_TRIGGER_GAME_LEVEL_END_WIN = "GAME_LEVEL_END_WIN";
MobilecorePlugin.AD_UNIT_TRIGGER_GAME_LEVEL_END_LOSE = "GAME_LEVEL_END_LOSE";
MobilecorePlugin.AD_UNIT_TRIGGER_MAIN_MENU = "MAIN_MENU";
MobilecorePlugin.AD_UNIT_TRIGGER_SETTINGS_SCREEN = "SETTINGS_SCREEN";
MobilecorePlugin.AD_UNIT_TRIGGER_ACTION_START = "ACTION_START";
MobilecorePlugin.AD_UNIT_TRIGGER_ACTION_BREAK = "ACTION_BREAK";
MobilecorePlugin.AD_UNIT_TRIGGER_ACTION_END = "ACTION_END";
        
MobilecorePlugin.AD_UNIT_TRIGGER_NULL = "NULL";
MobilecorePlugin.AD_UNIT_TRIGGER_NONE = "NONE";

//-------------------------------------------------------------------

module.exports = MobilecorePlugin;
});
