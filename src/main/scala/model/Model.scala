package model

sealed case class Userx(username: String)
sealed case class Customer(username: String)
sealed case class CustomerWithDate(id:Int,name: String) extends Customer(name)