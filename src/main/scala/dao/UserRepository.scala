package dao
import model._

trait UserRepositoryComponent { // For expressing dependencies
  def userRepository: UserRepository // Way to obtain the dependency

  trait UserRepository { // Interface exposed to the user
    def find(username: String): Userx
    def findAll(): Seq[CustomerWithDate]
  }
}
// Component definition, as before
trait UserAuthorizationComponent {
   def userAuthorization: UserAuthorization
 
   trait UserAuthorization {
      def authorize(user: Userx)
   }
}
