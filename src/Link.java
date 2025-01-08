import java.time.LocalDateTime;

public class Link {
    private String originalUrl;
    private String shortUrl;
    private int visitCount;
    private int visitLimit;
    private LocalDateTime creationTime;
    private LocalDateTime expirationTime;

    public Link(String originalUrl, String shortUrl, int visitLimit) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.visitCount = 0;
        this.visitLimit = visitLimit;
        this.creationTime = LocalDateTime.now();
        this.expirationTime = creationTime.plusDays(1); // Время жизни ссылки - 1 день
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationTime);
    }

    public boolean isBlocked() {
        return visitCount >= visitLimit;
    }

    public void incrementVisitCount() {
        visitCount++;
    }
}
