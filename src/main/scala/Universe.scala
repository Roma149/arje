package roma.arje

import roma.arje.particles._
import scala.collection.mutable.Set

class Universe {
  
  var particles: Set[Particle] = Set()
  
  var electric_force_active: Boolean = true
  var magnetic_force_active: Boolean = true
  var gravitational_force_active:Boolean = false
  // strong, weak
  
  def set_electric_force_to(b: Boolean) = electric_force_active = b
  def set_magnetic_force_to(b: Boolean) = magnetic_force_active = b
  def set_gravitational_force_to(b: Boolean) = gravitational_force_active = b
  
  def add(p: Particle) = particles += p
  
  def +=(p: Particle) = particles += p
  
  def remove(p: Particle) = particles -= p
  
  def update_all(dt: Double) {
    for (particle <- particles) {
      particle.update(dt)
    }
  }
 
  def add_all_forces(dt: Double) { // or separate by force?
    for (particle1 <- particles) { // force on particle1
      for (particle2 <- particles) { // due to particle2
        if (particle1 != particle2) {
        	if (electric_force_active)
        	  particle1 add_force particle2.electromagnetic_force_on(particle1)
        	if (magnetic_force_active)
        	  particle1 add_force particle2.electromagnetic_force_on(particle1)
        	if (gravitational_force_active)
        	  particle1 add_force particle2.gravitational_force_on(particle1)
        }
      }
    }
  }
}