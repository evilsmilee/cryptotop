package ru.nickb.cryptotop.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.nickb.cryptotop.R
import ru.nickb.cryptotop.adapter.BaseAdapter
import ru.nickb.cryptotop.adapter.CurrenciesAdapter
import ru.nickb.cryptotop.di.App
import ru.nickb.cryptotop.mvp.presenter.CurrenciesPresenter
import ru.nickb.cryptotop.mvp.view.CurrenciesView


class CurrenciesListFragment : BaseListFragment(), CurrenciesView {

    @InjectPresenter
    lateinit var presenter: CurrenciesPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currencies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.appComponent.inject(this)
        presenter.makeList()
    }

    override fun createAdapterInstance(): BaseAdapter<*> {
        return CurrenciesAdapter()
    }

    override fun addCurrency(currency: CurrenciesAdapter.Currency) {
        viewAdapter.add(currency)
    }

    override fun notifyAdapter() {
        viewAdapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        requireActivity().progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        requireActivity().progress.visibility = View.INVISIBLE
    }

    override fun showErrorMessage(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun refresh() {
        viewAdapter.items.clear()
        viewAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        /*presenter.attach(this)*/
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

}