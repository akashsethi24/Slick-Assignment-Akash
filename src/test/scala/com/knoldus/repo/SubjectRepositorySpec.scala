package com.knoldus.repo

import com.knoldus.connection.H2DBComponent
import org.scalatest.FunSuite
import org.scalatest.concurrent.ScalaFutures

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by akash on 7/3/16.
  */
class SubjectRepositorySpec extends FunSuite with SubjectRepository with H2DBComponent with ScalaFutures {


  test("Insert Statement for Subject"){
    val output  = insertSubject()
    val result = Await.result(output,5.seconds)
    assert(result == 1)
  }
  test("Update Statement for Subject"){
    val output  = updateSubject(1,"Scala")
    val result = Await.result(output,5.seconds)
    assert(result == 1)
  }
  test("Delete Statement for Subject"){
    val output  = deleteSubject(1)
    val result = Await.result(output,5.seconds)
    assert(result == 1)
  }


}
