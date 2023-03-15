# Advanced-Android-Horizontal-View-Pager-Swipe-WebView

## Custom Advanced WebView implementation for horizontal swipes in android `WebView`.

The class `AdvancedHorizontalViewPagerWebView` converts the webpage to enable scrolling horizontally for paging animation. This repository's source code offers a solution to the horizontal scrolling problem that occurs when you place a webview inside a viewpager fragment.



# Demo

![Alt text](https://media.giphy.com/media/4KEZmAdRDLos71TtdL/giphy.gif)


## How to Add "Advanced-Android-Horizontal-View-Pager-Swipe-WebView" 

> #### Step 1. Add the JitPack repository to your build file

```` gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
````

> #### Step 2. Add the dependency

```` gradle
dependencies {
	        implementation 'com.github.iAapTeck-Software-Labs:Advanced-Horizontal-View-Pager-Swipe-WebView:1.0.1'
	}
````

> #### Step 3. Add the kotlin/java coding inside your MainFragment Class [Not for MainActivity]

```` java
// --- Java ---
WebView.setWebContentsDebuggingEnabled(true)
AdvancedHorizontalViewPagerWebView advancedHorizontalViewPagerWebView = findViewById(R.id.web_view);
advancedHorizontalViewPagerWebView.getSettings().setJavaScriptEnabled(true);
advancedHorizontalViewPagerWebView.loadUrl("file:///android_asset/ch03.html"); // give the source from assets
````
OR
```` kotlin
// --- Kotlin ---
WebView.setWebContentsDebuggingEnabled(true)
val wv = findViewById<AdvancedHorizontalViewPagerWebView>(R.id.web_view)
wv.settings.javaScriptEnabled = true
wv.loadUrl("https://www.youtube.com/")// now it will not fail here
````


License
=======
### Copyright 2023 iAapTeck Software Labs

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
