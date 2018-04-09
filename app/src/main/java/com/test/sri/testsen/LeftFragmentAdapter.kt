package com.test.sri.testsen

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import android.widget.ProgressBar



/**
 * Created by Myworld on 24/03/2018.
 */
class LeftFragmentAdapter(private val data_list:List<Data>,private val context:Context) : RecyclerView.Adapter<LeftFragmentAdapter.ViewHolder>() {

    //Declaring Global variable the value of premium and standard will used to load relevant listing
    private val Premium = 1
    private val Standard = 2

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        //This statement checks and loads relevant view to recycler view
       if(viewType==Premium){
           val v=LayoutInflater.from(parent?.context).inflate(R.layout.premium_listing,parent,false)
           return ViewHolder(v)

       }else
       {
           val v=LayoutInflater.from(parent?.context).inflate(R.layout.standard_listing,parent,false)
           return ViewHolder(v)
       }
    }

    // This function will load data to appropriate listing
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        holder?.title_TV!!.text=data_list[position].title
        holder?.address_TV!!.text="${data_list[position].location.address_2} ${data_list[position].location.full_address}"
        holder?.agentname_TV!!.text="${data_list[position].owner.first_name} ${data_list[position].owner.last_name}"
        holder?.bedrooms_TV!!.text= "${data_list[position].bedrooms.toString()} "
        holder?.bathrooms_TV!!.text=" ${data_list[position].bathrooms.toString()} "
        holder?.carpark_TV!!.text=" ${data_list[position].carspots.toString()} "
        holder.progressBar!!.visibility = View.VISIBLE

        //picasso library is used to load image to imageview
        Picasso.with(context)
                .load(data_list[position].photo.image.small.url)
                .resize(450, 250)
                .into(holder.property_IV,object:Callback{
                    override fun onSuccess() {

                        // Progress bar is closed as soon as the image is loaded
                        holder.progressBar!!.visibility = View.GONE

                    }

                    override fun onError() {
                        //To change body of created functions use File | Settings | File Templates.
                    }
                })

        //picasso  library is used to load image to circular image view
        Picasso.with(context)
                .load(data_list[position].owner.avatar.small.url)
                .resize(100, 100)
                .into(holder.agent_IV)

        holder?.relativeLayout.setOnClickListener(View.OnClickListener {
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            //Get right Fragment object.
            val rightFragment = fragmentManager.findFragmentById(R.id.right_fragment)
            //Get the TextView object in right Fragment.
            val rightFragmentTextView = rightFragment.view!!.findViewById<TextView>(R.id.right_fragment_textview)
            Log.v("srikanth","I have been clicked")
            //Set text in right Fragment TextView.
            rightFragmentTextView.setText(" Id ="+data_list[holder.adapterPosition].id+"\n Name = "+data_list[holder.adapterPosition].owner.first_name+" "+data_list[holder.adapterPosition].owner.last_name)

        })


    }

    //this function returns the size of arraylist with json data
    override fun getItemCount(): Int {
        return data_list.size
    }

    //Function to find if is_premium is true or not!

    override fun getItemViewType(position: Int): Int {

        if (data_list.get(position).is_premium==true){
            return Premium
        }else{
            return Standard
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //declaring variable
        val relativeLayout =itemView.findViewById<RelativeLayout>(R.id.listing_layout)
        val progressBar= itemView!!.findViewById<ProgressBar>(R.id.progressBar)
        val title_TV = itemView.findViewById<TextView>(R.id.title_TV)
        val address_TV = itemView.findViewById<TextView>(R.id.address_tv)
        val agentname_TV = itemView.findViewById<TextView>(R.id.agent_name_TV)
        val bedrooms_TV = itemView.findViewById<TextView>(R.id.bedroom_TV)
        val  bathrooms_TV = itemView.findViewById<TextView>(R.id.bathroom_TV)
        val  carpark_TV = itemView.findViewById<TextView>(R.id.carpark_TV)
        val  property_IV = itemView.findViewById<ImageView>(R.id.property_image_view)
        val  agent_IV = itemView.findViewById<ImageView>(R.id.agent_IV)

    }
}