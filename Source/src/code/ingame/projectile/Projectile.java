/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.ingame.projectile;

import code.data.DataObject;

/**
 *
 * @author Eris
 */
public class Projectile {

    protected ProjectileBehavior projectileBehavior;
    protected ImpactBehavior impactBehavior;
    protected DataObject dataObject;

    public Projectile(ProjectileBehavior projectileBehavior, ImpactBehavior impactBehavior, DataObject dataObject) {
        this.projectileBehavior = projectileBehavior;
        this.impactBehavior = impactBehavior;
        this.dataObject = dataObject;
    }

}
