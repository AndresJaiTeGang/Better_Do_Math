/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

public class AdaptativeEngineService {

    public int getRange(double score) {

        if(score >= 90)
            return 100;

        if(score >= 70)
            return 50;

        return 10;
    }
}
