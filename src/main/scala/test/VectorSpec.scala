package roma.arje.test

import org.scalatest._

import roma.arje.math._

class VectorSpec extends FlatSpec with Matchers {

  "A Vector" should "be correctly added to another vector" in {
    val v1: Vector = Vector(1,1,1)
    val v2: Vector = Vector(2,2,2)
    val v3: Vector = Vector(1.5,1.5,1.5)
    val v4: Vector = Vector(1.7, 0.3, -0.4)
    
    assert(v1 + v2 == Vector(3,3,3), "")
    assert(v1 + v3 == Vector(2.5,2.5,2.5), "")
    assert(v1 + v2 == Vector(3,3,3), "")
    assert(v1 + v3 == Vector(2.5,2.5,2.5), "")
  }
  
  "A Vector" should "be correctly subtracted from another vector" in {
    
  }
  
  "A Vector" should "be correctly multiplied to a scalar" in {
    
  }
  
  "A Vector" should "be correctly divided by a scalar" in {
    
  }
  
  "A Vector" should "have a correct dot product to another vector" in {
    
  }
  
  "A Vector" should "have a correct cross product to another vector" in {
    
  }
  
}