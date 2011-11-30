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
import javax.ws.rs.core.Response
import javax.ws.rs.QueryParam

	
@Path("/auth")
class AuthMe(){
  
  val env = new AccountAuthorizationComponentImpl
            with AccountRepositoryComponentHibernateImpl
 
  @GET
  @Path("/gettoken")
  @Produces(Array("application/json"))
  def getToken(@QueryParam("username") username:String, 
      @QueryParam("password") password:String):Response = {
    val token = env.userAuthorization.authorize(username,password)
	Response.ok(token).build()
  }
    
  @GET
  @Path("/check")
  @Produces(Array("application/json"))
  def checkToken:Response = {
    Response.ok(env.userRepository.find("doruk")).build();
  }
}
