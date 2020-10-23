package com.construction.app.attendance.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.construction.app.R
import com.construction.app.attendance.adapter.AdapterAttendance
import com.construction.app.attendance.model.Attendance
import com.construction.app.attendance.viewmodel.ViewModelAttendance
import com.construction.app.base.constant.BaseModule
import com.construction.app.base.constant.toObjects
import com.construction.app.base.repository.Resource
import com.construction.app.base.utility.*
import com.construction.app.base.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_attendance.*
import javax.inject.Inject

class FragmentAttendance internal constructor(
    private val isReport: Boolean = false,
    private val document: BaseModule
) : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_attendance
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Attendance"

    private lateinit var viewModel: ViewModelAttendance

    @Inject
    lateinit var adapter: AdapterAttendance

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initRecyclerView()
        initNavigation()
        initObserver()

        setTitle(if (isReport) R.string.strDetails else R.string.titleAttendance)
        homeBackButton()
        homeOptionMenu()
    }

    private fun init() {
        buttonAdd.visibility = if (isReport) View.GONE else View.VISIBLE
        this.viewModel = ViewModelProvider(this, factory).get(ViewModelAttendance::class.java)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = this.adapter
    }

    private fun initNavigation() {
        buttonAdd.setOnClickListener {
            findNavController().navigateWithAnim(
                FragmentAttendanceDirections.toFragmentAttendanceAdd()
            )
        }
    }

    private fun initObserver() {
        this.viewModel.collection()
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        updateCollection(
                            collection = result.type?.collection?.toObjects() ?: emptyList()
                        )
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        showFullScreenError()
                    }
                }
            })
    }

    private fun updateCollection(collection: List<Attendance>) {
        this.adapter.setCollection(collection = collection)
    }
}
