package com.company.math;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Vector2D {
    public float x;
    public float y;
    private static final Random random = ThreadLocalRandom.current();

    public static final Vector2D ZERO = new Vector2D(0f, 0f);

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(float xy) {
        this(xy, xy);
    }

    public Vector2D(Vector2D vec) {
        x = vec.x;
        y = vec.y;
    }

    public Vector2D() {
        this(0f, 0f);
    }

    public Vector2D add(Vector2D v) {
        return new Vector2D(x + v.x, y + v.y);
    }
    public Vector2D subtract(Vector2D v) {
        return new Vector2D(x - v.x, y - v.y);
    }
    public Vector2D multiply(float a) {
        return new Vector2D(x * a, y * a);
    }
    public Vector2D multiply(double a) {
        return new Vector2D((float) (x * a), (float) (y * a));
    }

    public boolean isZero() {
        return x == 0 && y == 0;
    }

    public float magnitude() {
        return (float) Math.sqrt((x * x) + (y * y));
    }

    public void limit(float newMagnitude) {
        float mag = magnitude();
        if (mag != 0) {
            if (mag > newMagnitude) {
                normalize();
                x *= newMagnitude;
                y *= newMagnitude;
            }
        }
    }

    public Vector2D limited(float magnitude) {
        float mag = magnitude();
        if (mag != 0) {
            if (mag > magnitude) {
                return this.normalized().multiply(magnitude);
            } else {
                return new Vector2D(this);
            }
        }
        return Vector2D.ZERO;
    }

    public void normalize(){
        float mag = magnitude();
        if (mag != 0) {
            x /= mag;
            y /= mag;
        }
        else throw new ArithmeticException("Magnitude is 0");
    }
    public Vector2D normalized() {
        float mag = magnitude();
        if (mag != 0) {
            return new Vector2D(x / mag, y / mag);
        }
        else throw new ArithmeticException("Magnitude is 0");
    }
    public float dot(Vector2D v) {
        return x * v.x + y * v.y;
    }

    public float cross(Vector2D v) {
        return (x * v.y - y * v.x);
    }



    public Vector2D rotated(Vector2D point, float angle) {
        float dx = x - point.x;
        float dy = y - point.y;
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);
        return new Vector2D(
                point.x + (dx * cos - dy * sin),
                point.y + (dx * sin + dy * cos)
        );
    }

    public static float distance(Vector2D v1, Vector2D v2) {
        return (float) Math.sqrt((v1.x - v2.x) * (v1.x - v2.x) + (v1.y - v2.y) * (v1.y - v2.y));
    }

    public Vector2D divide(float a) {
        if (a == 0) {
            throw new ArithmeticException("Divisor is 0");
        }
        return new Vector2D(x / a, y / a);
    }
    public static float randomFloat(float min, float max) {
        return random.nextFloat() * (max - min) + min;
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
