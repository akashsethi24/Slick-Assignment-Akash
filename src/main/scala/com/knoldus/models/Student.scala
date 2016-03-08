package com.knoldus.models

/**
  * Created by akash on 7/3/16.
  */

case class Student(stuId:Int =0,stuName:String,stuEmail:String)

case class Subject(subId:Int =0,subName:String)

case class StuSub(id:Int=0, stuId:Int, subId:Int)
