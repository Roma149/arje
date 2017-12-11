package roma.arje.math

class Vector(val x: Double, val y: Double, val z: Double,) {
  
	def this() = this(0,0,0)
	def this(a: Double) = this(a,a,a)
	def this(v: Vector) = this(v.x, v.y, v.z)
  
	override def toString = { "(" + x + "," + y + "," + z + ")" }

	override def equals(other: Any) = other match {
	  case that: Vector =>
	    this.x == that.x && this.y == that.y && this.z == that.z
	  case _ =>
	    false
	}
	
	def +(v: Vector): Vector = Vector(x+v.x, y+v.y, z+v.z)
	def -(v: Vector): Vector = Vector(x-v.x, y-v.y, z-v.z)
	
	def *(a: Double): Vector = Vector(a*x, a*y, a*z) // scalar multiplication
	def *(v: Vector): Double = x*v.x + y*v.y + z*v.z // dot product
	def dot_product(v: Vector): Double = x*v.x + y*v.y + z*v.z // alias? use this when ambiguous
	def scalar_multiplication(a: Double): Vector = Vector(a*x, a*y, a*z) // alias? use when ambiguous
	
	def /(a: Double): Vector = Vector(x/a, y/a, z/a) // scalar division
	
	def cross_product(v: Vector): Vector = Vector(y*v.z-z*v.y, z*v.x-x*v.z, x*v.y-y*v.x) // cross product, change name to symbol
	
	def magnitude(): Double = Math.sqrt(x*x+y*y+z*z)
	
	// get a new vector with the same direction and a specified magnitude
	def to_magnitude(new_magnitude: Double): Vector = { // find better way to do this
	  val ratio = new_magnitude/this.magnitude
	  Vector(ratio*x, ratio*y, ratio*z)
	}
	
	def toUnit() = {
	  val mag = this.magnitude()
	  Vector(x/mag, y/mag, z/mag)
	}
	
	def to(v: Vector) = v - this // get the vector from this one to another one (maybe call vector_to?)
	def from(v: Vector) = this - v // get the vector from another one to this one
	  
	def invert() = new Vector(-x, -y, -z)
	
	def is_null(): Boolean = x == 0 && y == 0 && z == 0
	def is_not_null(): Boolean = x != 0 || y != 0 || z != 0
	
	def to_cartesian(from_system: String): Vector = {
	  if (from_system == "cartesian")
	    this
	  else if (from_system == "spherical")
	    Vector(x*Math.sin(y)*Math.cos(z), x*Math.sin(y)*Math.sin(z), x*Math.cos(y)) // spherical to cartesian
	  else if (from_system == "cylindrical")
	    Vector(x*Math.cos(y), x*Math.sin(y), z) // cylindrical to cartesian
	  else
	    // TODO: error
	    Vector()
	}
	
	def to_spherical(from_system: String): Vector = {
	  if (coordinate_system == 'cartesian) // see if can remove {}
	    Vector(this.magnitude, Math.acos(z/this.magnitude), Math.atan2(y,x)) // cartesian to spherical
	  else if (coordinate_system == 'spherical)
	    this
	  else if (coordinate_system == 'cylindrical) {
	    val r = Math.sqrt(x*x+z*z)
	    Vector(r, Math.acos(z/r), z) // cylindrical to spherical
	  } else {
	    // TODO: error
	    Vector()
	  }
	}
	
	def to_cylindrical(from_system: String): Vector = {
	  if (from_system == "cartesian") { // cartesian to cylindrical
	    val p = Math.sqrt(x*x+y*y)
	    val phi = if (x == 0 && y == 0)
	    			0
	    		  else if (x >= 0)
	    		    Math.asin(y/p)
	    		  else // if (x < 0)
	    		    -Math.asin(y/p)+Math.PI
	    Vector(p, phi, z)
	  } else if (from_system == "spherical")
	    Vector(x*Math.sin(y), z, x*Math.cos(y)) // spherical to cylindrical
	  else if (from_system == "cylindrical")
	    this
	  else
	  	// TODO: error
	  	Vector()
	}
}

object Vector {
  
  val null_vector: Vector = Vector(0,0,0)
  
  def apply() = new Vector()
  def apply(x: Double, y: Double, z: Double) = new Vector(x,y,z)
  
}