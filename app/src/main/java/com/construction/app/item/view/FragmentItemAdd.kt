package com.construction.app.item.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.construction.app.R
import com.construction.app.base.constant.Constant.Companion.BLANK
import com.construction.app.base.constant.toObjects
import com.construction.app.base.repository.Resource
import com.construction.app.base.utility.*
import com.construction.app.base.view.BaseFragment
import com.construction.app.databinding.FragmentItemAddBinding
import com.construction.app.item.adapter.AdapterItem
import com.construction.app.item.model.Item
import com.construction.app.item.viewmodel.ViewModelItem
import kotlinx.android.synthetic.main.fragment_item_add.*
import java.util.*
import javax.inject.Inject

class FragmentItemAdd : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_item_add
    override fun className() = this.javaClass.simpleName
    override fun pageName() = "Add Item"

    private lateinit var viewModel: ViewModelItem
    private lateinit var binding: FragmentItemAddBinding

    @Inject
    lateinit var adapter: AdapterItem

    private lateinit var item: Item
    private lateinit var ui: UI
    private var isUpdate = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        this.binding = FragmentItemAddBinding.inflate(layoutInflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initRecyclerView()
        initObserver()
        initListener()

        setTitle(R.string.titleItemAdd)
        homeBackButton()
        homeOptionMenu()
    }

    private fun init() {
        this.viewModel = ViewModelProvider(this, factory).get(ViewModelItem::class.java)
        this.ui = UI()
        this.item = Item()
        this.binding.ui = this.ui
        this.binding.item = this.item
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = this.adapter
    }

    private fun initObserver() {
        this.viewModel.collectionListener()
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        updateCollection(
                            collection = result.type?.collection?.toObjects() ?: emptyList()
                        )
                        hideProgressBar()
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        showFullScreenError()
                    }
                }
            })
        this.viewModel.update.nonNull().observe(viewLifecycleOwner, Observer {
            ui.update = it
        })
    }

    private fun updateCollection(collection: List<Item>) {
        this.adapter.setCollection(collection = collection)
    }

    private fun initListener() {
        edItem.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                item.status = !s.isNullOrBlank()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        ivCancel.setOnClickListener { clear() }

        buttonAdd.setOnClickListener {
            hideKeyboard()
            if (validate()) {
                if (!isUpdate) {
                    this.item.id = BLANK
                    this.item.createdAt = Date()
                    this.item.createdBy = "Admin"
                    addDocument()
                } else updateDocument()
            } else showErrorInput("Enter item name!!")
        }
    }

    private fun validate(): Boolean {
        return this.item.name.isNotBlank()
    }

    private fun addDocument() {
        val user = getUser()
        user?.let {
            this.item.createdBy = it.displayName ?: ""
            this.item.createdById = it.uid
        }
        this.viewModel.set(data = this.item)
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        clear()
                        hideProgressBar()
                        showSuccessAdd()
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        showErrorAdd()
                    }
                }
            })
    }

    fun editDocument(item: Item) {
        this.ui.update = true
        this.isUpdate = true
        val it = item
        this.item = it
    }

    private fun updateDocument() {
        this.viewModel.update(
            documentId = this.item.id,
            key = getString(R.string.fieldItem),
            value = this.item.name
        ).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    clear()
                    buttonAdd.isEnabled = true
                    hideProgressBar()
                    showSuccessAdd()
                }
                is Resource.Error -> {
                    clear()
                    hideProgressBar()
                    showErrorAdd()
                }
            }
        })
    }

    private fun clear() {
        this.isUpdate = false
        this.ui.update = false
        this.item = Item()
        this.binding.item = item
    }
}
