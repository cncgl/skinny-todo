package controller

import skinny.SkinnyController
import skinny.util.JSONStringOps._
import model.Todos

/**
 * Todo を扱うコントローラー
 * Created by cncgl on 15/09/26.
 */
class TodoController extends SkinnyController {
  protectFromForgery()    // CSRF protection enabled

  before() {
    contentType = formats("json")
  }

  def index = {
    val todos: List[Todos] = Todos.findAll()
    toJSONString(todos)
    //render("index")
  }

  def show = {
    render("show")
  }

  def create = {
    render("create")
  }

  def update = {
    render("update")
  }

  def delete = {
    render("delete")
  }

}

//case class Todo(status: Boolean = false, title: String)