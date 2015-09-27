
public class ONode 
{
	private float[] weight;
	private float output;
	
		/**
	 *	An activation function using a sigmoid function. 
	 *
	 */
	public float sigActivate(){
		float e = (float) Math.E;
        float t = (float) Math.pow(e, -output);
        float activate = 1 / (1 + t);
		return activate;
	}
	
	/**
	 *  An activation function using a hyperbolic tangent.
	 */
	public float tanhActivate(){
        float activate = (float) Math.tanh(output);
        return activate;
    }
}
