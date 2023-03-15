# Advanced-Android-Horizontal-View-Pager-Swipe-WebView

Custom Advanced WebView implementation for horizontal swipes in android `WebView`.

The class `AdvancedHorizontalViewPagerWebView` converts the webpage to enable scrolling horizontally for paging animation.

# Demo

![Alt text](https://media.giphy.com/media/4KEZmAdRDLos71TtdL/giphy.gif)

# Usage

```` java
AdvancedHorizontalViewPagerWebView advancedHorizontalViewPagerWebView = findViewById(R.id.web_view);
advancedHorizontalViewPagerWebView.getSettings().setJavaScriptEnabled(true);
    
advancedHorizontalViewPagerWebView.loadUrl("file:///android_asset/ch03.html"); // give the source from assets

````

License
=======
Copyright 2023 iAapTeck Software Labs

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
