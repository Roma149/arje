package roma.arje.particles

import roma.arje.math._
import roma.arje._

class Proton(position: Vector, velocity: Vector, acceleration: Vector) extends Particle(position, velocity, acceleration, Constants.ELEMENTARY_CHARGE_C, Constants.PROTON_MASS_kg, 0.5) {}