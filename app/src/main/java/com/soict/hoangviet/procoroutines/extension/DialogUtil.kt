package com.soict.hoangviet.procoroutines.extension

import android.app.Activity
import androidx.fragment.app.DialogFragment
import com.soict.hoangviet.procoroutines.custom.dialogfragment.BaseDialogFragment
import com.soict.hoangviet.procoroutines.extension.hideFragmentByTag
import com.soict.hoangviet.procoroutines.base.view.alert.BaseAlertDialog
import com.soict.hoangviet.procoroutines.custom.view.NetworkDialog
import dagger.android.support.DaggerFragment
import java.util.HashMap

fun Activity.showNetworkDialog(func: NetworkDialog.() -> Unit) =
    NetworkDialog(this).apply { func() }.create().show()

fun Activity.showLocationAlertDialog(func: BaseAlertDialog.() -> Unit) =
    BaseAlertDialog(this).apply { func() }.create().show()

fun <T : BaseDialogFragment> DaggerFragment.showDialogFragment(
    type: Class<T>,
    data: Map<String, Any>? = null,
    func: T.() -> Unit
): T {
    var dialog = type.newInstance().apply { func() }
    data?.let {
        dialog.setData(it as HashMap<String, Any>)
    }
    hideFragmentByTag(type.simpleName)
    dialog.show(childFragmentManager, type.simpleName)
    return dialog
}

fun DialogFragment.isShowing() = (dialog?.isShowing == true)