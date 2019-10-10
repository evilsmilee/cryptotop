package ru.nickb.cryptotop.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import kotlinx.android.synthetic.main.fragment_currencies_list.*
import ru.nickb.cryptotop.adapter.BaseAdapter


abstract class BaseListFragment : MvpAppCompatFragment() {

    private lateinit var recyclerView: RecyclerView
    protected lateinit var viewAdapter: BaseAdapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = LinearLayoutManager(context)
        viewAdapter = createAdapterInstance()

        recyclerView = list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    abstract fun createAdapterInstance(): BaseAdapter<*>
}