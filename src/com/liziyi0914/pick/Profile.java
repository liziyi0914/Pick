/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liziyi0914.pick;

import com.google.gson.Gson;
import com.liziyi0914.pio.PacketIn;
import com.liziyi0914.pio.PacketOut;
import com.liziyi0914.pio.types.BytesType;
import com.liziyi0914.pio.types.StringType;
import com.liziyi0914.pio.types.VarintType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author liziy
 */
public class Profile {

    File profile;
    private Config config;
    private byte[] voiceData;
    Gson gson = new Gson();
    private Student[] students;

    StringType stringType = new StringType();
    VarintType varintType = new VarintType();
    BytesType bytesType = new BytesType();

    public Profile(File profile) {
        this.profile = profile;
        try {
            PacketIn in = new PacketIn(Files.readAllBytes(this.profile.toPath()));
            config = gson.fromJson((String) in.load(stringType.in()), Config.class);
            students = gson.fromJson((String) in.load(stringType.in()), Student[].class);
            int len = ((BigInteger) in.load(varintType.in())).intValue();
            voiceData = (byte[]) in.load(bytesType.in(len));
        } catch (IOException ex) {
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void save() {
        try {
            PacketOut out = new PacketOut();
            out.write(stringType.out(gson.toJson(config)));
            out.write(stringType.out(gson.toJson(students)));
            out.write(varintType.out(BigInteger.valueOf(voiceData.length)));
            out.write(bytesType.out(voiceData));
            new FileOutputStream(profile).write(out.finish());
        } catch (IOException ex) {
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the config
     */
    public Config getConfig() {
        return config;
    }

    /**
     * @param config the config to set
     */
    public void setConfig(Config config) {
        this.config = config;
    }

    /**
     * @return the voiceData
     */
    public byte[] getVoiceData() {
        return voiceData;
    }

    /**
     * @param voiceData the voiceData to set
     */
    public void setVoiceData(byte[] voiceData) {
        this.voiceData = voiceData;
    }

    /**
     * @return the students
     */
    public Student[] getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(Student[] students) {
        this.students = students;
    }

}
