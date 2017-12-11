package roma.arje.particles

import roma.arje.math.Vector
import roma.arje.Constants

class Electron(position: Vector, velocity: Vector, acceleration: Vector) extends Particle(position, velocity, acceleration, -Constants.ELEMENTARY_CHARGE_C, Constants.ELECTRON_MASS_kg, 0.5) {}
