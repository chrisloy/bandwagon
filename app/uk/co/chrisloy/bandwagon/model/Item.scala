package uk.co.chrisloy.bandwagon.model

object Item {
  
  def apply(name:String, tags:List[Tag]):Item =  {
    new Item(name.toLowerCase(), tags)
  }
}

class Item (val name:String, val tags:List[Tag]) {
  
  def has(tag:Tag) = {
    tags.contains(tag)
  }
  
  override def toString = "Item[" + name + ":" + tags + "]"
}