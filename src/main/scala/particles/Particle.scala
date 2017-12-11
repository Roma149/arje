package roma.arje.particles

import roma.arje.math._
import roma.arje.Constants
import roma.arje.Force
import scala.collection.mutable.Set // change for immutable

class Particle(var position: Vector, var velocity: Vector, var acceleration: Vector, var mass: Double = 1, val charge: Double = 0, val spin: Double = 0) {

  var forces: Set[Force] = Set()
  
  def this() = this(Vector(0,0,0), Vector(0,0,0), Vector(0,0,0))
  
  override def toString = "P:" + position + " V:" + velocity + " A:" + acceleration + " M:" + mass + " C:" + charge + " S:" + spin
  
  override def equals(other: Any) = other match {
    case that: Particle =>
      this eq that
    case _ =>
    	false
  }
  
  override def hashCode = super.hashCode
  
  def distance_to_origin = position.magnitude
  def speed = velocity.magnitude
  def scalar_acceleration = acceleration.magnitude
  
  def update(dt: Double) = {
    acceleration = force_resultant / mass // F = m*a => a = F/m
    velocity = velocity + acceleration * dt
    position = position + velocity * dt
  }
  
  def update_with_fixed_acc(dt: Double) = {
    velocity = velocity + acceleration * dt
    position = position + velocity * dt
  }
  
  def distance_to(p: Particle) = position.to(p.position).magnitude
  def displacement_vector_to(p: Particle) = position.to(p.position) // another name?
  
  def force_resultant(): Vector = {
    var sum = Vector(0,0,0)
    for (force <- forces)
      sum = sum + force.force_vector // implement += and change for that
    return sum
  }
  
  def add_force(f: Force) = forces += f
  
  def clear_forces() = forces.clear()
  def show_forces() = forces.clone()
  
  def is_moving() = velocity.is_not_null
  def is_accelerating() = acceleration.is_not_null
  
  // Coulomb's law
  def electric_field(r: Vector): Vector = r*(Constants.COULOMBS_CONSTANT_Nm2C2*charge/Math.pow(r.magnitude, 3))
  def electric_force_on_charge(r: Vector, c: Double): Vector = electric_field(r) * c
  def electric_force_on_particle(p: Particle): Force = Force(electric_field(position.to(p.position)) * p.charge, 'electric, this)
  def electric_force_on(p: Particle): Force = electric_force_on_particle(p) // alias
  
  // B = mu/4*pi * q*VxR / r^3
  def magnetic_field(r: Vector): Vector = velocity.cross_product(r) * (1e-7 * charge / Math.pow(r.magnitude, 3))
  def magnetic_force_on_charge(r: Vector, v: Vector, c: Double): Vector = (v * c) cross_product magnetic_field(r)
  def magnetic_force_on_particle(p: Particle): Force = Force((p.velocity * p.charge) cross_product magnetic_field(position.to(p.position)),'magnetic , this) // it could call magnetic_foce_on_charge but it seems to be unnecessary overhead given that it is a very simple method
  def magnetic_force_on(p: Particle): Force = magnetic_force_on_particle(p) // alias
  
  def electromagnetic_force_on(p: Particle): Force = electric_force_on(p) + magnetic_force_on(p)
  
  def gravitational_force_on(p: Particle): Force = {
    val f = (Constants.G*this.mass*p.mass)/Math.pow(this.distance_to(p),2) // precalculate the displacement vector
    val v_f = p.displacement_vector_to(this).to_magnitude(f)
    Force(v_f, 'gravitational, this)
  }
    
  
  def snapshot(): Particle = new Particle() // TODO: return an immutable copy
}