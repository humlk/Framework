package com.sai.base.tools.adb;

import java.io.IOException;

public class ShellCmd {

//    public static InputStream run(String program){
//
//        try {
//            Process process = Runtime.getRuntime().exec(program);
//            if(process == null){
//                return null;
//            }
//            //读取执行结果
//            return process.getInputStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static Process run(String ... commands){

        ProcessBuilder pb = new ProcessBuilder();
        pb.command(commands);

        try {
//            Process process = pb.start();
//            process.destroy();
//            if(process != null){
//                return process.getInputStream();
//            }
            return pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
