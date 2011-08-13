package dao.impl
import dao._
import model._
import com.twitter.querulous.evaluator.QueryEvaluator

trait UserRepositoryComponentHibernateImpl
                extends UserRepositoryComponent {
   def userRepository = new UserRepositoryImpl
   val queryEvaluator = QueryEvaluator("localhost:3306/eczane", "root", "root")
   class UserRepositoryImpl extends UserRepository {
     
     def findAll(): Seq[CustomerWithDate] = {
       queryEvaluator.select("SELECT id,name FROM eczane.medicine") { row =>
       	new CustomerWithDate(row.getInt(0),row.getString("name"))
       }
      }
     
     def find(username: String): Userx = {
       val users = queryEvaluator.select("SELECT name FROM eczane.medicine") { row =>
       	new Userx(row.getString("name"))
       }
//       queryEvaluator.execute("INSERT INTO eczane.medicine VALUES (?, ?)", 2, "Jacques")
       println("Find with Hibernate: " + username)
       users(0)
      }
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