/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liziyi0914.pick;

/**
 *
 * @author ALW
 */
public class Config {

    /**
     * @return the speak
     */
    public boolean isSpeak() {
        return speak;
    }

    /**
     * @param speak the speak to set
     */
    public void setSpeak(boolean speak) {
        this.speak = speak;
    }
    
    private boolean expect;
    private boolean speak;

    /**
     * @return the expect
     */
    public boolean isExpect() {
        return expect;
    }

    /**
     * @param expect the expect to set
     */
    public void setExpect(boolean expect) {
        this.expect = expect;
    }

    public Config(boolean expect,boolean speak) {
        this.expect = expect;
        this.speak = speak;
    }
    
    
}
