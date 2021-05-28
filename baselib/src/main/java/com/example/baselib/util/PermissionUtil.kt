package com.example.baselib.util

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class PermissionUtil private constructor() {


    companion object {

        private object SignHolder {
            val INSTANCE = PermissionUtil()
        }

        fun get(): PermissionUtil {
            return SignHolder.INSTANCE
        }

    }

    /**
     * activity注册单个permission加载器，必须在onCreate方法调用
     * 返回一个ActivityResultLauncher对象，只是注册监听
     */
    fun registerPermission(
        activity: ComponentActivity,
        block: (MutableMap<String, Boolean>) -> Unit
    ): ActivityResultLauncher<Array<String>> {
        return registerMorePermission(activity, block)
    }

    /**
     * fragment注册permission加载器，必须在onViewCreated方法调用
     */
    fun registerPermission(
        fragment: Fragment,
        block: (MutableMap<String, Boolean>) -> Unit
    ): ActivityResultLauncher<Array<String>> {
        return registerMorePermission(fragment, block)
    }

    /**
     * 开始请求单个权限
     */
    fun launchPermission(launcher: ActivityResultLauncher<Array<String>>, permission: String) {
        launchMorePermission(launcher, arrayOf(permission))
    }

    /**
     * activity注册多个permission加载器，必须在onCreate方法调用
     */
    fun registerMorePermission(
        activity: ComponentActivity,
        block: (MutableMap<String, Boolean>) -> Unit
    ): ActivityResultLauncher<Array<String>> {
        return activity.requestMorePermission {
            block(it)
        }
    }

    /**
     * fragment注册多个permission加载器，必须在onViewCreated方法调用
     */
    fun registerMorePermission(
        fragment: Fragment,
        block: (MutableMap<String, Boolean>) -> Unit
    ): ActivityResultLauncher<Array<String>> {
        return fragment.requestMorePermission {
            block(it)
        }
    }

    /**
     * 开始请求多个权限
     */
    fun launchMorePermission(
        launcher: ActivityResultLauncher<Array<String>>,
        permissions: Array<String>
    ) {
        launcher.launch(permissions)
    }
}


private inline fun ComponentActivity.requestPermission(crossinline block: (Boolean) -> Unit): ActivityResultLauncher<String> {
    return registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        block(it)
    }

}


private inline fun ComponentActivity.requestMorePermission(
    crossinline block: (MutableMap<String, Boolean>) -> Unit
): ActivityResultLauncher<Array<String>> {
    return registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        block(it)
    }

}


private inline fun Fragment.requestPermission(
    crossinline block: (Boolean) -> Unit
): ActivityResultLauncher<String> {
    return registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        block(it)
    }

}

private inline fun Fragment.requestMorePermission(
    crossinline block: (MutableMap<String, Boolean>) -> Unit
): ActivityResultLauncher<Array<String>> {
    return registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        block(it)
    }
}