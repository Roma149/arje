package roma.arje.particles

import roma.arje.math._
import roma.arje._

class Neutron(position: Vector, velocity: Vector, acceleration: Vector) extends Particle(position, velocity, acceleration, 0, Constants.NEUTRON_MASS_kg, 0.5) {}