package controllers

import play.api.mvc.{Security, Action, Controller}
import play.api.data.Forms._
import play.api.data.Form
import eu.chlan.gplayx.User


class Auth extends Controller {

  val loginForm = Form(
    mapping(
      "username" -> text,
      "password" -> text
    ) (User.apply)(User.unapply) verifying ("Invalid username or password", result => result match {
      case user => check(user.username, user.password)
    }
  ))

  def check(username: String, password: String) = {
    (username == "admin" && password == "1234")
  }

  def login = Action { implicit request =>
    Ok(views.html.login(loginForm))
  }

  def authenticate = Action { implicit request =>


  loginForm.bindFromRequest.fold(
    formWithErrors => BadRequest(views.html.login(formWithErrors)),
    user => Redirect(routes.Application.index).withSession(Security.username -> user.username)
  )

  }

//  def authenticate = Action { implicit request =>
//    loginForm.bindFromRequest.fold(
//      formWithErrors => BadRequest(views.html.login(formWithErrors)),
//      user => Redirect(routes.Application.index).withSession(Security.username -> user._1)
//    )
//  }
//
//  def logout = Action {
//    Redirect(routes.Auth.login).withNewSession.flashing(
//      "success" -> "You are now logged out."
//    )
//  }
}
