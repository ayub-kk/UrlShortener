import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LinkShortener {
    private Map<String, Link> links = new HashMap<>();
    private Map<UUID, String> userLinks = new HashMap<>();

    public String shortenLink(String originalUrl, UUID userId) {
        String shortUrl = generateShortUrl(originalUrl);
        Link link = new Link(originalUrl, shortUrl, 5); // Лимит переходов - 5
        links.put(shortUrl, link);
        userLinks.put(userId, shortUrl);
        return shortUrl;
    }

    private String generateShortUrl(String originalUrl) {
        return "shortener/" + Integer.toHexString(originalUrl.hashCode());
    }

    public String getOriginalUrl(String shortUrl) {
        Link link = links.get(shortUrl);

        if (link.isExpired()) {
            links.remove(shortUrl);
            return "Ссылка истекла.";
        }
        if (link.isBlocked()) {
            return "Ссылка заблокирована.";
        }
        link.incrementVisitCount();
        return link.getOriginalUrl();
    }
}