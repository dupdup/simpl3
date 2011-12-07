package model
import scala.collection.immutable.SortedSet
import org.scala_tools.time.Imports._
sealed case class Account(username: String, password:String)
sealed case class CustomerType(id:Int,name:String)
sealed case class Customer(id:Int , name:String,address:String,telno:String,info:String,customerType:CustomerType)
sealed case class CustomerWithMedicine(customer:Customer,var medicines:SortedSet[MedicineWithPeriod])

sealed case class Medicine(id:Int,mediname:String)
sealed case class MedicineWithPeriod(medicine:Medicine,period:Int,startDate:DateTime)
