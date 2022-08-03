package samtech.org.bussinesssample.Utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import samtech.org.bussinesssample.R
import samtech.org.bussinesssample.Utils.Constants.ALERT
import samtech.org.bussinesssample.Utils.Constants.INFO
import samtech.org.bussinesssample.Utils.Constants.SUCESS
import samtech.org.bussinesssample.Utils.Constants.WARNING

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

    fun toast(ctx: Context?, message: String?) {
        if (ctx != null) {
            val toast = Toast.makeText(ctx, message, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show()
        }
    }

    fun AppCompatActivity.showAlert(title: CharSequence, message: CharSequence) {
        val builder = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ok", { dialog, which ->
            })

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}