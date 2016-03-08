package com.knoldus.repo

import com.knoldus.connection.DBComponent
import com.knoldus.models.StuSub
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by akash on 7/3/16.
  */
trait StudentSubjectConf extends StudentConf with SubjectConf{

  this: DBComponent =>
  import driver.api._

  val stu_sub_Table = TableQuery[StudentSubjectTable]

  class StudentSubjectTable(tag: Tag) extends Table[StuSub](tag, "stu_sub") {

    val id = column[Int]("stu_sub_id", O.PrimaryKey, O.AutoInc)
    val stu_id = column[Int]("stu_id")
    val sub_id = column[Int]("sub_id")

    def student = foreignKey("stu_id", stu_id, studentTable)(_.stu_id)

    def subject = foreignKey("sub_id", sub_id, subjectTable)(_.sub_id)

    def * = (id, stu_id, sub_id) <>(StuSub.tupled, StuSub.unapply)
  }

}

trait StudentSubjectRepository extends StudentSubjectConf{

  this: DBComponent =>
  import driver.api._

  def insertStatement():Future[Int] = {

      val insertStatement = stu_sub_Table += StuSub(1,1,1)
      db.run(insertStatement)
    }

  def updateStatement(id:Int,stu_id:Int):Future[Int] = {

      val updateSubject = for{stu <- stu_sub_Table if stu.id === id }yield stu.stu_id
      val updateAction = updateSubject.update(stu_id)
      db.run(updateAction)
  }

  def deleteStatement(id:Int):Future[Int] = {

      val deleteSubject = stu_sub_Table.filter(_.id === id)
      val deleteAction = deleteSubject.delete
      db.run(deleteAction)
  }

  def getStudentBySubject(id:Int): Future[Seq[(String, String)]] = {

    val studentBySubject = for {
      ((subject,mapping),student)<- subjectTable.filter(_.sub_id === id).join(stu_sub_Table).on(_.sub_id === _.sub_id).
        join(studentTable).on(_._2.stu_id === _.stu_id)
    }yield(subject.sub_name, student.stu_name)
    db.run(studentBySubject.result)

  }

  def getSubjectByStudent(id:Int): Future[Seq[(String, String)]] = {

    val subjectByStudent = for {
      ((subject,mapping),student)<- studentTable.filter(_.stu_id === id).join(stu_sub_Table).on(_.stu_id === _.stu_id).
        join(subjectTable).on(_._2.sub_id === _.sub_id)
    }yield(subject.stu_name, student.sub_name)
    db.run(subjectByStudent.result)

  }
}

