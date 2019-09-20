$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:Resources/AmazonLoginFunctionality.feature");
formatter.feature({
  "name": "Validating Amazon Login Functionality",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@Login"
    }
  ]
});
formatter.scenarioOutline({
  "name": "Validate Login fails with invalid credentials",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "User can click on SignIn Option under Accounts and Lists Widget",
  "keyword": "Given "
});
formatter.step({
  "name": "User Enter \"\u003cUsername\u003e\" and \"\u003cPassword\u003e\" as credentials",
  "keyword": "When "
});
formatter.step({
  "name": "User should see Error Message stating Login Failed",
  "keyword": "Then ",
  "rows": [
    {
      "cells": [
        "There was a problem"
      ]
    }
  ]
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "Username",
        "",
        "Password"
      ]
    },
    {
      "cells": [
        "kovidmehta10@gmail.com",
        "",
        "wallahHabibi"
      ]
    }
  ]
});
formatter.background({
  "name": "Pre-requisites of Login Functionality",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Website is up and running",
  "keyword": "Given "
});
formatter.match({
  "location": "Definitions.WebsiteIsUpandRunning()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Validate Login fails with invalid credentials",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Login"
    }
  ]
});
formatter.step({
  "name": "User can click on SignIn Option under Accounts and Lists Widget",
  "keyword": "Given "
});
formatter.match({
  "location": "Definitions.SignInOption()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User Enter \"kovidmehta10@gmail.com\" and \"wallahHabibi\" as credentials",
  "keyword": "When "
});
formatter.match({
  "location": "Definitions.EnterCreds(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User should see Error Message stating Login Failed",
  "rows": [
    {
      "cells": [
        "There was a problem"
      ]
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "Definitions.LoginFailedValidation(DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenarioOutline({
  "name": "Validate User is able to log out post successful login",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "User can click on SignIn Option under Accounts and Lists Widget",
  "keyword": "Given "
});
formatter.step({
  "name": "User Enter \"\u003cUsername\u003e\" and \"\u003cPassword\u003e\" as credentials",
  "keyword": "When "
});
formatter.step({
  "name": "Validate User Login",
  "keyword": "Then "
});
formatter.step({
  "name": "Click on Log Out to redirect on Sign In Page",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "Username",
        "",
        "Password"
      ]
    },
    {
      "cells": [
        "kovidmehta10@gmail.com",
        "",
        "honeybees@93"
      ]
    }
  ]
});
formatter.background({
  "name": "Pre-requisites of Login Functionality",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Website is up and running",
  "keyword": "Given "
});
formatter.match({
  "location": "Definitions.WebsiteIsUpandRunning()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Validate User is able to log out post successful login",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Login"
    }
  ]
});
formatter.step({
  "name": "User can click on SignIn Option under Accounts and Lists Widget",
  "keyword": "Given "
});
formatter.match({
  "location": "Definitions.SignInOption()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User Enter \"kovidmehta10@gmail.com\" and \"honeybees@93\" as credentials",
  "keyword": "When "
});
formatter.match({
  "location": "Definitions.EnterCreds(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Validate User Login",
  "keyword": "Then "
});
formatter.match({
  "location": "Definitions.LoginVerification()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Click on Log Out to redirect on Sign In Page",
  "keyword": "And "
});
formatter.match({
  "location": "Definitions.ValidateUserLogOut()"
});
formatter.result({
  "error_message": "org.openqa.selenium.JavascriptException: javascript error: Cannot read property \u0027left\u0027 of undefined\n  (Session info: chrome\u003d76.0.3809.100)\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027IN-KOMEHTA-W1\u0027, ip: \u0027172.28.221.207\u0027, os.name: \u0027Windows 10\u0027, os.arch: \u0027amd64\u0027, os.version: \u002710.0\u0027, java.version: \u002712.0.2\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities {acceptInsecureCerts: false, browserName: chrome, browserVersion: 76.0.3809.100, chrome: {chromedriverVersion: 76.0.3809.126 (d80a294506b4..., userDataDir: C:\\Users\\komehta\\AppData\\Lo...}, goog:chromeOptions: {debuggerAddress: localhost:55259}, javascriptEnabled: true, networkConnectionEnabled: false, pageLoadStrategy: normal, platform: XP, platformName: XP, proxy: Proxy(), setWindowRect: true, strictFileInteractability: false, timeouts: {implicit: 0, pageLoad: 300000, script: 30000}, unhandledPromptBehavior: dismiss and notify}\nSession ID: 77a5810fab116fd84bcb14b2db5d86e9\r\n\tat java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\r\n\tat java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\r\n\tat java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\r\n\tat java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)\r\n\tat java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)\r\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.createException(W3CHttpResponseCodec.java:187)\r\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:122)\r\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:49)\r\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:158)\r\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:83)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.perform(RemoteWebDriver.java:618)\r\n\tat org.openqa.selenium.interactions.Actions$BuiltAction.perform(Actions.java:639)\r\n\tat GlueCode.Definitions.ValidateUserLogOut(Definitions.java:498)\r\n\tat ✽.Click on Log Out to redirect on Sign In Page(file:Resources/AmazonLoginFunctionality.feature:22)\r\n",
  "status": "failed"
});
formatter.after({
  "status": "passed"
});
formatter.scenarioOutline({
  "name": "Validate User can log in with valid credentials",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "User can click on SignIn Option under Accounts and Lists Widget",
  "keyword": "Given "
});
formatter.step({
  "name": "User Enter \"\u003cUsername\u003e\" and \"\u003cPassword\u003e\" as credentials",
  "keyword": "When "
});
formatter.step({
  "name": "Validate User Login",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "Username",
        "",
        "Password"
      ]
    },
    {
      "cells": [
        "kovidmehta10@gmail.com",
        "",
        "honeybees@93"
      ]
    }
  ]
});
formatter.background({
  "name": "Pre-requisites of Login Functionality",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Website is up and running",
  "keyword": "Given "
});
formatter.match({
  "location": "Definitions.WebsiteIsUpandRunning()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Validate User can log in with valid credentials",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Login"
    }
  ]
});
formatter.step({
  "name": "User can click on SignIn Option under Accounts and Lists Widget",
  "keyword": "Given "
});
formatter.match({
  "location": "Definitions.SignInOption()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User Enter \"kovidmehta10@gmail.com\" and \"honeybees@93\" as credentials",
  "keyword": "When "
});
formatter.match({
  "location": "Definitions.EnterCreds(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Validate User Login",
  "keyword": "Then "
});
formatter.match({
  "location": "Definitions.LoginVerification()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});