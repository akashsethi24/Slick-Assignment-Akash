package com.knoldus.repo

import com.knoldus.connection.DBComponent
import com.knoldus.models.Subject

import scala.concurrent.Future

/**
  * Created by akash on 7/3/16.
  */


trait SubjectConf {

  this: DBComponent =>
  import driver.api._

  val subjectTable = TableQuery[SubjectTable]

  class SubjectTable(tag: Tag) extends Table[Subject](tag, "subject") {

    val sub_id = column[Int]("sub_id", O.PrimaryKey, O.AutoInc)
    val sub_name = column[String]("sub_name", O.SqlType("VARCHAR(20)"))

    def * = (sub_id, sub_name) <>(Subject.tupled, Subject.unapply)
  }

}

trait SubjectRepository extends SubjectConf{

  this: DBComponent =>
  import driver.api._

  def insertSubject():Future[Int] = {

    val insertSubject = subjectTable += Subject(1, "C++")
    db.run(insertSubject)
  }

  def deleteSubject(id: Int):Future[Int] = {

      val deleteSubject = subjectTable.filter(_.sub_id === id)
      val deleteAction = deleteSubject.delete
      db.run(deleteAction)
  }

  def updateSubject(id:Int,updatedName:String):Future[Int] = {

    val updateSubject = for{sub <- subjectTable if sub.sub_id === id }yield sub.sub_name
    val updateAction = updateSubject.update(updatedName)
    db.run(updateAction)

  }

}
