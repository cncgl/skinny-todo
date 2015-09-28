package controller

import org.json4s.jackson.Json
import org.scalatra.Ok
import skinny.SkinnyController
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
    //render("index")
  }

  def show = {
    // TODO: id が不正なら NoContents を返す
    // id がない場合とid が不正な場合のエラー処理
    params.getAs[Long]("id").map { id =>
      toJSONString(Todos.findById(id))
    }.getOrElse(toJSONString(Map("errors" -> "invalide")))
  }

  def create = {
    render("create")
  }

  def update = {
    render("update")
  }

  def delete = {
    params.getAs[Long]("id").map { id =>
      Todos.deleteById(id)

      Ok(toJSONString(Map("result" -> "ok")))
    }.getOrElse(toJSONString(Map("errors" -> "invalide")))
    render("delete")
  }

}

//case class Todo(status: Boolean = false, title: String)