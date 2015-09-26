package controller

import skinny._
import skinny.controller.AssetsController

object Controllers {

  def mount(ctx: ServletContext): Unit = {
    root.mount(ctx)
    todos.mount(ctx)
    AssetsController.mount(ctx)
  }

  object root extends RootController with Routes {
    val indexUrl = get("/?")(index).as('index)
  }

  object todos extends TodoController with Routes {
    val indexUrl  = get("/api/todos")(index).as('index)
    val showUrl   = get("/api/todos/:id")(show).as('show)
    val createUrl = post("/api/todos")(create).as('create)
    val updateUrl = put("/api/todos/:id")(update).as('update)
    val deleteUrl = delete("/api/todos/:id")(delete).as('delete)
  }
}
