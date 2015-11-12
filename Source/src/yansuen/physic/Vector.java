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
public interface Vector {

    public void addVector(Vector vector);

    public void left();

    public void right();

    public void invert();

    public PolarVector getUnitVector();

}
