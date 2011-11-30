package tester
import org.scalatest.FunSuite
import dao.impl.AccountRepositoryComponentHibernateImpl
import dao.impl.AccountAuthorizationComponentImpl
import dao.AccountRepositoryComponent
import dao.impl.AccountRepositoryComponentHibernateImpl
import model.Account
import model.CustomerWithDate
import dao.AccountAuthorizationComponent
import scala.util.Random

class TestThemall extends FunSuite{
	val userAuth = new AccountAuthorizationComponentImpl with AccountAuthorizationComponent
	with AccountRepositoryComponent{
		def userRepository =	new AccountRepository{
			override def findAll()= List(CustomerWithDate(1,"ds"))
			override def find(un:String)= Account(un,"123")
		}
	}
	test("authorization") {
		assert(userAuth.userAuthorization.authorize("doruk","123")>0)
	}
	test("use token generated") {
		val newusername = Random.nextString(3)
		val token =userAuth.userAuthorization.authorize(newusername,"123")
		assert(userAuth.userAuthorization.getUsernameByToken(token).get==newusername)
	}
}