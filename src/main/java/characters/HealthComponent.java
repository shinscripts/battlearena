package characters;

public final class HealthComponent {
    private final int maxHealth;
    private int currentHealth;

    public HealthComponent(int initialHealth) {
        if (!Float.isFinite(initialHealth) || initialHealth <= 0f) {
            throw new IllegalArgumentException("Initial health must be finite and greater than zero");
        }

        this.currentHealth = this.maxHealth = initialHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void reduceHealth(int amount) {
        if (!Float.isFinite(amount) || amount < 0f) {
            throw new IllegalArgumentException("Health amount must be finite and non-negative");
        }

        currentHealth = Math.max(currentHealth - amount, 0);
    }

    public void increaseHealth(int amount) {
        if (!Float.isFinite(amount) || amount < 0f) {
            throw new IllegalArgumentException("Health amount must be finite and non-negative");
        }

        currentHealth = Math.min(currentHealth + amount, maxHealth);
    }

    public String getHealthString() {
        return String.format("%s / %s", currentHealth, maxHealth);
    }
}
