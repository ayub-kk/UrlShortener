import java.io.IOException;
import java.net.URI;
import java.awt.Desktop;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        LinkShortener linkShortener = new LinkShortener();
        User user = new User();

        System.out.println("Введите длинную ссылку");
        String input = scanner.nextLine();

        String shortLink = linkShortener.shortenLink(input, user.getId());
        System.out.println("Короткая ссылка: " + shortLink);

        System.out.println("Введите короткую ссылку для перехода:");
        String shortInput = scanner.nextLine();
        String originalUrl = linkShortener.getOriginalUrl(shortInput);
        System.out.println(originalUrl);

        if (!originalUrl.equals("Ссылка истекла.") && !originalUrl.equals("Ссылка заблокирована.")) {
            Desktop.getDesktop().browse(URI.create(originalUrl));
        }
    }
}
