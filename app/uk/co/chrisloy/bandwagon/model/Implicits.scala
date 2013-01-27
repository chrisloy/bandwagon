package uk.co.chrisloy.bandwagon.model

import com.mongodb.casbah.Imports._

object Implicits {
  implicit def stringToTag(name:String):Tag = Tag(name)
  implicit def dbObjectToTag(dbObject:MongoDBObject):Tag = Tag(dbObject.as[String]("name"))
}
