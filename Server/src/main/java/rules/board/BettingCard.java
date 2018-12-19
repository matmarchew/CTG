package rules.board;

public class BettingCard {
    private final String color;
    private final String playerLogin;

    public BettingCard(String color, String playerLogin) {
        this.color = color;
        this.playerLogin = playerLogin;
    }

    public String getPlayerLogin() {
        return playerLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null) return o.equals(color) || o.equals(playerLogin);
        return false;
    }
}
