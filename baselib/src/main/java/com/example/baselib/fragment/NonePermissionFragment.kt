package com.example.test.util

import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

class NonePermissionFragment(
    private val liveData: MutableLiveData<Map<String, Boolean>>
) : Fragment() {
    private var launcher: ActivityResultLauncher<Array<String>>? = null

    companion object {
        const val TAG = "NonePermissionFragment"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launcher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            liveData.postValue(it)
        }
    }


    fun requestPermission(array: Array<String>) {
        launcher?.launch(array)
    }
}