
public class PolarComplex implements IComplex {

	double r;
	double theta;
	
	PolarComplex( double r, double theta )
	{
		this.r = r;
		this.theta = theta;
	}
	@Override
	public double Abs() {
		return this.r;
	}

	@Override
	public double Arg() {
		return this.theta;
	}

	@Override
	public double Im() {
		return this.r * Math.sin(theta);
	}

	@Override
	public double Re() {
		return this.r * Math.cos(theta);
	}

	@Override
	public IComplex conj() {
		return new PolarComplex( r, -theta );
		
	}

	@Override
	public IComplex exp() {
		return new PolarComplex( Math.exp( this.Re()), this.Im() );
	}

	@Override
	public IComplex log() {
		return new Complex( Math.log(this.r), theta );
	}

	@Override
	public IComplex rotate(double radians) {
		return new PolarComplex( r, theta + radians );
	}

	@Override
	public IComplex stretch(double scale) {
		return new PolarComplex( r*scale, theta);
	}
	@Override
	public int compareTo(IComplex complex) {
		if( this.Abs() == complex.Abs()) return 0;
		else if( this.Abs() > complex.Abs()) return 1;
		else return -1;
	}

}
