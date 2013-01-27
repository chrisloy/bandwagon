package uk.co.chrisloy.bandwagon.controllers

import uk.co.chrisloy.bandwagon.views._
import uk.co.chrisloy.bandwagon.model._
import uk.co.chrisloy.bandwagon.model.Implicits._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.Logger

object Application extends Controller {
  
  val itemForm = Form (
    tuple(
      "name" -> nonEmptyText,
      "tags" -> nonEmptyText
    )
  )
  
  def items = Action {
    Ok(html.index(Cache.tags, Cache.tags, itemForm))
  }
  
  def newItem = Action { implicit request =>
    val (name, tagStr) = itemForm.bindFromRequest.get
    val tags:List[String] = tagStr.split(",").toList.filter(s => !s.isEmpty())
    Logger.info("Adding " + name + " : " + tags)
    Cache.setupTag(name, tags)
    Redirect(routes.Application.items)
  }
  
  def tags(url:String) = Action {
    val name = url.replace("_", " ")
    val tag = Cache.getTag(name)
    Ok(html.tagdetails(tag))
  }
}
