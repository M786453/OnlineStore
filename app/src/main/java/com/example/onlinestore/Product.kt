package com.example.onlinestore

class EProduct {
    var pId : Int
    var pName: String
    var pPrice: Int
    var pImage: String
    constructor(id:Int,name:String,price:Int,image:String){
        this.pId = id
        this.pName =name
        this.pPrice = price
        this.pImage = image
    }
}