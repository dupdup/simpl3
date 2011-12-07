package dao.impl
import dao._
import model._
import com.twitter.querulous.evaluator.QueryEvaluator
import scala.util.Random

trait AccountRepositoryComponentHibernateImpl
extends AccountRepositoryComponent {
	def userRepository = new AccountRepositoryImpl
	val queryEvaluator = QueryEvaluator("localhost:3306/eczane", "root", "root")
	class AccountRepositoryImpl extends AccountRepository {

		def find(userid: String): Account = {
			val users = queryEvaluator.select("SELECT username,password " +
					"FROM eczane.appuser WHERE username = ? OR email = ?",userid,userid)
					{row =>new Account(row.getString(1),row.getString(2))}
			users(0)
		}
	}
}
trait AccountAuthorizationComponentImpl
extends AccountAuthorizationComponent {
	this: AccountRepositoryComponent =>
def userAuthorization = new AccountAuthorizationImpl
class AccountAuthorizationImpl extends AccountAuthorization {
	def authorize(username: String,password:String):Int ={
		val dbUser = userRepository.find(username)
		if(dbUser == null||dbUser.password!=password) return 0
		val token = Math.abs(Random.nextInt(100000))
		authMap= authMap+((token,username))
		token
	}
	def getUsernameByToken(token: Int) = authMap.get(token)
}
}