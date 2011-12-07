package tester
import org.scalatest.FunSuite
import dao._
import dao.impl._
import model.Account
import scala.util.Random

class TestThemall extends FunSuite{
	val userAuth = new AccountAuthorizationComponentImpl with AccountAuthorizationComponent
	with AccountRepositoryComponent{
		def userRepository =	new AccountRepository{
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
	test("get all customers with medicine"){
		val customerRepo = new CustomerRepositoryHibernateImpl{override def dBName="eczanetest"}
		val customerList = customerRepo.findAllSortByMedicineDate("doruk")
		assert(customerList(0).medicines.size==2)
	}
}