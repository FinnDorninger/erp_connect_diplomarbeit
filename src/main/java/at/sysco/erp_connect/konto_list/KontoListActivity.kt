package at.sysco.erp_connect.konto_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.sysco.erp_connect.adapter.KontoAdapter
import at.sysco.erp_connect.pojo.Konto
import at.sysco.erp_connect.model.KontoListModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_konto_list.*


class KontoListActivity : AppCompatActivity(),
    KontoListContract.View {
    var kontoListPresenter = KontoListPresenter(this, KontoListModel(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(at.sysco.erp_connect.R.layout.activity_konto_list)

        kontoListPresenter = KontoListPresenter(this, KontoListModel(this))
        kontoListPresenter.requestFromWS()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun displayKontoListInRecyclerView(kontoArrayList: List<Konto>) {
        rv_konto_list.layoutManager = LinearLayoutManager(this)
        rv_konto_list.addItemDecoration(DividerItemDecoration(rv_konto_list.context, 1))
        try_again.visibility = View.GONE
        rv_konto_list.adapter = KontoAdapter(kontoArrayList, this)
    }

    override fun showLoadingError() {
        try_again.visibility = View.VISIBLE
        try_again.setOnClickListener { kontoListPresenter.requestFromWS() }
    }
}
