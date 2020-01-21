package com.iamkurtgoz.example.facebookshimmeranimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var shimmerLayout: ShimmerFrameLayout
    private lateinit var loadingLayout: LinearLayout
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        shimmerLayout = findViewById(R.id.activity_main_shimmerLayout)
        loadingLayout = findViewById(R.id.activity_main_loadingLayout)
        recyclerView = findViewById(R.id.activity_main_recyclerView)

        start()
    }

    private fun start(){
        shimmerLayout.startShimmer()
        Handler().postDelayed({
            shimmerLayout.hideShimmer()
            loadingLayout.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            getList()
        }, 3500)
    }

    private fun getList(){
        val arrayList = ArrayList<ApplicationModel>()
        arrayList.add(ApplicationModel("https://image.flaticon.com/icons/png/128/733/733585.png", "Whatsapp", "Message App"))
        arrayList.add(ApplicationModel("https://image.flaticon.com/icons/png/128/733/733558.png", "Instagram", "Social Media"))
        arrayList.add(ApplicationModel("https://image.flaticon.com/icons/png/128/733/733579.png", "Twitter", "Social Media"))
        arrayList.add(ApplicationModel("https://image.flaticon.com/icons/png/128/733/733547.png", "Facebook", "Social Media"))
        arrayList.add(ApplicationModel("https://image.flaticon.com/icons/png/128/733/733590.png", "Youtube", "Video-Music Platform"))
        arrayList.add(ApplicationModel("https://image.flaticon.com/icons/png/128/733/733548.png", "Messenger", "Message App"))
        arrayList.add(ApplicationModel("https://image.flaticon.com/icons/png/128/733/733571.png", "Snapchat", "Social Media"))

        var adapter = CustomAdapter(arrayList);
        recyclerView.adapter = adapter;
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        adapter.notifyDataSetChanged();
    }

    private inner class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgHeader: ImageView = itemView.findViewById(R.id.list_item_movie_row_imgHeader)
        val textTitle: TextView = itemView.findViewById(R.id.list_item_movie_row_textTitle)
        val textDescription: TextView = itemView.findViewById(R.id.list_item_movie_row_textDescription)
    }

    private inner class CustomAdapter(val arrayList: ArrayList<ApplicationModel>): RecyclerView.Adapter<CustomViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            return CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie_row, parent, false))
        }

        override fun getItemCount(): Int {
            return arrayList.size
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            val model = arrayList[position]

            Picasso.get().load(model.imageUrl).into(holder.imgHeader);
            holder.textTitle.text = model.name
            holder.textDescription.text = model.description
        }

    }
}
