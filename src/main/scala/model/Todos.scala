package model

import org.joda.time.DateTime
import scalikejdbc.WrappedResultSet
import scalikejdbc.ResultName
import skinny.orm.SkinnyCRUDMapper

/**
 * Database 上の Todos モデル
 * Created by shigeru on 15/09/28.
 */
case class Todos(id: Long, status: Boolean, title: String, inserted_at: DateTime, updated_at: DateTime)
object Todos extends SkinnyCRUDMapper[Todos] {
  override def defaultAlias = createAlias("m")
  override def extract(rs: WrappedResultSet, n: ResultName[Todos]): Todos = new Todos(
    id          = rs.get(n.id),
    status      = rs.get(n.status),
    title       = rs.get(n.title),
    inserted_at = rs.get(n.inserted_at),
    updated_at  = rs.get(n.updated_at)
  )
}
