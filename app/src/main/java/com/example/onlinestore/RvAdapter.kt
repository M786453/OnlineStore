package com.example.onlinestore

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class RvAdapter(var context:Context,var arraylist :ArrayList<EProduct>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var productView = LayoutInflater.from(context).inflate(R.layout.recycler_view_row,parent,false)
        return ProductViewHolder(productView)
    }

    override fun getItemCount(): Int {
        return arraylist.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductViewHolder).initializeUiComponents(arraylist[position].pId,
            arraylist[position].pName,arraylist[position].pPrice,arraylist[position].pImage)
    }
    inner class ProductViewHolder(var myView:View):RecyclerView.ViewHolder(myView){
        var pIdTextView = myView.findViewById<TextView>(R.id.txtId)
        var pNameTextView = myView.findViewById<TextView>(R.id.txtName)
        var pPriceTextView = myView.findViewById<TextView>(R.id.txtPrice)
        var pImageView = myView.findViewById<ImageView>(R.id.imgProduct)
        var pImgAdd = myView.findViewById<ImageView>(R.id.imgAdd)

        fun initializeUiComponents(productId:Int,productName:String,productPrice:Int,productImage:String){
            pIdTextView.text = "ID: "+ productId.toString()
            pNameTextView.text ="Name: " + productName
            pPriceTextView.text ="Price: " + productPrice.toString()
            var picUrl = "http://192.168.10.4/OnlineStore/osimages/$productImage"
            picUrl = picUrl.replace(" ","%20")
            Picasso.get().load(picUrl).into(pImageView)
            pImgAdd.setOnClickListener {
             var fragmentDialogue = AmountFragment()
                var fragmentManager = (myView.context as Activity).fragmentManager
                fragmentDialogue.show(fragmentManager,"TAG")
            }
        }
    }
}