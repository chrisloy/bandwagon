package uk.co.chrisloy.bandwagon.model

import play.api.db._
import play.api.Play.current
import Implicits._
import play.Logger
import com.mongodb.casbah.Imports._

object Tag {
  def apply(name:String):Tag = {
    val clean = name.trim.toLowerCase
    val tag = new Tag(name)
    Dao get name match {
      case Some(db) => {
        db.as[List[String]]("tags").foreach(t => tag += t)
        tag
      }
      case None => tag
    }
  }
}

class Tag (val name:String) extends Equals {
  
  val url = name.replace(" ", "_")
  var tags:List[Tag] = Nil
  
  def canEqual(other: Any) = {
    other.isInstanceOf[Tag]
  }
  
  def +=(tag:Tag):Tag = {
    Logger.debug("Adding tag " + tag + " to " + this)
    tags = tag :: tags
    Dao update this
    this
  }
  
  def valid():Boolean = true // TODO
  
  def tagNames():List[String] = {
    tags.map(t => t.name)
  }
  
  override def equals(other: Any) = {
    other match {
      case that: Tag => that.canEqual(Tag.this) && name == that.name
      case _ => false
    }
  }
  
  override def toString = name;
  override val hashCode = 41 + name.hashCode
}
