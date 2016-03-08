package com.knoldus.repo

import com.knoldus.connection.DBComponent
import com.knoldus.models.Student
import scala.concurrent.Future

/**
  * Created by akash on 7/3/16.
  */

trait StudentConf {

  this: DBComponent =>
  import driver.api._

  val studentTable = TableQuery[StudentTable]

  class StudentTable(tag: Tag) extends Table[Student](tag, "student") {
    val stu_id = column[Int]("stu_id", O.PrimaryKey, O.AutoInc)
    val stu_name = column[String]("stu_name", O.SqlType("VARCHAR(20)"))
    val stu_email = column[String]("stu_email", O.SqlType("VARCHAR(30)"))

    def * = (stu_id, stu_name, stu_email) <>(Student.tupled, Student.unapply)

  }

}
trait StudentRepository extends StudentConf {

  this: DBComponent =>

  import driver.api._

  def insertStudent(): Future[Int] = {
    db.run {
      studentTable.returning(studentTable.map(_.stu_id)) += Student(1, "Some", "Some@one.com")
    }
  }

  def deleteStudent(id: Int): Future[Int] = {

    val deleteStudent = studentTable.filter(_.stu_id === id).delete
    db.run(deleteStudent)
  }

  def updateStudent(id: Int, updatedName: String): Future[Int] = {

      val updateSubject = for {stu <- studentTable if stu.stu_id === id} yield stu.stu_name
      val updateAction = updateSubject.update(updatedName)
      db.run(updateAction)

  }


}

