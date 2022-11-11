/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bugallolugomizrahi;

/**
 *
 * @author davidmizrahi
 */
public class Randomizer {
   
    public static int generate(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}

