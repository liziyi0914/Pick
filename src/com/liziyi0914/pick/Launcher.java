/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liziyi0914.pick;

import com.liziyi0914.jal.App;
import com.liziyi0914.jal.Application;
import com.liziyi0914.jal.ApplicationContext;

/**
 *
 * @author SEEWO
 */
@App(Name = "Pick", VersionID = 3, Version = "1.2", Git = "liziyi0914/Pick")
public class Launcher extends Application{

    @Override
    public void launch(ApplicationContext context) {
        context.updater.checkUpdate();
        Pick.main(context.args);
    }
    
}
