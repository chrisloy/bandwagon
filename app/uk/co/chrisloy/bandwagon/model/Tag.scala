package uk.co.chrisloy.bandwagon.model

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current


object Tag {
  
  val tag = {
    get[Long]("id") ~
    get[String]("name") map {
      case id~name => new Tag(name.toLowerCase())
    }
  }
  
  def all(): List[Tag] = DB.withConnection { implicit c =>
    SQL("select * from tag").as(tag *)
  }
  
  def persist(tag:Tag):Unit = {
    def create(label: String) {
      DB.withConnection { implicit c =>
        SQL("insert into tag (name) values ({name})").on(
          'label -> label
        ).executeUpdate()
      }
    }
  }
}



class Tag (val name:String) extends Equals {
  
  override def toString = "Tag[" + name + "]";
  
  def canEqual(other: Any) = {
    other.isInstanceOf[Tag]
  }
  
  val url = name.replace(" ", "_")
  
  override def equals(other: Any) = {
    other match {
      case that: Tag => that.canEqual(Tag.this) && name == that.name
      case _ => false
    }
  }
  
  override val hashCode = 41 + name.hashCode
}
