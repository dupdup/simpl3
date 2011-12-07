package dao
import model._

trait CustomerRepositoryComponent extends QueryEvaluatore{ // Fonnnr expressing dependencies
	def findAllSortByMedicineDate(siteId:String): List[CustomerWithMedicine]
	def dBName = "eczane"
}