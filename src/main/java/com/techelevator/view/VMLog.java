package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class VMLog {
    private static PrintWriter printWriter;


    public static void log(String message){

        File tester = new File("log.txt");

        try {
            if(printWriter == null){
                printWriter = new PrintWriter(new FileOutputStream(tester));
            }
            LocalDateTime localDateTime = LocalDateTime.now();

            printWriter.println(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(localDateTime) + "  " + message );


        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        printWriter.flush();
    }
}