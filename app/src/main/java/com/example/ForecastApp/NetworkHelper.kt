package com.example.ForecastApp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class NetworkHelper : Fragment(){


    companion object {
        val TAG = "NetworkHelper"
        val CHECK_INTERNET = "network_connection"

        fun isInternetConnected(context: Context): Boolean {
            val connec = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            val mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

            if (wifi != null && wifi.isConnected) {
                return true
            } else if (mobile != null && mobile.isConnected) {
                return true
            }
            return false
        }

        fun newInstance(): NetworkHelper {
            return NetworkHelper()
        }
    }

    private var mActivity: Context? = null
    private var mAlertDialog: AlertDialog? = null

    private val onNotice = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {

            if (intent.hasExtra(CHECK_INTERNET) && !intent.getBooleanExtra(CHECK_INTERNET, true)) {
                showAlertDialog(mActivity, "Internet Connection",
                        "No internet connection available.\n\n" + "Please check your internet connection and try again.")
            } else {
                if (mAlertDialog != null && mAlertDialog!!.isShowing) {
                    mAlertDialog!!.dismiss()
                    mAlertDialog = null
                }

            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onAttach(activity: Context) {
        super.onAttach(activity)
        mActivity = activity

    }

    override fun onResume() {
        super.onResume()
        val iff = IntentFilter(CHECK_INTERNET)
        LocalBroadcastManager.getInstance(mActivity!!).registerReceiver(onNotice, iff)
        if (!isInternetConnected(mActivity!!)) {
            showAlertDialog(mActivity, "Internet Connection",
                    "No internet connection available.\n\n" + "Please check your internet connection and try again.")
        }
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(mActivity!!).unregisterReceiver(onNotice)
    }

    override fun onDetach() {
        super.onDetach()
        mActivity = null
    }

    private fun showAlertDialog(context: Context?, title: String, message: String) {
        if (mAlertDialog != null && mAlertDialog!!.isShowing) {
            return  //already showing
        } else if (mAlertDialog != null) {
            mAlertDialog!!.dismiss()
            mAlertDialog = null
        }
        mAlertDialog = AlertDialog.Builder(context!!).create()

        // Setting Dialog Title
        mAlertDialog!!.setTitle(title)

        // Setting Dialog Message
        mAlertDialog!!.setMessage(message)

        // Setting alert dialog icon
        //mAlertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        // Setting OK Button
        mAlertDialog!!.setButton(DialogInterface.BUTTON_POSITIVE,
                getString(android.R.string.ok)
        ) { dialog, _ ->
            dialog.dismiss()
            mAlertDialog = null
        }

        // Showing Alert Message
        mAlertDialog!!.show()
    }

    fun isWifiConnected(context: Context): Boolean {
        val connec = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI)

        return wifi != null && wifi.isConnected
    }

    fun isMobileDataConnected(context: Context): Boolean {
        val connec = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

        return mobile != null && mobile.isConnected
    }
}






