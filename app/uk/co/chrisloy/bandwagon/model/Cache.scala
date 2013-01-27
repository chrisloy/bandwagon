package uk.co.chrisloy.bandwagon.model

import Implicits._
import com.mongodb.casbah.Imports._

object Cache {
  
  var tags:List[Tag] = Nil

  def recover() {
    Dao.allTags.foreach(tag => setupTag(tag.as[String]("name"), tag.as[Iterable[String]]("tags")))
  }
  
  recover()
  
  def addTag(tagStr:String):Tag = {
    if (!tags.exists(t => t.name == tagStr)) {
      val tag = new Tag(tagStr)
      if (tag.valid()) {
        tags = tag :: tags
        Dao += tag
        tag
      } else {
        null
      }
    } else {
      tags.filter(t => t.name == tagStr).head
    }
  }
  
  def setupTag(tag:Tag, subTags:Iterable[String]) {
    subTags.foreach(t => tag.addTag(t))
  }
  
  def getTag(name:String) = tags.filter(t => t.name == name).head
  
  override def toString = "Cache[" + tags.size + "]"
}
