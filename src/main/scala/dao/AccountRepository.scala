package dao
import model._

trait AccountRepositoryComponent { // For expressing dependencies
	def userRepository: AccountRepository // Way to obtain the dependency

	trait AccountRepository { // Interface exposed to the user
	def find(username: String): Account
//	def findAll(): List[CustomerWithDate]
}
}
// Component definition, as before
trait AccountAuthorizationComponent {
	def userAuthorization: AccountAuthorization
	var authMap : Map[Int,String]=Map()
	trait AccountAuthorization {
	def authorize(username: String,password:String):Int
	def getUsernameByToken(username: Int):Option[String]
}
}
