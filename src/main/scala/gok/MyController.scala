package gok

import javax.ws.rs.{GET, Path, Produces}
import javax.ws.rs.ext.Provider
import javax.xml.bind.annotation.XmlRootElement
import com.google.gson.Gson;
import dao.impl._
import model._

case class Widget(i:Int,x:String)
	
@Path("/todo")
class MyController(gson:Gson){
  def this()= this(new Gson())
  val env = new UserAuthorizationComponentImpl
            with UserRepositoryComponentHibernateImpl
 
  @GET
  @Produces(Array("application/json"))
  def getr:String = gson.toJson(Widget(3,"ds"));

  @GET
  @Path("/db")
  @Produces(Array("application/json"))
  def getfromDB:String = gson.toJson(env.userRepository.find("doruk"));

}
