package com.example.thetatechnolabassignment.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thetatechnolabassignment.R
import com.example.thetatechnolabassignment.home.adapter.UserAdapter
import com.example.thetatechnolabassignment.home.model.HomeActivity
import com.example.thetatechnolabassignment.home.viewModel.HomeViewModel
import com.example.thetatechnolabassignment.util.Resource
import com.example.thetatechnotest.home.model.Data
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var userAdapter: UserAdapter
    private var dataList: MutableList<Data>? = null
    private var isLoading = false
    private var isLastPage = false
    private var isScrolling = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBegining = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= viewModel.PAGE_SIZE
            val shoultPaginate =
                isNotLoadingNotLastPage && isAtLastItem && isNotAtBegining && isTotalMoreThanVisible && isScrolling
            if (shoultPaginate) {
                viewModel.getAllUsers()
                isScrolling = false
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel = (activity as HomeActivity).viewModel
        viewModel.userData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data.let {
                        swipeToRefresh.isRefreshing=false
                        hideProgressBar()
                        dataList = it?.data as MutableList<Data>
                        userAdapter.differ.submitList(it.data.toList())
                        val totalPages = it.total_pages / viewModel.PAGE_SIZE + 2
                        isLastPage = viewModel.pageNumber == totalPages
                    }
                }
                is Resource.Loading -> {

                    showProgressBar()

                }
                is Resource.Error -> {
                    swipeToRefresh.isRefreshing=false
                    hideProgressBar()
                }
            }

        })
        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val data = dataList?.get(position)
                dataList?.remove(data)

                userAdapter.differ.submitList(dataList)


            }
        }
        ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(rvUser)
        swipeToRefresh.setOnRefreshListener {
            viewModel.getAllUsers()
        }

    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }


    private fun initRecyclerView() {
        userAdapter = UserAdapter()
        rvUser.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = userAdapter
            addOnScrollListener(this@HomeFragment.scrollListener)


        }
    }


}