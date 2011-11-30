package model

sealed case class Account(username: String, password:String)
sealed case class Customer(username: String)
sealed case class CustomerWithDate(id:Int,name: String) extends Customer(name)