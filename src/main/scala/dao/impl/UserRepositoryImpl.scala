package dao.impl
import dao.UserRepositoryComponent
import model._

trait UserRepositoryComponentHibernateImpl
                extends UserRepositoryComponent {
   def userRepository = new UserRepositoryImpl
 
   class UserRepositoryImpl extends UserRepository {
     def find(username: String): Userx = {
         println("Find with Hibernate: " + username)
         new Userx(username)
      }
   }
}

// Component definition, as before
trait UserAuthorizationComponent {
   def userAuthorization: UserAuthorization
 
   trait UserAuthorization {
      def authorize(user: Userx)
   }
}

// Component implementation
trait UserAuthorizationComponentImpl
                extends UserAuthorizationComponent {
   // Dependencies
   this: UserRepositoryComponent =>
 
   def userAuthorization = new UserAuthorizationImpl
 
   class UserAuthorizationImpl extends UserAuthorization {
      def authorize(user: Userx) {
         println("Authorizing " + user.username)
         // Obtaining the dependency and calling a method on it
         userRepository.find(user.username)
      }
   }
}