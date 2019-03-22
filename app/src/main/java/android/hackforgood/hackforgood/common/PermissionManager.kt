package android.hackforgood.hackforgood.common

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity


/**
 * Created by justo on 21/03/2019.
 */
object PermissionsManager {
    private val PERMISSIONS = arrayOf<String>()

    fun getPermissions(context: Context) {
        if (!checkPermissions(context)) {
            ActivityCompat.requestPermissions(context as AppCompatActivity, PERMISSIONS, 100)
        }
    }

    fun checkPermissions(context: Context): Boolean {
        var isGiven = true
        for (permission in PERMISSIONS)
            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                isGiven = false

        return isGiven
    }

}