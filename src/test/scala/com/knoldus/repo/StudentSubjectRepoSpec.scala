package com.knoldus.repo

import com.knoldus.connection.H2DBComponent
import org.scalatest.FunSuite
import org.scalatest.concurrent.ScalaFutures
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by akash on 7/3/16.
  */
class StudentSubjectRepoSpec extends FunSuite with StudentSubjectRepository with H2DBComponent {


  test("Insert Statement for Map table"){
    val output  = insertStatement()
    val result = Await.result(output,5.seconds)
    assert(result == 1)
  }
  test("Update Statement for Map table"){
    val output  = updateStatement(1,1)
    val result = Await.result(output,5.seconds)
    assert(result == 1)
  }
  test("Delete Statement for Map table"){
    val output  = deleteStatement(1)
    val result = Await.result(output,5.seconds)
    assert(result == 1)
  }
  test("Get List  for StudentBySubject table"){
    val output  = getStudentBySubject(1)
    val result = Await.result(output,5.seconds)
    assert(result == Vector(("Scala","Akash")))
  }
  test("Get List  for SubjectByStudent table"){
    val output  = getSubjectByStudent(1)
    val result = Await.result(output,5.seconds)
    assert(result == Vector(("Akash","Scala")))
  }

}
