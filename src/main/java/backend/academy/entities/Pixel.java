package backend.academy.entities;

public class Pixel {
    private int r;
    private int g;
    private int b;
    private int hitCount;
    private double normal;

    public Pixel() {
        this.r = 0;
        this.g = 0;
        this.b = 0;
        this.hitCount = 0;
        this.normal = 0;
    }

    public void setColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void setCorrectedColor(int r, int g, int b) {
        this.r = (this.r + r) / 2;
        this.g = (this.g + g) / 2;
        this.b = (this.b + b) / 2;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public double getNormal() {
        return normal;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setB(int b) {
        this.b = b;
    }
}
