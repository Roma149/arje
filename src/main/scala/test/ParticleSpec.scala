package roma.arje.test

//import org.scalatest.FlatSpec
//import org.scalatest.Matchers

import org.scalatest._

import roma.arje._
import roma.arje.math._
import roma.arje.particles._

class ParticleSpec extends FlatSpec with Matchers {

  "A Particle" should "update position correctly" in {
    var p: Particle = new Particle(Vector(0,0,0), Vector(1,1,1), Vector(0,0,0), 1, 0, 0)
    val dt: Double = 1
    assert(p.position == Vector())
    p.update(dt)
    assert(p.position == Vector(1,1,1), p)
    p.update(dt)
    assert(p.position == Vector(2,2,2), p)
    p.update(dt)
    assert(p.position == Vector(3,3,3), p)
  }
  
  "A Particle" should "update velocity correctly" in {
    var p: Particle = new Particle(Vector(0,0,0), Vector(0,0,0), Vector(1,1,1), 1, 0, 0)
    val dt: Double = 1
    p.add_force(new Force(Vector(1,1,1)))
    
    assert(p.velocity == Vector(0,0,0))
    p.update(dt)
    assert(p.velocity == Vector(1,1,1), p)
    p.update(dt)
    assert(p.velocity == Vector(2,2,2), p)
    p.update(dt)
    assert(p.velocity == Vector(3,3,3), p)
  }
  
  "A Particle" should "update acceleration correctly" in {
    var p: Particle = new Particle(Vector(0,0,0), Vector(0,0,0), Vector(1,1,1), 1, 0, 0)
    p.add_force(new Force(Vector(1,1,1)))
    val dt: Double = 1
    
    assert(p.acceleration == Vector(1,1,1))
    p.update(dt)
    assert(p.acceleration == Vector(1,1,1), p)
    p.clear_forces()
    
    p.add_force(new Force(Vector(2,2,2)))
    p.update(dt)
    assert(p.acceleration == Vector(2,2,2), p)
    p.clear_forces()
    
    p.add_force(new Force(Vector(3,3,3)))
    p.update(dt)
    assert(p.acceleration == Vector(3,3,3), p)
  }
  
  "A Particle" should "keep a list of forces applied to it" in {
    var p: Particle = new Particle(Vector(0,0,0), Vector(0,0,0), Vector(1,1,1), 1, 0, 0)
    val f1: Force = new Force(1,1,1) // replace for object version
    val f2: Force = new Force(2,2,2)
    val f3: Force = new Force(3,3,3)
    
    p add_force f1
    p add_force f2
    p add_force f3
    
    assert(p.forces == Set(f1,f2,f3), p)
  }
  
  "A Particle" should "calculate the resultant from the forces applied to it" in {
	var p1: Particle = new Particle(Vector(0,0,0), Vector(0,0,0), Vector(0,0,0), 1, 0, 0)
    val f1: Force = new Force(1,1,1) // replace for object version
    val f2: Force = new Force(2,2,2)
    val f3: Force = new Force(3,3,3)
	
	p1 add_force f1
	p1 add_force f2
	p1 add_force f3
	
    assert(p1.force_resultant == Vector(6,6,6), p1)
	
	var p2: Particle = new Particle(Vector(0,0,0), Vector(0,0,0), Vector(0,0,0), 1, 0, 0)
	
	val f4: Force = new Force(1.3,-1e6,2.56)
    val f5: Force = new Force(-2.45,2e-7,2.343)
    val f6: Force = new Force(-3,3e3,3.798)
	
	p2 add_force f4
	p2 add_force f5
	p2 add_force f6
	
	assert(p2.force_resultant == Vector(0,0,0), "") // todo
  }
  
  "A Particle" should "calculate the electric field exerted on a certain point" in {
    var p: Particle = new Particle(Vector(0,0,0), Vector(0,0,0), Vector(0,0,0), 1, 1, 0)
    val r1: Vector = Vector(1,1,1)
    val r2: Vector = Vector(1,-2,3)
    val r3: Vector = Vector(3,3,3)
    val r4: Vector = Vector(-1,-1,-1)
    val r5: Vector = Vector(0.23, 7.64, -2.34)
    val r6: Vector = Vector(1.7e10, -2.3e9, 4.1e12)
    assert(p.electric_field(r1) == Vector(0,0,0), "")
  }
  
  "A Particle" should "calculate the electric force exerted on another particle" in {
    var p1: Particle = new Particle(Vector(0,0,0), Vector(0,0,0), Vector(0,0,0), 1, 1)
    var p2: Particle = new Particle(Vector(1,1,1), Vector(0,0,0), Vector(0,0,0), 1, 1)
  }
  
  "A Particle" should "calculate the magnetic field exerted on a certain point" in {
    
  }
  
  "A Particle" should "calculate the magnetic force exerted on another particle" in {
    
  }
  
  "A Particle" should "be able to clear its list of applied forces" in {
    var p: Particle = new Particle(Vector(0,0,0), Vector(1,1,1), Vector(0,0,0), 1, 0, 0)
    val f1: Force = new Force(1,1,1) // replace for object version
    val f2: Force = new Force(2,2,2)
    val f3: Force = new Force(3,3,3)
    p add_force f1
    p add_force f2
    p add_force f3
    p.clear_forces()
    assert(p.forces == Set(), p) 
  }
  
  "A Particle" should "determine equality according to reference equality" in {
    var p1: Particle = new Particle(Vector(0,0,0), Vector(0,0,0), Vector(0,0,0), 1, 0, 0)
    var p2: Particle = new Particle(Vector(0,0,0), Vector(0,0,0), Vector(0,0,0), 1, 0, 0)
    var p1b: Particle = p1
    
    assert(p1 != p2, p1)
    assert(p1 == p1b, p1)
  }
  
  // should it???
  // also distance
  "A Particle" should "correctly determine the vector between its position and another particle's position" in {
    var p1: Particle = new Particle(Vector(1,1,1), Vector(0,0,0), Vector(0,0,0), 1, 0)
    var p2: Particle = new Particle(Vector(4,4,4), Vector(0,0,0), Vector(0,0,0), 1, 0)
    assert(p1.displacement_vector_to(p2) == Vector(3,3,3))
  }

}