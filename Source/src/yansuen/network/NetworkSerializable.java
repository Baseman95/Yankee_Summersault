/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yansuen.network;

/**
 *
 * @author Link
 */
public interface NetworkSerializable {

    String[] networkSerialize();
    
    int networkSerializeArgumentCount();

    void networkDeserialize(String[] args);

    public static String[] generateArguments(Object... parameter) {
        String args[] = new String[parameter.length];
        for (int i = 0; i < parameter.length; i++) {
            args[i] = String.valueOf(parameter[i]);
        }
        return args;
    }
}
