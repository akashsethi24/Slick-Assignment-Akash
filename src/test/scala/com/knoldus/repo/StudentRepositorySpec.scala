package com.knoldus.repo

import com.knoldus.connection.H2DBComponent
import org.scalatest.FunSuite
import org.scalatest.concurrent.ScalaFutures
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by akash on 7/3/16.
  */
class StudentRepositorySpec extends FunSuite with StudentRepository with H2DBComponent with ScalaFutures{

  test("Insert Statement"){
    val output  = insertStudent()
    val result = Await.result(output,5.seconds)
    assert(result == 2)
  }
  test("Update Statement"){
    val output  = updateStudent(1,"Scala")
    val result = Await.result(output,5.seconds)
    assert(result == 1)
  }
  test("Delete Statement"){
    val output  = deleteStudent(1)
    val result = Await.result(output,5.seconds)
    assert(result ==1)
  }

}
