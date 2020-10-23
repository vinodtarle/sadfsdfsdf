package com.construction.app.base.utility

import android.graphics.Color
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.construction.app.R
import com.construction.app.base.view.ActivityMain
import com.google.android.material.snackbar.Snackbar

fun Fragment.string(id: Int) = getString(id)

fun Fragment.homeBackButton(visible: Boolean = true) {
    requireActivity().let {
        (it as ActivityMain).homeBackButton(visible = visible)
    }
}

fun Fragment.homeOptionMenu(visible: Boolean = false) {
    requireActivity().let {
        (it as ActivityMain).homeOptionMenu(visible = visible)
    }
}

fun Fragment.setTitle(title: String? = null) {
    requireActivity().title = title ?: name(id = R.string.app_name)
}

fun Fragment.setTitle(id: Int) {
    requireActivity().title = name(id = id)
}

fun Fragment.name(id: Int): String {
    return getString(id)
}

fun Fragment.findNavController(): NavController =
    NavHostFragment.findNavController(this)

fun Fragment.toNavigate(actionId: Int) {
    findNavController().navigateWithAnim(
        actionId = actionId
    )
}

fun Fragment.toNavigate(actionId: Int, document: Any) {
    val bundle = bundleOf("document" to document)
    findNavController().navigateWithAnim(
        actionId = actionId,
        args = bundle
    )
}

fun Fragment.showErrorInput(input: String) {
    val snackbar = Snackbar.make(requireView(), input, Snackbar.LENGTH_LONG)
    val view = snackbar.view
    val textView = view.findViewById<TextView>(R.id.snackbar_text)
    textView.setTextColor(Color.YELLOW)
    snackbar.show()
}

fun Fragment.showSuccessAdd() {
    val snackbar = Snackbar.make(requireView(), R.string.successAdd, Snackbar.LENGTH_LONG)
    val view = snackbar.view
    val textView = view.findViewById<TextView>(R.id.snackbar_text)
    textView.setTextColor(Color.WHITE)
    snackbar.show()
}

fun Fragment.showErrorAdd() {
    val snackbar = Snackbar.make(requireView(), R.string.errorAdd, Snackbar.LENGTH_LONG)
    val view = snackbar.view
    val textView = view.findViewById<TextView>(R.id.snackbar_text)
    textView.setTextColor(Color.WHITE)
    snackbar.show()
}

fun Fragment.showErrorLoad() {
    val snackbar = Snackbar.make(requireView(), R.string.errorLoad, Snackbar.LENGTH_LONG)
    val view = snackbar.view
    val textView = view.findViewById<TextView>(R.id.snackbar_text)
    textView.setTextColor(Color.WHITE)
    snackbar.show()
}
