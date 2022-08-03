package samtech.org.bussinesssample.Utils

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import android.widget.TextView

object TextUtils {
    @SuppressLint("ObsoleteSdkInt")
    fun setHtml(tView: TextView, paramContent: String?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tView.setText(Html.fromHtml(paramContent, Html.FROM_HTML_MODE_COMPACT))
        } else {
            tView.setText(Html.fromHtml(paramContent));
        }
    }
}