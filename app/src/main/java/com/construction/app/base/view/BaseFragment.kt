package com.construction.app.base.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.construction.app.R
import com.construction.app.base.constant.Converter
import com.construction.app.base.di.factory.ViewModelFactory
import com.construction.app.base.utility.hideKeyboard
import com.construction.app.databinding.FragmentBaseBinding
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {
    abstract fun layoutResourceId(): Int
    abstract fun className(): String
    abstract fun pageName(): String

    @Inject
    lateinit var factory: ViewModelFactory

    val TAG = className()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentBaseBinding.inflate(layoutInflater, container, false)
        val content = inflater.inflate(layoutResourceId(), container, false)
        binding.baseLayout.addView(content)
        return binding.root
    }

    fun onBackPress() {
        requireActivity().onBackPressed()
    }

    fun getUser() = (requireActivity() as BaseActivity).getUser()

    fun path() = (requireActivity() as BaseActivity)

    fun getAppName(): String = getString(R.string.app_name)

    fun hideKeyboard() = requireActivity().hideKeyboard()

    fun showProgressBar() {}

    fun hideProgressBar() {}

    fun showFullScreenError() {}

    fun datePickerDialog(onClick: (date: String) -> Unit = {}) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, cYear, cMonth, cDay ->
                onClick.invoke(Converter.toDMY(cDay, (cMonth + 1), cYear))
            }, year, month, day
        )
        dialog.show()
    }

    fun timePickerDialog(onClick: (time: String) -> Unit = {}) {
        val calendar = Calendar.getInstance()
        val mHour = calendar.get(Calendar.HOUR_OF_DAY)
        val mMinute = calendar.get(Calendar.MINUTE)
        val dialog = TimePickerDialog(
            requireContext(),
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                onClick.invoke(Converter.toHM(hourOfDay, minute))
            }, mHour, mMinute, true
        )
        dialog.show()
    }
}