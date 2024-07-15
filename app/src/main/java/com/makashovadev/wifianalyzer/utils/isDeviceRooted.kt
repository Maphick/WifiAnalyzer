package com.makashovadev.wifianalyzer.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import java.io.File

object RootUtils {
    fun hasRootAccess(context: Context): Boolean {
        // 1. Check for 'su' binary
        if (checkSuBinary()) {
            return true
        }

        // 2. Check for specific root files
        if (checkRootFiles()) {
            return true
        }

        // 3. Check for specific permissions (less reliable)
        if (checkRootPermissions(context)) {
            return true
        }

        return false // No evidence of root access
    }

    private fun checkSuBinary(): Boolean {
        try {
            val process = Runtime.getRuntime().exec("su")
            return process.waitFor() == 0
        } catch (e: Exception) {
            return false
        }
    }

    private fun checkRootFiles(): Boolean {
        val superuserApk = File("/system/app/Superuser.apk")
        val magiskFile = File("/data/local/tmp/magisk")
        return superuserApk.exists() || magiskFile.exists()
    }

    private fun checkRootPermissions(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_SUPERUSER") ==
                PackageManager.PERMISSION_GRANTED
    }
}

/*
import android.os.Build
import android.util.Log
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader


class RootUtil()
{
val TAG = "RootUtil"

fun isDeviceRooted(): Boolean {
    return checkRootMethod1() || checkRootMethod2() || checkRootMethod3()
}

    /*
private fun checkRootMethod1(): Boolean {
    val buildTags = Build.TAGS
    return buildTags != null && buildTags.contains("test-keys")
}
*/

fun checkRootMethod1(): Boolean {
    val superuserApk = File("/system/app/Supperuser.apk")
    val suBinary = File("/system/bin/su")

    return superuserApk.exists() || suBinary.exists()
}

private fun checkRootMethod2(): Boolean {
    val paths = arrayOf(
        "/system/app/Superuser.apk",
        "/sbin/su",
        "/system/bin/su",
        "/system/xbin/su",
        "/data/local/xbin/su",
        "/data/local/bin/su",
        "/system/sd/xbin/su",
        "/system/bin/failsafe/su",
        "/data/local/su",
        "/su/bin/su"
    )
    for (path in paths) {
        if (File(path).exists()) return true
    }
    return false
}

private fun checkRootMethod3(): Boolean {
    var process: Process? = null
    var isRooted = false
    try {
        process = Runtime.getRuntime().exec("su")
        isRooted = true
        //process = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
        val `in` = BufferedReader(InputStreamReader(process.inputStream))
        //return `in`.readLine() != null
    } catch (t: Throwable) {
        return false
    } finally {
        process?.destroy()
        return isRooted
    }

}

fun isDeviceRootedV2(): Boolean {
    try {
        Runtime.getRuntime().exec("su")
        return true
    } catch (e: IOException) {
        Log.e(TAG, "isDeviceRootedV2: " + e.message)
        return false
    }
}

    }
*/