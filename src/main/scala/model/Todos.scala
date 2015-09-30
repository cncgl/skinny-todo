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
  override val columns = Seq("id", "status", "title", "inserted_at", "updated_at")
  override def defaultAlias = createAlias("m")
  override def extract(rs: WrappedResultSet, n: ResultName[Todos]): Todos = new Todos(
    // Type Dynamic による取得はできるが、列名を指定すると取得できない
    // IDE などでは対応していないが、コンパイルは scala 2.10 以降ならビルドできる。
    id          = rs.long(n.id),
    status      = rs.boolean(n.status),
    title       = rs.string(n.title),
    inserted_at = rs.jodaDateTime(n.inserted_at),
    updated_at  = rs.jodaDateTime(n.updated_at)
  )
}
