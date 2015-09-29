package controller

import org.joda.time.DateTime
import org.json4s.jackson.Json
import org.scalatra.Ok
import skinny._
import skinny.controller.SkinnyController
import skinny.util.JSONStringOps._
import model.Todos

/**
 * Todo を扱うコントローラー
 * Created by cncgl on 15/09/26.
 */
class TodoController extends SkinnyController {
  // API だと Session は使わないので CSRF を無効にしたが正しいのか?
  //protectFromForgery()    // CSRF protection enabled

  before() {
    contentType = formats("json")
  }

  def index = {
    val todos: List[Todos] = Todos.findAll()
    toJSONString(todos)
  }

  def show = {
    // TODO: id が不正なら NoContents を返す
    // id がない場合とid が不正な場合のエラー処理
    params.getAs[Long]("id").map { id =>
      toJSONString(Todos.findById(id))
    }.getOrElse(toJSONString(Map("errors" -> "invalide")))
  }

  def createFormParams = params.permit(
    "status"      -> ParamType.Boolean,
    "title"       -> ParamType.String
  )

  def create = {
    // TODO: validation 処理
    logger.debug("body:"+request.body)
    logger.debug("status:" + params.getAs[Boolean]("status"))
    logger.debug("title:" + params.getAs[String]("title"))
    //var status:Boolean = params.getAs[Boolean]("status").getOrElse(halt(400))
    //var status:Boolean = params.getOrElse("status", halt(400)).toBoolean
    //var title:String   = params.getAs[String]("title").getOrElse(halt(400))
    //var title:String   = params.getOrElse("title", halt(400))

    //Todos.createWithPermittedAttributes(createFormParams)
    val todo: Option[TodoRequest] = fromJSONString[TodoRequest](request.body)
    Todos.createWithAttributes(
      'status      -> todo.get.status,
      'title       -> todo.get.title,
      'inserted_at -> DateTime.now,
      'updated_at  -> DateTime.now
    )

    Ok(toJSONString(Map("result" -> "ok")))
  }

  def update = {
    val todo: Option[TodoRequest] = fromJSONString[TodoRequest](request.body)
    params.getAs[Long]("id").map { id =>
      Todos.updateById(id).withAttributes(
        'status     -> todo.get.status,
        'title      -> todo.get.title,
        'updated_at -> DateTime.now
      )
    }.getOrElse(toJSONString(Map("errors" -> "invalide")))

    Ok(toJSONString(Map("result" -> "ok")))
  }

  def delete = {
    params.getAs[Long]("id").map { id =>
      Todos.deleteById(id)

      Ok(toJSONString(Map("result" -> "ok")))
    }.getOrElse(toJSONString(Map("errors" -> "invalide")))
  }

}

case class TodoRequest(status: Boolean = false, title: String)