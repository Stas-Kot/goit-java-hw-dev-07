package com.goit.feature;

import com.goit.feature.exceptions.NotFoundException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HttpStatusImageDownloader {
    private static final String RELATIVE_PATH = "./src/main/resources/out/images/";

    public void downloadStatusImage(int code) throws IOException, InterruptedException {
        HttpStatusChecker checker = new HttpStatusChecker();

        try {
            String imageUrl = checker.getStatusImage(code);
            URL url = new URL(imageUrl);

            String fileName = code + ".jpg";

            File file = new File(RELATIVE_PATH+fileName);
            if(!file.exists()){
                file.getParentFile().mkdirs();
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }

            try(FileOutputStream outputStream = new FileOutputStream(file); InputStream inputStream = url.openStream()) {
                byte[] buffer = new byte[2048];
                int length;

                while ((length = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, length);
                }
                System.out.println("Image for HTTP status " + code + " - saved successfully! :)");
            } catch(IOException e) {
                System.err.println("Exception!!!" + e.getMessage());
            }
        } catch (NotFoundException ex) {
            System.out.println("There is not image for HTTP status " + code + "!");
        }
    }
}
