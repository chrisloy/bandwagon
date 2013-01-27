package uk.co.chrisloy.bandwagon.model

class User(val id:Int, val name:String) {
  
  var likes:List[Tag] = Nil
  
  def like(item:Tag) = item :: likes
}