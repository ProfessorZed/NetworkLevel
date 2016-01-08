package me.zed.lvl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class NetworkLevels extends JavaPlugin {

	public void onEnable() {

		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();

		Objective objective = board.registerNewObjective("expshow", "exp");
		objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
		objective.setDisplayName(ChatColor.GOLD + Symbols.star);

		new BukkitRunnable() {

			@SuppressWarnings("deprecation")
			public void run() {
				for (Player online : Bukkit.getOnlinePlayers()) {
					Score score = objective.getScore(online);
					score.setScore(online.getLevel());

					online.setScoreboard(board);
				}
			}
		}.runTaskTimer(this, 0, 20);
	}
}
