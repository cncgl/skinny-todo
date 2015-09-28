package model

import scalikejdbc.WrappedResultSet
import scalikejdbc.ResultName
import skinny.orm.SkinnyCRUDMapper

/**
 * Created by shigeru on 15/09/28.
 */
case class Todos(id: Long, status: Boolean, title: String)
object Todos extends SkinnyCRUDMapper[Todos] {
  override def defaultAlias = createAlias("m")
  override def extract(rs: WrappedResultSet, n: ResultName[Todos]) = new Todos(
    id     = rs.get(n.id),
    status = rs.get(n.status),
    title  = rs.get(n.title)
  )
}
