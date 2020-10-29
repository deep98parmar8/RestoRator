package com.example.restorater

class Restaurant {
    var id: String? = null
    var name: String? = null
    var rating: Int? = null


    constructor(){
    }

    constructor(id: String, name: String, rating: Int){
        this.id = id
        this.name = name
        this.rating = rating
    }



}
