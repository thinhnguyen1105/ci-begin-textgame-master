package bases;

/**
 * Created by huynq on 7/16/17.
 */
public class Vector2D {
    public float x;
    public float y;

    public static final Vector2D ZERO = new Vector2D(0,0);

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        this(0, 0);
    }

    public void addUp(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void addUp(Vector2D other) {
        addUp(other.x, other.y);
    }

    public Vector2D add(float x, float y) {
        return new Vector2D(this.x + x, this.y + y);
    }

    public Vector2D add(Vector2D other) {
        return add(other.x, other.y);
    }

    public Vector2D subtract(float x, float y) {
        return new Vector2D(this.x - x, this.y - y);
    }

    public Vector2D subtract(Vector2D other) {
        return subtract(other.x, other.y);
    }

    public Vector2D normalize() {
        float length = (float) Math.sqrt(x * x + y * y);
        return new Vector2D(x  / length, y / length);
    }

    public Vector2D multiply(float f) {
        return new Vector2D(x * f, y * f);
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2D other) {
        set(other.x, other.y);
    }

    public Vector2D clone() {
        return new Vector2D(this.x, this.y);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
