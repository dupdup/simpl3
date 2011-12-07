package dao.impl
import dao._
import model.{Customer,CustomerType,CustomerWithMedicine,Medicine,MedicineWithPeriod}
import scala.collection.immutable.TreeSet
import org.scala_tools.time.Imports._
import org.joda.time.Days

class CustomerRepositoryHibernateImpl extends CustomerRepositoryComponent{
  implicit val order=Ordering.fromLessThan[MedicineWithPeriod](
      (a,b)=>{
        val la = Days.daysBetween(DateTime.now,a.startDate).getDays()%a.period
        val lb = Days.daysBetween(DateTime.now,b.startDate).getDays()%b.period
        if (la == lb) a.medicine.id>b.medicine.id else la>lb 
  }) 
  def findAllSortByMedicineDate(siteId:String) ={
	  		var set:List[CustomerWithMedicine]=List();
	  		var seq = queryEvaluator.
			select("""SELECT c.*,m.id,m.name,cm.period,cm.startdate 
			    FROM %s.customer_medicine cm,%s.medicine m,%s.customer c 
			    WHERE cm.medicineid=m.id 
			    AND cm.customerid = c.id 
			    AND c.siteId = ?
			    """.replaceAll("%s",dBName),siteId) { row =>{
			    var temp = set.filter(_.customer.id==row.getInt(1))
			    val medicine = MedicineWithPeriod(Medicine(row.getInt(8),row.getString(9)),row.getInt(10),new DateTime(row.getDate(11)));
			    if(temp.isEmpty){
					set= CustomerWithMedicine(
					    Customer(id = row.getInt(1), name=row.getString(2),address= row.getString(3),telno=row.getString(4),info=row.getString(5)
					        ,CustomerType(id=row.getInt(6), name=row.getString(7)))
					    ,TreeSet.apply(medicine))::set
			    }else{
			      var customer = temp.iterator.next()
			      customer.medicines = customer.medicines+medicine
			    }
			    }
			}
	  		set.sortWith(_.medicines.iterator.next.startDate>_.medicines.iterator.next.startDate)
			set.toList
  }
}