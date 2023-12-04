package de.traumastudios.infernal.core.math;

public class MersenneTwister {
    private static final int N = 624;
    private static final int M = 397;
    private static final int MATRIX_A = 0x9908b0df;
    private static final int UPPER_MASK = 0x80000000;
    private static final int LOWER_MASK = 0x7fffffff;

    private final int[] mt;
    private int index;

    public MersenneTwister() {
        this(System.currentTimeMillis());
    }

    public MersenneTwister(long seed) {
        mt = new int[N];
        index = N + 1;
        initialize(seed);
    }

    private void initialize(long seed) {
        mt[0] = (int) seed;
        for (int i = 1; i < N; i++) {
            mt[i] = (int) (0xffffffffL & (1812433253 * (mt[i - 1] ^ (mt[i - 1] >>> 30)) + i));
        }
    }

    public void setSeed(long seed) {
        initialize(seed);
    }

    public int nextInt() {
        if (index >= N) {
            twist();
        }

        int y = mt[index++];
        y ^= (y >>> 11);
        y ^= ((y << 7) & 0x9d2c5680);
        y ^= ((y << 15) & 0xefc60000);
        y ^= (y >>> 18);

        return y;
    }

    public float nextFloat() {
        return (nextInt() >>> 8) / ((float) (1 << 24));
    }

    public double nextDouble() {
        long high = ((long) nextInt() << 26) << 27;
        long low = nextInt() >>> 5;
        return (high | low) / (double) (1L << 53);
    }

    public long nextLong() {
        return ((long) nextInt() << 32) | (nextInt() & 0xFFFFFFFFL);
    }

    public int[] nextInts(int length) {
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = nextInt();
        }
        return result;
    }

    public float[] nextFloats(int length) {
        float[] result = new float[length];
        for (int i = 0; i < length; i++) {
            result[i] = nextFloat();
        }
        return result;
    }

    public double[] nextDoubles(int length) {
        double[] result = new double[length];
        for (int i = 0; i < length; i++) {
            result[i] = nextDouble();
        }
        return result;
    }

    private void twist() {
        for (int i = 0; i < N; i++) {
            int x = (mt[i] & UPPER_MASK) | (mt[(i + 1) % N] & LOWER_MASK);
            mt[i] = mt[(i + M) % N] ^ (x >>> 1) ^ ((x & 1) == 0 ? 0 : MATRIX_A);
        }
        index = 0;
    }
}
