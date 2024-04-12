package ListGames;

import Model.GameData;

import java.util.Collection;

public record ListGamesResponce(Collection<GameData> games, String message) {
}
