﻿# Payeezy Android SDK

Quickly integrate Payeezy from First Data into your Android application.

If you want to enable secure and convenient payments in your Android app this guide will get you up and running within minutes. The Payeezy Service from First Data was created to simplify your integration with Android. Payeezy handles all the heavy lifting of the complex cryptography that protects your customers’ transactions. It also makes it super simple to create a developer test account and even apply for a merchant account all through our developer portal.

* To download and set up Android Studio , the official Android IDE, please refer to http://developer.android.com/sdk/index.html
# Minimum technical requirements
*	The Payeezy android mobile SDK requires android OS 4.0 ice cream sandwich or higher (API level 14 or higher).
*	A physical device or an emulator to use for developing and testing because Google Play services can only be installed on an emulator with an AVD that runs Google APIs platform based on Android 4.0 or higher.
*	The latest version of the Android SDK, including the SDK Tools component. The SDK is available from the Android SDK Manager.
*	Your project to compile against Android 4.0 (Ice Cream Sandwich) or higher.
*	The latest version of Android Studio.
*	Android SDK requires Java JRE (JDK for development) as per android guidelines.

For quick start on android sample application please refer to the link https://developers.google.com/+/quickstart/android.

# Payeezy Prerequisites
Please refer to prerequisites document for 
*	Developer registration
*	API Key, secret and merchant token generation for sandbox/test region
*	Download SDK from GitHub

# TokenBased Transactions - 
*	Generate Token with ta_token - auth false - GET API
*	Generate Token with ta_token - auth true - GET API
*	Generate Token without  ta_token & auth -  - GET API with 0$ Auth
*	Generate Token - Backward compatible -  GET API call

For more information on step by step integration, please refer our Integration Guide

# Getting Started with Payeezy
Using below listed steps, you can easily integrate your mobile/web payment application with Payeezy APIs and go LIVE!
*	LITE  - REGISTRATION  
*	GET CERTIFIED
*	ADD MERCHANTS 
*	LIVE!

![alt tag](https://github.com/payeezy/payeezy_js/raw/master/ignore/get_start_with_payeezy.png)

For more information on getting started, visit  [get_start_with_payeezy guide] (https://github.com/payeezy/get_started_with_payeezy/blob/master/get_started_with_payeezy042015.pdf) or [download](https://github.com/payeezy/get_started_with_payeezy/raw/master/get_started_with_payeezy042015.pdf)

#How to Secure APIKey/token etc??
Securing APIKey, token constant values in Android app : http://stackoverflow.com/questions/14570989/best-practice-for-storing-private-api-keys-in-android

# Testing - Payeezy {SANDBOX region}
For test credit card,eCheck,GiftCard please refer to [test data ](https://github.com/payeezy/testing_payeezy/blob/master/payeezy_testdata042015.pdf) or [download] (https://github.com/payeezy/testing_payeezy/raw/master/payeezy_testdata042015.pdf)

# Error code/response - Payeezy {SANDBOX/PROD region}
For HTTP status code, API Error codes and error description please refer to [API error code ](https://developer.payeezy.com/payeezy_new_docs/apis) and select your API

#Questions?
We're always happy to help with code or other questions you might have! Check out [FAQ page](https://developer.payeezy.com/faq-page) or call us. 

## Contributing
1. Fork it 
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create a new Pull Request  

## Feedback
We appreciate the time you take to try out our sample code and welcome your feedback. Here are a few ways to get in touch:
* For generally applicable issues and feedback, create an issue in this repository.
* support@payeezy.com - for personal support at any phase of integration
* [1.855.799.0790](tel:+18557990790)  - for personal support in real time 

## Terms of Use
Terms and conditions for using Payeezy Android SDK: Please see [Payeezy Terms & conditions](https://developer.payeezy.com/terms-use)
 
### License
The Payeezy Android SDK is open source and available under the MIT license. See the LICENSE file for more info.
