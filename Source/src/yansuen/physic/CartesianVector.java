/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yansuen.physic;

import java.awt.geom.Point2D;

/**
 *
 * @author Link
 */
public class CartesianVector implements Vector {

    public float x;
    public float y;

    public CartesianVector(double x, double y) {
        this.x = (float) x;
        this.y = (float) y;
    }

    public CartesianVector(Point2D.Float point) {
        this.x = point.x;
        this.y = point.y;
    }

    public CartesianVector(CartesianVector vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public CartesianVector(PolarVector vector) {
        this.x = PolarVector.xFromPolar(vector);
        this.y = PolarVector.yFromPolar(vector);
    }

    public PolarVector toPolarVector() {
        return new PolarVector(this);
    }

    @Override
    public void left() {
        float b = x;
        x = y;
        y = -b;
    }

    @Override
    public void right() {
        float b = x;
        x = -y;
        y = b;
    }

    @Override
    public void invert() {
        x = -x;
        y = -y;
    }

    @Override
    public void addVector(Vector vector) {
        if (vector instanceof CartesianVector) {
            CartesianVector v = (CartesianVector) vector;
            this.x += v.x;
            this.y += v.y;
        }
        if (vector instanceof PolarVector) {
            CartesianVector v = new CartesianVector((PolarVector) vector);
            v.addVector(new CartesianVector(this));
            this.x += v.x;
            this.y += v.y;
        }
    }

    @Override
    public PolarVector getUnitVector() {
        return new PolarVector(CartesianVector.angleFromCartesian(this), 1);
    }

    public Point2D.Float toPoint2D() {
        return new Point2D.Float(x, y);
    }

    public static double angleFromCartesian(CartesianVector vector) {
        return Math.atan2(vector.y, vector.x);
    }

    public static float lengthFromCartesian(CartesianVector vector) {
        return (float) Math.sqrt(vector.x * vector.x + vector.y * vector.y);
    }

}
