package com.john.kotlinanddatabindingdemo

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.john.kotlindemo.R
import com.john.kotlindemo.databinding.ActivityOtherBinding

class OtherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityOtherBinding =DataBindingUtil.setContentView(this, R.layout.activity_other)

        val user:User= User()
        user.name="这是第二个activity"
        user.photo="http://wx.zshisong.com:8085/imgServer/upload/pic/02f3b3322a844014923453e95806f8a2.jpg"
        binding.data=user
    }
}
