import com.goit.feature.cli.HttpImageStatusCli;
import com.goit.feature.exceptions.NotFoundException;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws NotFoundException {
        HttpImageStatusCli cli = new HttpImageStatusCli(new Scanner(System.in));
        cli.askStatus();
    }
}
