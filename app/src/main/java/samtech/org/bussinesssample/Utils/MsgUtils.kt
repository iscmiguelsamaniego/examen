package samtech.org.bussinesssample.Utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.location.Location
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import org.osmdroid.views.MapView
import samtech.org.bussinesssample.R
import samtech.org.bussinesssample.Utils.Constants.ALERT
import samtech.org.bussinesssample.Utils.Constants.INFO
import samtech.org.bussinesssample.Utils.Constants.SUCESS
import samtech.org.bussinesssample.Utils.Constants.WARNING
import samtech.org.bussinesssample.Utils.MapUtils.setMap
import java.util.*

object MsgUtils {

    @SuppressLint("InflateParams")
    fun showCustomToast(paramActivity: Activity, srcMsg: Any, type: Int) {
        if (paramActivity != null) {
            val layout: View =
                paramActivity.layoutInflater.inflate(R.layout.custom_toast_warning, null)

            val text = layout.findViewById<TextView>(R.id.text)
            val image = layout.findViewById<ImageView>(R.id.imageview)

            var srcImgAux = -1
            var colorAux = -1

            when (type) {
                ALERT -> {
                    srcImgAux = R.drawable.ic_action_error
                    colorAux = R.color.redLigth
                }

                WARNING -> {
                    srcImgAux = R.drawable.ic_action_warning
                    colorAux = R.color.yellowLigth
                }

                SUCESS -> {
                    srcImgAux = R.drawable.ic_action_success
                    colorAux = R.color.greenLigth
                }

                INFO -> {
                    srcImgAux = R.drawable.ic_action_info
                    colorAux = R.color.blueLigth
                }
            }

            if (srcImgAux != -1) {
                image.setBackgroundResource(srcImgAux)
            }

            if (colorAux != -1) {
                layout.setBackgroundColor(ContextCompat.getColor(paramActivity, colorAux))
                text.setTextColor(ContextCompat.getColor(paramActivity, R.color.black))
            }

            if (srcMsg is Int) {
                text.setText(paramActivity.getString(srcMsg))
            } else if (srcMsg is String) {
                text.setText(srcMsg.toString())
            }

            val toast = Toast(paramActivity)
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            toast.setDuration(Toast.LENGTH_LONG)
            toast.setView(layout)
            toast.show()
        }
    }

    fun showOSMMapAlert(
        context: Context, paramLocation: Location?,
        title: String?
    ) {
        if (paramLocation != null) {
            val mapDialog = Dialog(context)
            mapDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            Objects.requireNonNull(mapDialog.window)
                ?.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
                )
            mapDialog.setContentView(R.layout.alert_map)
            val previewMap: MapView = mapDialog.findViewById(R.id.alert_map_preview_map)
            setMap(
                previewMap,
                true,
                true,
                17.5,
                paramLocation,
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_action_marker
                ),
                title
            )
            mapDialog.setCancelable(true)
            mapDialog.show()
        }
    }
}