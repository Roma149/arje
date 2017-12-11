package roma.arje

import roma.arje.particles._
import roma.arje.math._

object Test {

  def main(args: Array[String]): Unit = {
    
    var p: Particle = new Particle(Vector(), Vector(1,1,1), Vector())
    
    p.update(0.1)
    println(p.toString)
    
    p.update(0.1)
    println(p.toString)
    
    p.update(0.1)
    println(p.toString)
    
    p.update(0.1)
    println(p.toString)
    
    var p2: Particle = new Particle(Vector(0,0,0), Vector(1,2,3), Vector(0.5,0.5,0.5), 1, 1)
    println(p2)
    val r: Vector = Vector(1,1,1)
    println(p2.magnetic_field(r))
    
    val v1: Vector = Vector(1,1,1)
    val v2: Vector = Vector(2,2,2)
    var p3: Particle = new Particle(v1, Vector(0,0,0), Vector(0,0,0))
    var p4: Particle = new Particle(v2, Vector(0,0,0), Vector(0,0,0))
    println(p4.position.to(p3.position))
    
    var p5: Particle = new Particle(Vector(0,0,0), Vector(0,0,0), Vector(0,0,0), 1, 1)
    println(p5.electric_field(Vector(1,1,1)))
    //println(p5.electric_force_on_charge(Vector(1,1,1), -0.5))
    println(p5.electric_force_on_charge(Vector(1,0,0), 0.1).magnitude)
    println(Math.sqrt(2))
    var p6: Particle = new Particle(Vector(1,1,1), Vector(0,0,0), Vector(0,0,0), 1, 1)
  }

}