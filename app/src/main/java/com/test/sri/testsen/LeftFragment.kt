package com.test.sri.testsen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by Myworld on 24/03/2018.
 */
class LeftFragment : Fragment() {

    private var compositedisposable: CompositeDisposable? = CompositeDisposable()
    private var data_list: List<Data>? = null
    internal var view: View? = null
    private var rvAdapter: RecyclerView.Adapter<*>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Fragment is not destroyed if retainInstance is true on orientation change
        retainInstance = true

        //this condition will check if we have json data if yes data will not be redownloaded.
        if (data_list==null){
            view=inflater?.inflate(R.layout.left_fragment,container,false)
            val recyclerView= view!!.findViewById<RecyclerView>(R.id.left_fragment_RV)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
            getData(recyclerView)

        }
        else{
            view=inflater?.inflate(R.layout.left_fragment,container,false)
            val recyclerView= view!!.findViewById<RecyclerView>(R.id.left_fragment_RV)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
            rvAdapter= LeftFragmentAdapter(data_list!!,activity)
            recyclerView.adapter=rvAdapter
        }
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    //This function  is used to call the url and RX is used to observe
    private fun getData(recyclerView:RecyclerView) {


        val retrofitService = RetrofitApiService.create()
        compositedisposable!!.add(retrofitService.loadfeed()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ RealEstateModel -> handleResponse(RealEstateModel, recyclerView) },this::handleError));

    }

    private fun handleResponse(response: RealEstateModel, recyclerView: RecyclerView) {
        data_list= response.data
        rvAdapter = LeftFragmentAdapter(data_list!!,activity)

        if (recyclerView != null) {
            recyclerView.adapter=rvAdapter
        }
    }
    private fun handleError(error: Throwable) {

    }

    override fun onDestroy() {
        super.onDestroy()

        // Compositedisposable is cleared once the fragment is destroyed
        compositedisposable!!.clear()
    }


}