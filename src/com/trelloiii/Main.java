package com.trelloiii;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        if(args.length==3) {
            if (args[2].equals("-stored")) {
                List<KeyVal> values = parseFile();
                play(args[0], values);
            } else {
                play(args[0], args[1]);
            }
        }
        else {
            play(args[0], args[1]);
        }
    }

    private static List<KeyVal> parseFile() throws IOException {
        File coords=new File("/Users/trelloiii/Desktop/JavaProjects/console-game-of-life/coords.txt");
        FileReader reader=new FileReader(coords);
        StringBuilder builder=new StringBuilder();
        int c;
        while((c=reader.read())!=-1){
            char a=(char) c;
            builder.append(a);
        }
        String[] coordsStr=builder.toString().split(";");
        List<KeyVal> res=new ArrayList<>();
        for(String coord:coordsStr){
            System.out.println(coord);
            String[] sp=coord.split(",");
            res.add(new KeyVal(Integer.parseInt(sp[0]),Integer.parseInt(sp[1])));
        }
        return res;
    }

    public static void play(String size,String bound) throws InterruptedException, IOException {
        GridNode gridNode = new GridNode(Integer.parseInt(size),Integer.parseInt(bound));
        while (true) {
            try {
                gridNode.print();
                Thread.sleep(150);
                gridNode.newGeneration();
                clear();
            }
            catch (Exception e){
                System.err.println(e.getMessage());
                break;
            }
        }
    }
    public static void play(String size, List<KeyVal> values) throws InterruptedException, IOException {
        GridNode gridNode = new GridNode(Integer.parseInt(size),values);
        while (true) {
            try {
                gridNode.print();
                Thread.sleep(150);
                gridNode.newGeneration();
                clear();
            }
            catch (Exception e){
                System.err.println(e.getMessage());
                break;
            }
        }
    }
    private static void clear() throws IOException {
        for(int i=0;i<25;i++){
            System.out.println();
        }
//        Runtime.getRuntime().exec("pwd");
    }
}
