package uk.co.chrisloy.bandwagon.controllers

import uk.co.chrisloy.bandwagon.views._
import uk.co.chrisloy.bandwagon.model._

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

object Application extends Controller {
  
  val itemForm = Form (
    tuple(
      "name" -> nonEmptyText,
      "tags" -> nonEmptyText
    )
  )
  
  def index = Action {
    Redirect(routes.Application.items)
  }
  
  def items = Action {
    Ok(html.index(Cache.items, Cache.tags, itemForm))
  }
  
  def newItem = Action { implicit request =>
    val (name, tagStr) = itemForm.bindFromRequest.get
    val tags:List[String] = tagStr.split(",").toList.map(s => s.trim().toLowerCase()).filter(s => !s.isEmpty())
    Cache.addItem(name, tags)
    Redirect(routes.Application.items)
  }
  
  def tags(url:String) = Action {
    val name = url.replace("_", " ")
    val tag = Cache.getTag(name)
    Ok(html.tagdetails(tag, Cache.getItems(tag)))
  }
}