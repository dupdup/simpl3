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
import javax.ws.rs.core.Response

case class Widget(i:Int,x:String)

@Path("/todo")
class MyController(){

	val gson = new Gson
	implicit val WidgetFormat: Format[Widget] = asProduct2("i", "x")(Widget)(Widget.unapply(_).get)
//	implicit val CustomerWithDateFormat: Format[CustomerWithDate] = asProduct2("name", "va")(CustomerWithDate)(CustomerWithDate.unapply(_).get)
	val env = new AccountAuthorizationComponentImpl
	with AccountRepositoryComponentHibernateImpl

	@GET
	@Produces(Array("application/json"))
	def getr:String = gson.toJson(Widget(3,"ds"))

	@GET
	@Path("/db")
	@Produces(Array("application/json"))
	def getfromDB:Response = {
		//    gson.toJson(env.userRepository.find("doruk"))
		Response.ok(env.userRepository.find("doruk")).build();
	}

	@GET
	@Path("/customer")
	@Produces(Array("application/json"))
	def getAllCustomersOrderByMedicineDate:String = {
		val x= tojson("doruk")//tojson(env.userRepository.findAll())
		dispatch.json.JsValue.toJson(x)
	}

}
