
public class Complex implements IComplex {

	Complex( double x, double y )
	{
		this.x = x;
		this.y = y;
	}
	
	private double x;
	private double y;
	@Override
	public double Abs() 
	{
		return Math.sqrt(x*x + y*y);
	}

	@Override
	public double Arg() {
		return Math.atan2( this.Im(), this.Re());
	}

	@Override
	public double Im() {
		return this.y;
	}

	@Override
	public double Re() {
		return this.x;
	}

	@Override
	public IComplex conj() {
		return new Complex( x, -y );
	}

	@Override
	public IComplex exp() {
		double a = Math.exp(x) * Math.cos(y);
		double b = Math.exp(x) * Math.sin(y);
		return new Complex(a, b);
	}

	@Override
	public IComplex log() {
		return new Complex(Math.log(this.Abs()), this.Arg());
	}

	@Override
	public IComplex rotate(double radians) {
		double a = this.Abs() * Math.cos((this.Arg() + radians));
		double b = this.Abs() * Math.sin(this.Arg() + radians);
		return new Complex( a, b );
	}

	@Override
	public IComplex stretch(double scale) {
		return new PolarComplex( scale * this.Abs(), this.Arg());
	}
	@Override
	public int compareTo(IComplex complex) {
		if( this.Abs() == complex.Abs()) return 0;
		else if( this.Abs() > complex.Abs()) return 1;
		else return -1;
	}

	

}
