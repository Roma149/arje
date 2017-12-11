package roma.arje

import roma.arje.math._
import roma.arje.particles._

class Force(val force_vector: Vector, val force_type: Symbol, val originator: Particle = null) {
  // create "physical element" trait which to add to things that can be originators
  
  require(List('electric, 'magnetic, 'electromagnetic, 'gravitational, 'weak, 'strong, 'mixed', 'none').contains(force_type), "invalid force type")
    
  def this() = this(Vector(0,0,0), 'none, null)
  def this(v: Vector) = this(v, 'none, null)
  def this(x: Double, y: Double, z: Double) = this(Vector(x,y,z), 'none, null)
  
  def +(f: Force): Force = Force(this.force_vector + f.force_vector, combine_force_types(this.force_type, f.force_type), combine_force_originators(this.originator, f.originator))
  
  def -(f: Force): Force = Force(this.force_vector - f.force_vector, combine_force_types(this.force_type, f.force_type), combine_force_originators(this.originator, f.originator))
  
  def combine_force_types(s1: Symbol, s2: Symbol): Symbol = {
    if (s1 == s2)
      s1
    else if ((s1 == 'electric && s2 == 'magnetic) || (s1 == 'magnetic && s2 == 'electric))
      'electromagnetic
    else
      'mixed
  }
  
  def combine_force_originators(p1: Particle, p2: Particle): Particle = if (p1 == p2) p1 else null
}

object Force {
  
  def apply(v: Vector, s: Symbol, o: Particle) = new Force(v,s,o)
  
}