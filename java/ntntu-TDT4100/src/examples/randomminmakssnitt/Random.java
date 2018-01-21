package examples.randomminmakssnitt;

public class Random {
	
	private long mW = 11;    /* must not be zero, nor 0x464fffff */
	private long mZ = 42;    /* must not be zero, nor 0x9068ffff */
	 
	public long getRandom() {
	    mZ = 36969 * (mZ & 65535) + (mZ >> 16);
	    mW = 18000 * (mW & 65535) + (mW >> 16);
	    return (mZ << 16) + mW;  /* 32-bit result */
	}
	public long getMW() {
		return mW;
	}
	public long getMZ() {
		return mZ;
	}
}
