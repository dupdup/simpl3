package dao
import com.twitter.querulous.evaluator.QueryEvaluator

trait QueryEvaluatore {
	val queryEvaluator = QueryEvaluator("localhost:3306/eczane", "root", "root")
}