package com.construction.app.attendance.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.construction.app.R
import com.construction.app.attendance.model.Attendance
import com.construction.app.attendance.viewmodel.ViewModelAttendance
import com.construction.app.base.constant.toObjects
import com.construction.app.base.repository.Resource
import com.construction.app.base.utility.*
import com.construction.app.base.view.BaseFragment
import com.construction.app.databinding.FragmentAttendanceAddBinding
import com.construction.app.labour.model.Labour
import com.construction.app.labour.viewmodel.ViewModelLabour
import com.construction.app.site.model.Site
import com.construction.app.site.viewmodel.ViewModelSite
import kotlinx.android.synthetic.main.fragment_attendance_add.*

class FragmentAttendanceAdd : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_attendance_add
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Add Attendance"

    private lateinit var binding: FragmentAttendanceAddBinding
    private lateinit var viewModelAttendance: ViewModelAttendance
    private lateinit var viewModelLabour: ViewModelLabour
    private lateinit var viewModelSite: ViewModelSite

    private lateinit var attendance: Attendance

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        this.binding = FragmentAttendanceAddBinding.inflate(layoutInflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initListener()
        getLabour()
        getSite()

        setTitle(R.string.titleAttendanceAdd)
        homeBackButton()
        homeOptionMenu()
    }

    private fun init() {
        this.viewModelAttendance = ViewModelProvider(this, factory).get(ViewModelAttendance::class.java)
        this.viewModelLabour = ViewModelProvider(this, factory).get(ViewModelLabour::class.java)
        this.viewModelSite = ViewModelProvider(this, factory).get(ViewModelSite::class.java)

        this.attendance = Attendance()
        this.binding.attendance = this.attendance
    }

    private fun initListener() {
        edDate.setOnClickListener {
            hideKeyboard()
            datePickerDialog { date ->
                this.attendance.date = date
            }
        }

        labour.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                attendance.labour = parent.getItemAtPosition(position) as Labour
            }
        site.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                attendance.site = parent.getItemAtPosition(position) as Site
            }

        rgDayType.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbFullDay -> {
                    this.attendance.dayType = getString(R.string.strFullDay)
                }
                R.id.rbHalfDay -> {
                    this.attendance.dayType = getString(R.string.strHalfDay)
                }
            }
            this.binding.attendance = this.attendance
        }

        buttonAdd.setOnClickListener {
            if (validate()) {
                hideKeyboard()
                addDocument()
            } else showErrorInput("Enter all details!!")
        }
    }

    private fun getLabour() {
        this.viewModelLabour.collection()
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        hideProgressBar()
                        bindLabour(
                            collection = result.type?.collection?.toObjects() ?: emptyList()
                        )
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        showErrorLoad()
                    }
                }
            })
    }

    private fun bindLabour(collection: List<Labour>) {
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu,
            collection
        )
        this.binding.labour.setAdapter(adapter)
    }

    private fun getSite() {
        this.viewModelSite.collectionListener()
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        hideProgressBar()
                        bindSite(
                            collection = result.type?.collection?.toObjects() ?: emptyList()
                        )
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        showErrorLoad()
                    }
                }
            })
    }

    private fun bindSite(collection: List<Site>) {
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_menu,
            collection
        )
        this.binding.site.setAdapter(adapter)
    }

    private fun validate(): Boolean {
        return (this.attendance.date.isNotBlank()
                && this.attendance.labour != null
                && this.attendance.site != null)
    }

    private fun addDocument() {
        buttonAdd.isEnabled = false
        this.viewModelAttendance.set(data = this.attendance)
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        clear()
                        hideProgressBar()
                        showSuccessAdd()
                        onBackPress()
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        showErrorAdd()
                    }
                }
            })
    }

    private fun clear() {
        this.attendance = Attendance()
        this.binding.attendance = this.attendance
    }
}
