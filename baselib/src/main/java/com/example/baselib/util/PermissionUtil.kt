package com.lihui.qmyn.util.permission

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.example.test.util.NonePermissionFragment
import java.util.concurrent.ConcurrentHashMap

class PermissionUtil private constructor() {
    private val fragmentMap = ConcurrentHashMap<String, NonePermissionFragment>()
    private val permissionLiveData = MutableLiveData<Map<String, Boolean>>()

    companion object {

        private object SignHolder {
            val INSTANCE = PermissionUtil()
        }

        fun get(): PermissionUtil {
            return SignHolder.INSTANCE
        }


    }

    fun with(
        activity: FragmentActivity, permissions: Array<String>,
        block: (Boolean) -> Unit
    ) {
        registerFragment(activity, permissions, block)
    }

    fun with(
        fragment: Fragment, permissions: Array<String>,
        block: (Boolean) -> Unit
    ) {
        if (fragment.lifecycle.currentState != Lifecycle.State.DESTROYED && fragment.activity != null) {
            registerFragment(fragment.requireActivity(), permissions, block)
        }

    }


    private fun registerFragment(
        activity: FragmentActivity,
        permissions: Array<String>,
        block: (Boolean) -> Unit
    ) {
        if (activity.lifecycle.currentState == Lifecycle.State.DESTROYED) {
            return
        }
        val manager = activity.supportFragmentManager
        var fragment = manager.findFragmentByTag(NonePermissionFragment.TAG)
        if (fragment == null) {
            fragment = fragmentMap[activity::class.java.simpleName]
            if (fragment == null) {
                fragment = NonePermissionFragment(permissionLiveData)
                fragmentMap[activity::class.java.simpleName] = fragment

                manager.beginTransaction().add(fragment, NonePermissionFragment.TAG)
                    .commitAllowingStateLoss()
                permissionLiveData.observe(activity, Observer { map ->
                    var result = true
                    map.forEach { item ->
                        if (!item.value) {
                            result = false
                        }
                    }
                    block(result)
                })
            }
        }
        observable(activity, permissions)
    }

    private fun observable(
        activity: FragmentActivity,
        permissions: Array<String>
    ) {

        val fragment = fragmentMap[activity::class.java.simpleName]
        if (activity.supportFragmentManager.findFragmentByTag(NonePermissionFragment.TAG) == null) {
            fragment?.lifecycle?.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
                fun onStart() {
                    fragment.requestPermission(permissions)
                }

                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onDestroy() {
                    fragmentMap.remove(activity::class.java.simpleName)
                }
            })
        } else {
            fragment?.requestPermission(permissions)
        }
    }


}


