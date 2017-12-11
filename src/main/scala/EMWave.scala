package roma.arje

import roma.arje.particles._

// in the frame when it is created it will also travel c*dt, so if dt is large enough and the particles are close together enough
// having it act instantaneously may be a good enough approximation, which would improve efficiency
// also when the second iteration would be so far away as to have a negligible effect on the particles it affects

class EMWave(val originator: Particle) { // particles are mutable, so I would need a snapshot of the particle as it was when it originated the field (could make that one immutable)

  var r: Double = 0 // distance from center expanded until now
  
  def update(dt: Double) = r += Constants.C_ms*dt // precalculate c*dt
  
}