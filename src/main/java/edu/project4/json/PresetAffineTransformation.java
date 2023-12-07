package edu.project4.json;

public class PresetAffineTransformation {
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;
    private int red;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getE() {
        return e;
    }

    public void setE(double e) {
        this.e = e;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    private int green;
    private int blue;

    @Override
    public String toString() {
        return "PresetAffineTransformation{"
                + "a=" + a
                + ", b=" + b
                + ", c=" + c
                + ", d=" + d
                + ", e=" + e
                + ", f=" + f
                + ", red=" + red
                + ", green=" + green
                + ", blue=" + blue
                + '}';
    }
}
