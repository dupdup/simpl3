package gok

import javax.ws.rs.{GET, Path, Produces}
import javax.ws.rs.ext.Provider
import javax.xml.bind.annotation.XmlRootElement
import com.google.gson.Gson
import sjson.json._
import JsonSerialization._
import DefaultProtocol._
import dao.impl._
import model._
import model.CustomerWithDate

case class Widget(i:Int,x:String)
	
@Path("/todo")
class MyController(){
  
  val gson = new Gson
  implicit val WidgetFormat: Format[Widget] = asProduct2("i", "x")(Widget)(Widget.unapply(_).get)
  implicit val CustomerWithDateFormat: Format[CustomerWithDate] = asProduct2("name", "va")(CustomerWithDate)(CustomerWithDate.unapply(_).get)
  val env = new UserAuthorizationComponentImpl
            with UserRepositoryComponentHibernateImpl
 
  @GET
  @Produces(Array("application/json"))
  def getr:String = gson.toJson(Widget(3,"ds"))

  @GET
  @Path("/db")
  @Produces(Array("application/json"))
  def getfromDB:String = {
    gson.toJson(env.userRepository.find("doruk"))
    }
  @GET
  @Path("/customer")
  @Produces(Array("application/json"))
  def getAllCustomersOrderByMedicineDate:String = {
    val x= tojson(env.userRepository.findAll())
    dispatch.json.JsValue.toJson(x)
    }

}
