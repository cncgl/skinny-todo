package controller

import skinny.SkinnyController

/**
 * Created by shigeru on 15/09/26.
 */
class TodoController extends SkinnyController {
  protectFromForgery()    // CSRF protection enabled

  def index = {
    render("index")
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
