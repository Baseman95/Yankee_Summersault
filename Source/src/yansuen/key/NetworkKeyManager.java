/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yansuen.key;

/**
 *
 * @author Link
 */
public class NetworkKeyManager extends LocalKeyManager {

    public void forcePressKey(Integer key) {
        if (keyList.contains(key))
            return;
        keyList.add(key);
        checkShortcuts(key);
        //System.out.println(key);
    }

    public void forceReleaseKey(Integer key) {
        if (!keyList.contains(key))
            return;
        keyList.remove(key);
    }
}
