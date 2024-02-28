package ListGames;

import Server.GameData;

import java.util.Collection;
import java.util.Vector;

public record ListGamesResponce(Collection<GameData> games, String message) {
}
