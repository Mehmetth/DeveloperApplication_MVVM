package com.honeycomb.developerapplication_mvvm.presentation.products

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.honeycomb.developerapplication_mvvm.R
import com.honeycomb.developerapplication_mvvm.data.product.remote.dto.ProductResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_rv_item.view.*

class ProductsAdapter(var context : Context,
                      var memesList: List<ProductResponse>,
                      val iProductItemClick: IProductsItemClick,):
    RecyclerView.Adapter<ProductsAdapter.DataViewHolder> () {
    class DataViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.product_rv_item,parent,false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return memesList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        Picasso.get().load(memesList[position].image).into(holder.view.product_image)
        holder.view.product_name.text = memesList[position].name
        holder.view.product_price.text = "${context.getString(R.string.price)} ${memesList[position].price}"

        holder.itemView.setOnClickListener {
            iProductItemClick.itemClicked( memesList[position])
        }
    }
}