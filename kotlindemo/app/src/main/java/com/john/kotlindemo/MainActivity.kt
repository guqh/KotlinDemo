package com.john.kotlindemo

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.john.kotlinanddatabindingdemo.OtherActivity
import com.john.kotlindemo.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //    val webUrl ="https://kotlinlang.org/docs/tutorials/android-frameworks.html"
    val webUrl ="https://www.baidu.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.data="这是一个Kotlin和dataBinding的组合应用demo \n 并包含了自定义View  DraweeView"

        //点下试试
        bt_test.setOnClickListener {
            val  mSettings : WebSettings = web_test.settings
            mSettings.javaScriptEnabled=true
            web_test.loadUrl(webUrl)
            web_test.setWebViewClient(WebViewClient())
        }
    }

    //启动新界面
    fun startactivity(view: View){
        startActivity(Intent(this, OtherActivity::class.java))
    }



    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode== KeyEvent.KEYCODE_BACK&&web_test.canGoBack()){
            web_test.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
    override fun onDestroy() {
        if (web_test != null) {
            web_test.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
            web_test.clearHistory()
            (web_test.getParent() as ViewGroup).removeView(web_test)
            web_test.destroy()
        }
        super.onDestroy()
    }
}
