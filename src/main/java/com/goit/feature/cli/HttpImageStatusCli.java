package com.goit.feature.cli;

import com.goit.feature.HttpStatusImageDownloader;
import com.goit.feature.util.UtilHttpResponse;
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
                int statusCode = new UtilHttpResponse().getStringHttpResponse(code).statusCode();
                if (statusCode != 404) {
                    HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
                    downloader.downloadStatusImage(code);
                    System.out.println("Image for HTTP status " + code + " - saved successfully! :)");
                    System.out.println("Please enter a new number: ");
                    askStatusService();

                } else {
                    System.out.println("There is not image for HTTP status " + code + "!");
                    System.out.println("Please enter valid number: ");
                    askStatusService();
                }

            } catch (Exception ex) {
                System.out.println("Please enter valid number: ");
            }
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
