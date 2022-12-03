import com.goit.feature.HttpStatusChecker;
import com.goit.feature.HttpStatusImageDownloader;
import com.goit.feature.cli.HttpImageStatusCli;
import com.goit.feature.exceptions.NotFoundException;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException, NotFoundException {
        HttpStatusChecker checker = new HttpStatusChecker();
        checker.getStatusImage(404);

//        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
//        downloader.downloadStatusImage(305);

        HttpImageStatusCli cli = new HttpImageStatusCli(new Scanner(System.in));
        cli.askStatus();
    }
}
