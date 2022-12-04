package com.goit.feature.cli;

import com.goit.feature.HttpStatusImageDownloader;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Scanner;


@RequiredArgsConstructor
public class HttpImageStatusCli {
    private final Scanner scanner;
    private boolean isCorrectNumberFormat = true;

    public void askStatus(){
        if (isCorrectNumberFormat) {
            System.out.print("Enter HTTP status code: ");
        }

        String s = scanner.nextLine();

        if (isNumeric(s)) {
            int code = Integer.parseInt(s);

            HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
            try {
                downloader.downloadStatusImage(code);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            isCorrectNumberFormat = true;
        };

        askStatus();
    }

    private boolean isNumeric(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.print("Please enter valid number: ");
            isCorrectNumberFormat = false;
            return false;
        }
    }
}
