package com.john.kotlindemo


import android.content.Context
import android.content.res.TypedArray
import android.net.Uri
import android.text.TextUtils
import android.util.AttributeSet

import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.generic.GenericDraweeHierarchy
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder

/**
 * Created by John
 *
 *
 * =============================================================================
 * <com.john.widget.DraweeView android:layout_margin="10dp" android:id="@+id/id_drawee_view" android:layout_width="300dp" android:layout_height="300dp" fresco:failureImage="@mipmap/failed" fresco:actualImageScaleType="focusCrop" fresco:placeholderImage="@mipmap/loading" fresco:retryImage="@mipmap/retry"></com.john.widget.DraweeView>
 *
 *
 *
 * =============================================================================
 */
class DraweeView : SimpleDraweeView {

    private var mUrl: String? = null

    constructor(context: Context, hierarchy: GenericDraweeHierarchy) : super(context, hierarchy) {}

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        if (null != attrs) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.DraweeView)
            if (a.hasValue(R.styleable.DraweeView_url)) {
                mUrl = a.getString(R.styleable.DraweeView_url)
            }
            a.recycle()
        }

    }

    /**
     * 支持 直接资源引用

     * @param res
     */
    fun setUrl(res: Int?) {
        val uri = Uri.parse("res:///" + res!!)
        mUrl = uri.path
        this.displayImage(uri)
    }

    fun setUrl(url: String) {
        mUrl = url
        this.displayImage(mUrl)
    }

    fun getUrl(): String? {
        return mUrl
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (!TextUtils.isEmpty(mUrl))
            this.displayImage(mUrl)
    }

    /**
     * 类型 	          Scheme 	             示例
     * 远程图片 	          http://, https:// 	 HttpURLConnection 或者参考 使用其他网络加载方案
     * 本地文件               file:// 	             FileInputStream
     * Content provider 	  content:// 	         ContentResolver
     * asset目录下的资源 	  asset:// 	             AssetManager
     * res目录下的资源 	      res:// 	             Resources.openRawResource

     * @param url
     */
    fun displayImage(url: String?) {
        if (null != url) {
            this.controller = getController(Uri.parse(url))
        } else
            displayFail()
    }

    private fun displayFail() {
        this.controller = getController(Uri.parse(""))
    }

    fun displayImage(uri: Uri?) {
        if (null != uri) {
            this.controller = getController(uri)
        } else
            displayFail()
    }

    protected fun getController(uri: Uri): DraweeController {
        //可以设置 .setTapToRetryEnabled(true) 来让他加载失败时再点击一次重新加载图片，但是这个会引发拦截住 touch事件，比如 listview中item的点击事件
        return Fresco.newDraweeControllerBuilder().setAutoPlayAnimations(true)
                .setImageRequest(ImageRequestBuilder.newBuilderWithSource(uri)
                        .build()).build()
    }
}
