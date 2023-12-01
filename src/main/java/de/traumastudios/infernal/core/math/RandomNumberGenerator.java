package de.traumastudios.infernal.core.math;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RandomNumberGenerator {
    private long seed;

    public RandomNumberGenerator() {
        this(System.nanoTime());
    }

    public RandomNumberGenerator(long seed) {
        this.seed = seed;
    }

    private long xorshift64() {
        seed ^= (seed << 21);
        seed ^= (seed >>> 35);
        seed ^= (seed << 4);
        return seed;
    }

    public int nextIntXorshift() {
        return (int) xorshift64();
    }

    public float nextFloatXorshift() {
        return (float) xorshift64() / (1L << 32);
    }

    public double nextDoubleXorshift() {
        return xorshift64() / (double) (1L << 63);
    }

    public long nextLongXorshift() {
        return xorshift64();
    }

    public int nextIntMathRandom() {
        return (int) (Math.random() * Integer.MAX_VALUE);
    }

    public float nextFloatMathRandom() {
        return (float) Math.random();
    }

    public double nextDoubleMathRandom() {
        return Math.random();
    }

    public long nextLongMathRandom() {
        return (long) (Math.random() * Long.MAX_VALUE);
    }

    public int[] nextIntsXorshift(int length) {
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = nextIntXorshift();
        }
        return result;
    }

    public float[] nextFloatsXorshift(int length) {
        float[] result = new float[length];
        for (int i = 0; i < length; i++) {
            result[i] = nextFloatXorshift();
        }
        return result;
    }

    public double[] nextDoublesXorshift(int length) {
        double[] result = new double[length];
        for (int i = 0; i < length; i++) {
            result[i] = nextDoubleXorshift();
        }
        return result;
    }

    public int[] nextIntsMathRandom(int length) {
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = nextIntMathRandom();
        }
        return result;
    }

    public float[] nextFloatsMathRandom(int length) {
        float[] result = new float[length];
        for (int i = 0; i < length; i++) {
            result[i] = nextFloatMathRandom();
        }
        return result;
    }

    public double[] nextDoublesMathRandom(int length) {
        double[] result = new double[length];
        for (int i = 0; i < length; i++) {
            result[i] = nextDoubleMathRandom();
        }
        return result;
    }
}
