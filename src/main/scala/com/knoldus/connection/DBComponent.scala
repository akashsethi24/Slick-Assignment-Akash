package com.knoldus.connection

import slick.driver.JdbcProfile
/**
  * Created by akash on 7/3/16.
  */

trait DBComponent {

  val driver: JdbcProfile

  import driver.api._

  val db: Database

}
