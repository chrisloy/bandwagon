package uk.co.chrisloy.bandwagon.model

import Implicits._
import com.mongodb.casbah.Imports._

object Dao {
  
  val mongo = MongoClient()("bandwagon")
  val tags = mongo("tags")
  
  def allTags():Iterable[DBObject] = { tags }
  
  def += (tag:Tag):Unit = {
    val builder = MongoDBObject.newBuilder
    builder += "name" -> tag.name
    builder += "tags" -> tag.tagNames
    val obj = builder.result
    tags += obj
  }
  
  def apply(name:String):Tag = {
    val query = MongoDBObject("name" -> name)
    val cursor = tags.findOne(query)
    cursor match {
      case Some(dbObject) => Tag(dbObject.as[String]("name"))
      case None => Tag(name)
    }
  }
  
  def update(tag:Tag):Unit = {
    allTags().filter(t => t("name") == tag.name).foreach(t => println(t))
    val query = MongoDBObject("name" -> tag.name)
    val cursor = tags.findOne(query)
    cursor match {
      case Some(dbObject) => tags.update(dbObject, $set("tags" -> tag.tagNames))
      case None => apply(tag.name)
    }
  }
}
