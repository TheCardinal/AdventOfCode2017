package adventofcode.helpers;

public class Particle
{
	private Coordinate position, velocity, acceleration;

	public Particle(Coordinate position, Coordinate velocity, Coordinate acceleration)
	{
		this.position = position;
		this.velocity = velocity;
		this.acceleration = acceleration;
	}

	public void takeStep()
	{
		velocity.move(acceleration);
		position.move(velocity);
	}

	public void printPosition()
	{
		position.printLocationAndDistance();
	}

	public long getManhattanDistance()
	{
		return position.getManhattanDistance();
	}

	public boolean atSamePosition(Particle p)
	{
		return position.isEqualTo(p.position);
	}

	@Override
	public String toString()
	{
		return position.toString();
	}
}
