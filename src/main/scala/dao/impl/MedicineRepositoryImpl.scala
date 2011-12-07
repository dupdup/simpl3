package dao.impl
import dao._
import model.Medicine
import model.MedicineWithPeriod
import org.joda.time.DateTime

trait MedicineRepositoryComponentHibernateImpl
extends MedicineRepositoryComponent {
	def userRepository = new MedicineRepositoryImpl
	class MedicineRepositoryImpl extends MedicineRepository {

		def findAll() = {
			var seq = queryEvaluator.select("SELECT id,name FROM eczane.medicine") { row =>
				Medicine(row.getInt(1),row.getString(2))
			}
			seq.toList
		}
		def findByCustomerId(customerId:Int,siteId:Int) = {
			var seq = queryEvaluator.
			select("""SELECT m.id,m.name,cm.period,cm.startdate 
			    FROM eczane.customer_medicine cm,eczane.medicine m,eczane.customer c 
			    WHERE cm.medicineid=m.id 
			    AND cm.customerid = c.id 
			    AND cm.customerid = ? 
			    AND c.siteId = ?
			    """,customerId,siteId) { row =>
				MedicineWithPeriod(Medicine(row.getInt(1),row.getString(2)),row.getInt(3),new DateTime(row.getDate(4)))
			}
			seq.toList
		}
		
	}
}