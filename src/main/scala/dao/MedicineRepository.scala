package dao
import model._

trait MedicineRepositoryComponent extends QueryEvaluatore{ // For expressing dependencies
	def medicineRepository: MedicineRepository  // Way to obtain the dependency
	
	trait MedicineRepository { // Interface exposed to the user
		def findAll(): List[Medicine]
		def findByCustomerId(customerId:Int,siteId:Int):List[MedicineWithPeriod]
	}
}
