package com.example.basecleanarchapp.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Base64
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.view.animation.TranslateAnimation
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.FontRes
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.basecleanarchapp.R
import com.example.data.remote.RetrofitClient
import com.github.chrisbanes.photoview.PhotoView
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.skydoves.powerspinner.PowerSpinnerView
import com.skydoves.powerspinner.SpinnerGravity
import com.yariksoffice.lingver.Lingver
import io.github.muddz.styleabletoast.StyleableToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.*
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Type
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.DayOfWeek
import java.time.temporal.WeekFields
import java.util.*

fun Context.showSuccess(message: String) {
    StyleableToast.Builder(this)
        .text(message)
        .textColor(Color.WHITE)
        .backgroundColor(
            this.findColor(
                R.color.green
            )
        )
        .cornerRadius(5.toPx)
        .font(R.font.light)
        .textSize(11f)
        .gravity(Gravity.BOTTOM)
        .iconStart(R.drawable.ic_check)
        .length(Toast.LENGTH_LONG)
        .solidBackground()
        .stroke(1.toPx, this.findColor(R.color.green))
        .show()
}

@RequiresApi(Build.VERSION_CODES.O)
fun daysOfWeekFromLocale(): Array<DayOfWeek> {
    val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
    var daysOfWeek = DayOfWeek.values()
    // Order `daysOfWeek` array so that firstDayOfWeek is at index 0.
    // Only necessary if firstDayOfWeek != DayOfWeek.MONDAY which has ordinal 0.
    if (firstDayOfWeek != DayOfWeek.SUNDAY) {
        val rhs = daysOfWeek.sliceArray(firstDayOfWeek.ordinal..daysOfWeek.indices.last)
        val lhs = daysOfWeek.sliceArray(0 until firstDayOfWeek.ordinal)
        daysOfWeek = rhs + lhs
    }
    return daysOfWeek
}

fun FragmentActivity.shareProduct(promo: String, title: String?) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    var shareMessage = promo
    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
    shareIntent.putExtra(Intent.EXTRA_TITLE, title)
    this.startActivity(Intent.createChooser(shareIntent, "${title}"))
}

fun RecyclerView.bindEndlessScroll(callback: () -> Unit): RecyclerEndlessScrollListener {
    val endlessScroll = object :
        RecyclerEndlessScrollListener(this.layoutManager) {
        override fun onLoadMore() {
            callback()
        }
    }
    this.addOnScrollListener(endlessScroll)
    return endlessScroll
}

fun Any.log(tag: String = "") {
    if (tag.equals("")) {
        Log.d("TAG_QMR", this.toString())
    } else {
        Log.d("TAG_QMR $tag", this.toString())

    }
}

fun Double.getDecimals(): String {
    val symbols = DecimalFormatSymbols(Locale.US)
    val def = DecimalFormat("#", symbols)
    def.roundingMode = RoundingMode.UP
    return def.format(this)
}

fun File.encodeFileToBase64(ext: String): String {
    val size = this.length()
    val bytes = ByteArray(size.toInt())
    try {
        val buf = BufferedInputStream(FileInputStream(this))
        buf.read(bytes, 0, bytes.size)
        buf.close()
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
// ext+";"+Base64.encodeToString(bytes, Base64.NO_WRAP)
    return Base64.encodeToString(bytes, Base64.NO_WRAP)
}

fun String.calculateDiscount(totalPrice: Double, discount: Float?): String {
    var taxString = " "
    val cost = totalPrice.times(discount!!)
    taxString = "${cost.div(100)}"
    return taxString
}

fun String.calculateDiscount(totalPrice: Float, discount: Float?): String {
    var taxString = " "
    val cost = totalPrice.times(discount!!)
    taxString = "${cost.div(100)}"
    return taxString
}

fun String.decodeFileToBase64Binary(context: Context): File? {
    val imgBytesData: ByteArray = Base64.decode(
        this,
        Base64.NO_WRAP
    )
    val file = File.createTempFile(
        "File" + Calendar.getInstance().timeInMillis,
        null,
        context?.cacheDir
    )
    val fileOutputStream: FileOutputStream = try {
        FileOutputStream(file)
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
        return null
    }

    val bufferedOutputStream = BufferedOutputStream(
        fileOutputStream
    )
    try {
        bufferedOutputStream.write(imgBytesData)
        return file

    } catch (e: IOException) {
        e.printStackTrace()
        return null
    } finally {
        try {
            bufferedOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

fun ImageView.setImageDrawableWithAnimation(drawable: Drawable, duration: Int = 300) {
    val currentDrawable = getDrawable()
    if (currentDrawable == null) {
        setImageDrawable(drawable)
        return
    }

    val transitionDrawable = TransitionDrawable(
        arrayOf(
            currentDrawable,
            drawable
        )
    )
    setImageDrawable(transitionDrawable)
    transitionDrawable.startTransition(duration)
}

fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
}

fun NavController.safeNavigate(
    @IdRes currentDestinationId: Int,
    @IdRes id: Int,
    args: Bundle? = null,
    ops: NavOptions? = null
) {
    if (currentDestinationId == currentDestination?.id) {
        navigate(id, args, ops)
    }
}

fun NavController.safeNavigatePopUp(
    @IdRes currentDestinationId: Int,
    args: Bundle? = null,
    ops: NavOptions? = null
) {
    if (currentDestinationId == currentDestination?.id) {
        navigateUp()
    }
}


fun View.setMargins(
    leftMarginDp: Int? = null,
    topMarginDp: Int? = null,
    rightMarginDp: Int? = null,
    bottomMarginDp: Int? = null
) {
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        leftMarginDp?.run { params.leftMargin = this.dpToPx(context) }
        topMarginDp?.run { params.topMargin = this.dpToPx(context) }
        rightMarginDp?.run { params.rightMargin = this.dpToPx(context) }
        bottomMarginDp?.run { params.bottomMargin = this.dpToPx(context) }
        requestLayout()
    }
}

fun Int.dpToPx(context: Context): Int {
    val metrics = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics).toInt()
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun EditText.onDone(callback: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            callback.invoke()
            true
        }
        false
    }
}

fun EditText.onDoneClick(callback: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            callback.invoke()
            true
        }
        false
    }
}

fun Activity.dialPhoneNumber(phoneNumber: String) {
    if (phoneNumber == null) return
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:$phoneNumber")
    if (intent.resolveActivity(this.packageManager) != null) {
        this.startActivity(intent)
    } else showError("الرجاء تحميل التطبيق لامكانية الوصول لهذا الحساب")
}

fun Activity.share(string: String) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, application.getString(R.string.app_name))
    val shareMessage = string
    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
    this.startActivity(Intent.createChooser(shareIntent, "choose one"))
}

fun FragmentActivity.share(string: String, orderId: Int) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, application.getString(R.string.app_name))
    val shareMessage = string
    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
    this.startActivity(Intent.createChooser(shareIntent, "موقع الطلب رقم: #$orderId "))
}

fun FragmentActivity.share() {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, application.getString(R.string.app_name))
    val shareMessage = ""
    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
    this.startActivity(Intent.createChooser(shareIntent, "choose one"))
}

fun Context.share() {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "")
    val shareMessage = ""
    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
    this.startActivity(Intent.createChooser(shareIntent, "choose one"))
}
//
//fun FragmentActivity.shareApplication() {
//    val shareIntent = Intent(Intent.ACTION_SEND)
//    shareIntent.type = "text/plain"
//    shareIntent.putExtra(Intent.EXTRA_SUBJECT, application.getString(R.string.app_name))
//    val shareMessage = "تطبيق  العود للعطور العربية الشرقية, حمله الآن !" +
//            "\n ${LocalData.configs?.android}"
//    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
//    this.startActivity(Intent.createChooser(shareIntent, "choose one"))
//}

fun FragmentActivity.dialPhoneNumber(phoneNumber: String) {
    if (phoneNumber == null) return
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:$phoneNumber")
    if (intent.resolveActivity(this.packageManager) != null) {
        this.startActivity(intent)
    }// else Log.e("qmrNoApp", "الرجاء تحميل التطبيق لامكانية الوصول لهذا الحساب")
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    var edit = this
    var isOnTextChanged = false

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            if (isOnTextChanged) {
                isOnTextChanged = false
                if (editable.toString() != "") {
                    {
                        afterTextChanged(editable.toString())
                    }.withDelay(1500)
                } else {
                    afterTextChanged.invoke(editable.toString())
                }
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            isOnTextChanged = true

//            if (s.toString() == "")
//                afterTextChanged.invoke(s.toString())
//            Log.e("qmrText",s.toString())

        }
    })
}

fun Context.getNavigationBarSize(): Point? {
    val appUsableSize = getAppUsableScreenSize(this)
    val realScreenSize = getRealScreenSize(this)

    // navigation bar on the side
    if (appUsableSize.x < realScreenSize.x) {
        return Point(realScreenSize.x - appUsableSize.x, appUsableSize.y)
    }

    // navigation bar at the bottom
    return if (appUsableSize.y < realScreenSize.y) {
        Point(appUsableSize.x, realScreenSize.y - appUsableSize.y)
    } else Point()

    // navigation bar is not present
}

fun getAppUsableScreenSize(context: Context): Point {
    val windowManager: WindowManager =
        context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display: Display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size
}

fun getRealScreenSize(context: Context): Point {
    val windowManager: WindowManager =
        context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display: Display = windowManager.defaultDisplay
    val size = Point()
    if (Build.VERSION.SDK_INT >= 17) {
        display.getRealSize(size)
    } else if (Build.VERSION.SDK_INT >= 14) {
        try {
            size.x = (Display::class.java.getMethod("getRawWidth").invoke(display) as Int)
            size.y = (Display::class.java.getMethod("getRawHeight").invoke(display) as Int)
        } catch (e: IllegalAccessException) {
        } catch (e: InvocationTargetException) {
        } catch (e: NoSuchMethodException) {
        }
    }
    return size
}

fun View.getLocationOnScreen(): Point {
    val location = IntArray(2)
    this.getLocationOnScreen(location)
    return Point(location[0], location[1])
}


fun <T> Activity.navigateCleaerSatch(fromActivity: Context, toActivity: Class<T>) {
    this.startActivity(
        Intent(fromActivity, toActivity)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    )
    this.finish()
}

inline fun <reified T> parseArray(json: String, typeToken: Type): T {
    val gson = GsonBuilder().create()
    return gson.fromJson<T>(json, typeToken)
}

fun <T> Activity.navigateWithBack(fromActivity: Context, toActivity: Class<T>) {
    this.startActivity(Intent(fromActivity, toActivity))
}

fun <T> List<Any>.getItems(): List<T> {

    val objs: Type =
        object : TypeToken<List<T?>?>() {}.type
    return Gson().fromJson(this.toJson(), objs)

}

fun <T> Activity.navigateWithBack(fromActivity: Context, toActivity: Class<T>, value: Int) {
    this.startActivity(Intent(fromActivity, toActivity).putExtra("fromCart", value))
}

fun <T> Activity.navigateWithBack(fromActivity: Context, toActivity: Class<T>, fromCart: Boolean) {
    this.startActivity(Intent(fromActivity, toActivity).putExtra("fromCart", fromCart))
}
fun RecyclerView.disableItemAnimator() {
    (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
}

fun Activity.getScreenWidth(): Int {
    val displayMetrics = DisplayMetrics()
    this.windowManager.defaultDisplay.getMetrics(displayMetrics)
    val height: Int = displayMetrics.heightPixels
    val width: Int = displayMetrics.widthPixels
    return width
}

fun ViewPager.setupAutomaticScroll(lifeCycle: Lifecycle, delayMillis: Long = 5000) {
    lifeCycle.coroutineScope.launch {
        repeat(Int.MAX_VALUE) {
            delay(delayMillis)
            val next = when (currentItem) {
                0 -> 1
                1 -> 2
                2 -> 0
                else -> 0
            }
            setCurrentItem(next, true)
        }
    }
}

fun Context.showMessage(message: String) {
    StyleableToast.Builder(this)
        .text(message)
        .textColor(Color.WHITE)
        .backgroundColor(this.findColor(R.color.gray))
        .cornerRadius(5.toPx)
        .textSize(11f)
        .font(R.font.light)
        .gravity(Gravity.BOTTOM)
        .iconStart(R.drawable.ic_warning)
        .length(Toast.LENGTH_LONG)
        .solidBackground()
        .stroke(1.toPx, this.findColor(R.color.gray))
        .show()
}


fun Context.showError(message: String) {
    StyleableToast.Builder(this)
        .text(message)
        .textColor(Color.WHITE)
        .backgroundColor(this.findColor(R.color.red))
        .cornerRadius(5.toPx)
        .textSize(11f)
        .font(R.font.light)
        .gravity(Gravity.BOTTOM)
        .iconStart(R.drawable.ic_error)
        .length(Toast.LENGTH_LONG)
        .solidBackground()
        .stroke(1.toPx, this.findColor(R.color.red))
        .show()
}

fun Window.transparentStatus() {
    this.decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    this.statusBarColor = Color.parseColor("#1A000000")
}

fun Context.findColor(color: Int): Int {
    return ContextCompat.getColor(this, color)
}

@RequiresApi(Build.VERSION_CODES.O)
fun Context.findFont(font: Int): Typeface {
    return this.resources.getFont(font)
}

fun Context.isNightModeEnabled(): Boolean {
    var isEnabled = false
    val mode = resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)
    when (mode) {
        Configuration.UI_MODE_NIGHT_YES -> {
            isEnabled = true
        }
        Configuration.UI_MODE_NIGHT_NO -> {
            isEnabled = false
        }
        Configuration.UI_MODE_NIGHT_UNDEFINED -> {
            isEnabled = false
        }
    }
    return isEnabled
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.visibleAnimate() {
    visibility = View.VISIBLE
    val animate = TranslateAnimation(
        0f,  // fromXDelta
        0f,  // toXDelta
        height.toFloat(),  // fromYDelta
        0f
    ) // toYDelta

    animate.duration = 500
    animate.fillAfter = true
    startAnimation(animate)
}

fun View.goneAnimate() {
    visibility = View.GONE
    val animate = TranslateAnimation(
        0f,  // fromXDelta
        0f,  // toXDelta
        0f,  // fromYDelta
        height.toFloat()
    ) // toYDelta

    animate.duration = 500
    animate.fillAfter = true
    startAnimation(animate)
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

val Int.toPx: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.toDp(context: Context): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics
).toInt()

fun ImageView.setPhoto(url: String, context: Context, res: Int =R.mipmap.ic_launcher) {
    val subUrl = url.substringAfter(".png")
    var newUrl = url.dropLast(subUrl.length)
    if (newUrl.startsWith("https://").not() && newUrl.startsWith("http://").not()) {
        newUrl = RetrofitClient.BASE_IMAGE_URL + url
    }
    Glide.with(context)
        .load(newUrl)
        .placeholder(res)
        .error(res)
        .into(this)
}
fun ImageView.setPhotoBanners(url: String, context: Context , res: Int = R.mipmap.ic_launcher) {
    val subUrl = url.substringAfter(".png")
    var newUrl = url.dropLast(subUrl.length)
    if (newUrl.startsWith("https://").not() && newUrl.startsWith("http://").not()) {
        newUrl = RetrofitClient.BASE_IMAGE_URL + url
    }
    Glide.with(context)
        .load(newUrl)
        .placeholder(res)
        .into(this)
}

fun ImageView.setPhoto(url: Bitmap, context: Context, res: Int = R.mipmap.ic_launcher) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun ImageView.setPhoto(url: File, context: Context, res: Int =R.mipmap.ic_launcher) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun PhotoView.setPhoto(url: String, context: Context, res: Int =R.mipmap.ic_launcher) {
    Glide.with(context)
        .load(url)
        .error(res)
        .into(this)
}

fun PowerSpinnerView.responsiveDropDown() {
    if (Lingver.getInstance().getLanguage() == "ar")
        this.arrowGravity = SpinnerGravity.START
    else
        this.arrowGravity = SpinnerGravity.END
    this.setOnSpinnerOutsideTouchListener { view, motionEvent ->
        this.dismiss()
    }
}

fun PowerSpinnerView.responsiveDropDown2() {
    if (Lingver.getInstance().getLanguage() == "ar")
        this.arrowGravity = SpinnerGravity.END
    else
        this.arrowGravity = SpinnerGravity.START
    this.setOnSpinnerOutsideTouchListener { view, motionEvent ->
        this.dismiss()
    }
}

fun ImageView.setPhotoStorage(url: String, context: Context, res: Int) {
    Glide.with(context)
        .load(File(url))
        .error(res)
        .into(this)
}

fun <T> (() -> T).withDelay(delay: Long = 250L) {
    Handler(Looper.getMainLooper()).postDelayed({ this.invoke() }, delay)
}

fun CheckBox.bindWithEt(et: EditText) {
    val typeface = et.typeface
    this.setOnCheckedChangeListener { _, isChecked ->
        if (isChecked) {
            et.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            et.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        et.typeface = typeface

    }
}

fun MaterialButton.setBackgroundTint(color: Int) {
    this.backgroundTintList = ColorStateList.valueOf(color)
}

/*
fun <T> Spinner.initWithHint(
    hintId: Int,
    list: List<T> = listOf<T>("" as T),
    lambda: (Int, T) -> Unit
) {
    val hintSpinner: HintSpinner<T> by lazy {
        val hintAdapter = HintAdapter<T>(
            context,
            context.getString(hintId),
            list
        )
        HintSpinner<T>(
            this,
            hintAdapter
        ) { position, itemAtPosition ->
            lambda(position, itemAtPosition)
        }

    }
    hintSpinner.init()
    hintSpinner.selectHint()

}*/
/*fun RecyclerView.bindPaginationWithList(lambda: () -> Unit): NoPaginate {
    val noPaginate = NoPaginate.with(this)
        .setOnLoadMoreListener {
            lambda()
        }.setLoadingTriggerThreshold(0) //0 by default
        .setCustomLoadingItem(CustomLoadingItem())
        .build()
    return noPaginate

}*/
/*
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun Window.transparentStatus() {
    this.decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    this.statusBarColor = Color.TRANSPARENT
}*/

fun TextView.unSelectText() {
    this.setTextColor(Color.parseColor("#D8D8D8"))
}

@Suppress("DEPRECATION")
fun Activity.setLightStatusBar(flag: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val controller = this.window.insetsController
        if (flag) {
            controller?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else
            controller?.setSystemBarsAppearance(
                0,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (flag) {
            var flags: Int =
                window.decorView.systemUiVisibility // get current flag
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // add LIGHT_STATUS_BAR to flag
            window.decorView.systemUiVisibility = flags
        } else {

            var flags: Int =
                window.decorView.systemUiVisibility // get current flag
            if (flags == View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) {
                flags =
                    flags xor View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // use XOR here for remove LIGHT_STATUS_BAR from flags
                window.decorView.systemUiVisibility = flags
            }

        }
    }
}

fun TextView.strikethrough() {
    this.paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}

fun Window.setNoLimit() {
    this.setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )


}

fun Window.clearNoLimit() {
    this.clearFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )


}

fun String.arabicDay(): String {
    return when (this) {
        "saturday" -> "السبت"
        "sunday" -> "الأحد"
        "monday" -> "الأثنين"
        "tuesday" -> "الثلاثاء"
        "wednesday" -> "الأربعاء"
        "thursday" -> "الخميس"
        "friday" -> "الجمعة"
        else -> "كل ايام الاسبوع"
    }

}


fun TabLayout.changeSelectedTabItemFontFamily(tabPosition: Int, @FontRes fontFamilyRes: Int) {
    val linearLayout = (this.getChildAt(0) as ViewGroup).getChildAt(tabPosition) as LinearLayout
    val tabTextView = linearLayout.getChildAt(1) as TextView
    context?.let {
        val typeface = ResourcesCompat.getFont(it, fontFamilyRes)
        tabTextView.typeface = typeface
    }
}


fun <T> Activity.navigate(fromActivity: Context, toActivity: Class<T>) {
    this.startActivity(Intent(fromActivity, toActivity))
    this.finish()
}

fun <T> Activity.navigateWithParams(
    fromActivity: Context,
    toActivity: Class<T>,
    key: String,
    bundle: Bundle
) {
    this.startActivity(
        Intent(fromActivity, toActivity)
            .putExtra(key, bundle)
    )

}

fun String.preventArabicNumber(): String {
    val input = this
    if (input.isEmpty()) {
        return input
    }
    val builder = StringBuilder()
    for (element in input) {
        val ch = element
        if (Character.isDigit(ch) && !(ch in '0'..'9')) {
            val numericValue = Character.getNumericValue(ch)
            if (numericValue >= 0) {
                builder.append(numericValue)
            }
        } else {
            builder.append(ch)
        }
    }
    return builder.toString()
}
