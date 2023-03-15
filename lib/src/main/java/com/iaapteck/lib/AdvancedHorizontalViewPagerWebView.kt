package com.iaapteck.lib

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.animation.LinearInterpolator
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlin.math.abs
import kotlin.math.ceil

/**
 * @author gautam iaapteck on 22/6/17.
 */
class AdvancedHorizontalViewPagerWebView(context: Context,
                                         attrs: AttributeSet) : WebView(context, attrs) {
    private var x1 = -1f
    private var pageCount = 0
    private var currentPage = 0
    private var currentX = 0
    private var delta: Int = 30
    private val prevPagePosition: Int
        get() = ceil((--currentPage * this.measuredWidth).toDouble()).toInt()

    private val nextPagePosition: Int
        get() = ceil((++currentPage * this.measuredWidth).toDouble()).toInt()

    init {
        setDelta()
        this.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                injectJavascript()
            }
        }

        this.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                val pageCount = Integer.parseInt(message)
                this@AdvancedHorizontalViewPagerWebView.setPageCount(pageCount)
                injectCSS()
                result.confirm()
                return true
            }
        }
    }

    private fun setDelta() {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        delta = (displayMetrics.widthPixels * 0.04).toInt()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> super.onTouchEvent(event)
            MotionEvent.ACTION_DOWN -> {
                x1 = event.x
                return super.onTouchEvent(event)
            }
            MotionEvent.ACTION_UP -> {
                val x2 = event.x
                val deltaX = x2 - x1
                if (abs(deltaX) > delta) {
                    // Left to Right swipe action
                    return if (x2 > x1) {
                        turnPageLeft(deltaX)
                        true
                    } else {
                        turnPageRight(deltaX)
                        true
                    }// Right to left swipe action
                }
            }
            else -> super.onTouchEvent(event)
        }
        return super.onTouchEvent(event)
    }

    private fun turnPageLeft(deltaX: Float) {
        if (currentPage > 0) {
            val scrollX = prevPagePosition
            loadAnimation(scrollX, deltaX)
            currentX = scrollX
            scrollTo(scrollX, 0)
        }
    }

    private fun turnPageRight(deltaX: Float) {
        if (currentPage < pageCount - 1) {
            val scrollX = nextPagePosition
            loadAnimation(scrollX + PADDING_OFFSET, deltaX)
            currentX = scrollX + PADDING_OFFSET
            scrollTo(scrollX + PADDING_OFFSET, 0)
        }
    }

    private fun loadAnimation(scrollX: Int, deltaX: Float) {
        val anim = ObjectAnimator.ofInt(this, "scrollX",
                currentX - deltaX.toInt(), scrollX)
        anim.duration = SCROLL_DURATION
        anim.interpolator = LinearInterpolator()
        anim.start()
    }

    private fun injectJavascript() {
        val js = "function initialize(){\n" +
                "    var d = document.getElementsByTagName('body')[0];\n" +
                "    var ourH = window.innerHeight - 40;\n" +
                "    var ourW = window.innerWidth - (2*20);\n" +
                "    var fullH = d.offsetHeight;\n" +
                "    var pageCount = Math.floor(fullH/ourH)+1;\n" +
                "    var currentPage = 0;\n" +
                "    var newW = pageCount*window.innerWidth - (2*20);\n" +
                "    d.style.height = ourH+'px';\n" +
                "    d.style.width = newW+'px';\n" +
                "    d.style.margin = 0;\n" +
                "    d.style.webkitColumnGap = '40px';\n" +
                "    d.style.webkitColumnCount = pageCount;\n" +
                "    document.head.innerHTML = document.head.innerHTML + '<meta name=\"viewport\" content=\"height=device-height, user-scalable=no\" />';" +
                "    return pageCount;\n" +
                "}"
        this.loadUrl("javascript:$js")
        this.loadUrl("javascript:alert(initialize())")
    }

    private fun injectCSS() {
        this.loadUrl("javascript:(function() {" +
                "var parent = document.getElementsByTagName('head').item(0);" +
                "var style = document.createElement('style');" +
                "style.type = 'text/css';" +
                "style.innerHTML = 'body { padding: 20px 20px !important; }';" +
                "parent.appendChild(style)" +
                "})()")
    }

    fun setPageCount(pageCount: Int) {
        this.pageCount = pageCount
    }

    companion object {
        const val SCROLL_DURATION: Long = 400
        const val PADDING_OFFSET = 10
    }
}