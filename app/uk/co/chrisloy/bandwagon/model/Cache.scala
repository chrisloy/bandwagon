package uk.co.chrisloy.bandwagon.model

object Cache {
  
  var tags:List[Tag] = Tag.all
  var items:List[Item] = Nil
  
  hax()
  
  def hax() {
    addItem("Pulp Fiction", List("film", "violent", "royale", "with", "cheese"))
    addItem("Ulysses", List("book", "james joyce", "long", "difficult", "ireland"))
    addItem("Scala", List("software", "terse", "new", "ladders"))
    addItem("George Orwell", List("person", "british", "socialism", "author"))
    addItem("Python", List("software", "terse", "old", "snakes"))
    addItem("Java", List("software", "verbose", "old", "coffee"))
    addItem("Ernest Hemingway", List("person", "author", "old", "suicide"))
    addItem("District 9", List("film", "south africa", "science fiction", "aliens", "mockumentary"))
    addItem("Irvine Welsh", List("person", "author", "british", "trainspotting", "scotland"))
    addItem("Play", List("software", "framework", "new"))
    addItem("Philosophy", List("school", "discipline"))
    addItem("Wittgenstein", List("philosophy", "language", "dead", "person"))
    addItem("James Joyce", List("person", "author", "ulysses", "ireland"))
  }
  
  def addTag(tagStr:String):Tag = {
    if (!tags.exists(t => t.name == tagStr)) {
      val tag = new Tag(tagStr)
      tags = tag :: tags
      Tag.persist(tag)
      tag
    } else {
      tags.filter(t => t.name == tagStr).head
    }
  }
  
  def addItem(name:String, tagNames:List[String]) {
    tagNames.foreach(t => addTag(t))
    val item = Item(name, tags.filter(t => tagNames.contains(t.name)))
    items = item :: items
  }
  
  def getTag(name:String) = tags.filter(t => t.name == name).head
  
  def getItems(tag:Tag) = items.filter(i => i.has(tag))
  
  override def toString = "Cache[" + items.size + "]"
}