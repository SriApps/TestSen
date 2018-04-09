package com.test.sri.testsen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by Myworld on 24/03/2018.
 */

// Right Fragment to display ID of on clicked card view
class RightFragment: Fragment() {

    //Global variables
    internal var view: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Fragment is not destroyed if retainInstance is true
        retainInstance = true
        view=inflater?.inflate(R.layout.right_fragment,container,false)
        val resultTextView = view!!.findViewById<TextView>(R.id.right_fragment_textview)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}