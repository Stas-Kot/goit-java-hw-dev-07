package com.goit.feature.cli;

import com.goit.feature.HttpStatusChecker;
import com.goit.feature.HttpStatusImageDownloader;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class HttpImageStatusCli {
    private final Scanner scanner;

    public void askStatus() {
        System.out.print("Enter HTTP status code: ");
        askStatusService();
    }

    private void askStatusService() {
        int code;
        String s = scanner.nextLine();

        if(isNumeric(s)) {
            code = Integer.parseInt(s);

            try {
                HttpStatusChecker checker = new HttpStatusChecker();
                int statusCode = checker.getStringHttpResponse(code).statusCode();
                if (statusCode != 404) {
                    HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
                    downloader.downloadStatusImage(code);
                } else {
                    System.err.println("There is not image for HTTP status " + code);
                }

            } catch (Exception ex) {
                System.out.println("Please enter valid number: ");
            }

            scanner.close();
        } else {
            System.out.print("Please enter valid number: ");
            askStatusService();
        }
    }

    private boolean isNumeric(String string) {
        if(string == null || string.equals("")) {
            return false;
        }

        try {
            int intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }
}
