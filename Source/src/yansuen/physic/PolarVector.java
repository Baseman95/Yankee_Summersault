/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yansuen.physic;

/**
 *
 * @author Link
 */
public class PolarVector implements Vector {

    public double angle;
    public float length;

    public PolarVector(double angle, float length) {
        this.angle = angle;
        this.length = length;
    }

    public PolarVector(PolarVector vector) {
        this.angle = vector.angle;
        this.length = vector.length;
    }

    public PolarVector(CartesianVector vector) {
        this.angle = CartesianVector.angleFromCartesian(vector);
        this.length = CartesianVector.lengthFromCartesian(vector);
    }

    public CartesianVector toCartesianVector() {
        return new CartesianVector(this);
    }

    @Override
    public void left() {
        angle -= Math.PI;
    }

    @Override
    public void right() {
        angle += Math.PI;
    }

    @Override
    public void invert() {
        angle = -angle;
    }

    @Override
    public void addVector(Vector vector) {
        if (vector instanceof CartesianVector) {
            CartesianVector v = (CartesianVector) vector;
            v.addVector(new CartesianVector(this));
            this.angle = CartesianVector.angleFromCartesian(v);
            this.length = CartesianVector.lengthFromCartesian(v);
        }
        if (vector instanceof PolarVector) {
            CartesianVector v = new CartesianVector((PolarVector) vector);
            v.addVector(new CartesianVector(this));
            this.angle = CartesianVector.angleFromCartesian(v);
            this.length = CartesianVector.lengthFromCartesian(v);
        }
    }

    @Override
    public PolarVector getUnitVector() {
        return new PolarVector(angle, 1);
    }

    public void updateAngleRange2Pi() {
        angle = angle % (Math.PI * 2);
        if (angle < 0)
            angle += Math.PI * 2;
    }

    public void updateAngleRangePi() {
        angle = angle % (Math.PI * 2);
        if (angle > Math.PI)
            angle -= Math.PI * 2;

        if (angle < -Math.PI)
            angle += Math.PI * 2;
    }

    public static float xFromPolar(PolarVector vector) {
        return (float) (Math.cos(vector.angle) * vector.length);
    }

    public static float yFromPolar(PolarVector vector) {
        return (float) (Math.sin(vector.angle) * vector.length);
    }
}
