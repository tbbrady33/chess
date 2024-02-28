package ListGames;

import server.GameData;

import java.util.Collection;

public record ListGamesResponce(Collection<GameData> games, String message) {
}
