package me.zed.lvl;

import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import mkremins.fanciful.FancyMessage;

public class NetworkLevels extends JavaPlugin implements Listener {


	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("rank")) {
				p.setDisplayName(ChatColor.AQUA + "[MVP" + ChatColor.RED + "+" + ChatColor.AQUA + "] " + p.getName()
						+ ChatColor.WHITE);
			}
		}
		return false;
	}

	Color getColor(int i) {
		Color c = null;
		if (i == 1) {
			c = Color.AQUA;
		}
		if (i == 2) {
			c = Color.BLACK;
		}
		if (i == 3) {
			c = Color.BLUE;
		}
		if (i == 4) {
			c = Color.FUCHSIA;
		}
		if (i == 5) {
			c = Color.GRAY;
		}
		if (i == 6) {
			c = Color.GREEN;
		}
		if (i == 7) {
			c = Color.LIME;
		}
		if (i == 8) {
			c = Color.MAROON;
		}
		if (i == 9) {
			c = Color.NAVY;
		}
		if (i == 10) {
			c = Color.OLIVE;
		}
		if (i == 11) {
			c = Color.ORANGE;
		}
		if (i == 12) {
			c = Color.PURPLE;
		}
		if (i == 13) {
			c = Color.RED;
		}
		if (i == 14) {
			c = Color.SILVER;
		}
		if (i == 15) {
			c = Color.TEAL;
		}
		if (i == 16) {
			c = Color.WHITE;
		}
		if (i == 17) {
			c = Color.YELLOW;
		}

		return c;
	}

	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);

		for (Player online : Bukkit.getOnlinePlayers()) {
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard board = manager.getNewScoreboard();

			Objective objective = board.registerNewObjective("expshow", "exp");
			objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
			objective.setDisplayName(ChatColor.WHITE + "Lv.");

			new BukkitRunnable() {

				public void run() {
					for (Player online : Bukkit.getOnlinePlayers()) {
						Score score = objective.getScore(online);
						score.setScore(online.getLevel());

					}
					for (Player online : Bukkit.getOnlinePlayers()) {
						online.setScoreboard(board);
					}
				}
			}.runTaskTimer(this, 0, 20);
		}
	}
	
	public void spawnfw(Player p, Location loc){
		Firework fw = (Firework) p.getLocation().getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
		FireworkMeta fwmeta = fw.getFireworkMeta();

		Random ran = new Random();

		int r = ran.nextInt(4) + 1;
		Type type = Type.BALL;

		if (r == 1)
			type = Type.BALL;
		if (r == 2)
			type = Type.BALL_LARGE;
		if (r == 3)
			type = Type.BURST;
		if (r == 4)
			type = Type.CREEPER;
		if (r == 5)
			type = Type.STAR;

		int r1 = ran.nextInt(17) + 1;
		int r2 = ran.nextInt(17) + 1;
		Color c1 = getColor(r1);
		Color c2 = getColor(r2);

		FireworkEffect effect = FireworkEffect.builder().flicker(ran.nextBoolean()).withColor(c1).withFade(c2)
				.with(type).trail(ran.nextBoolean()).build();

		fwmeta.addEffect(effect);

		int rp = ran.nextInt(2) + 1;
		fwmeta.setPower(rp);

		fw.setFireworkMeta(fwmeta);
	}

	@EventHandler
	public void expgain(PlayerLevelChangeEvent e) {
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		String name = ChatColor.GRAY + "[" + ChatColor.GREEN + "Lv. " + ChatColor.WHITE + p.getLevel() + ChatColor.GRAY
				+ "] ";
		p.setDisplayName(name + p.getName());

		if (p.getLevel() == 25) {
			spawnfw(p, loc);

			String first = p.getDisplayName();
			String second = ChatColor.GOLD.toString() + ChatColor.BOLD + "Has Reached Network Lv. 25!";
			String third = ChatColor.GRAY + "You can level up by doing quests, daily voting and";
			String forth = ChatColor.GRAY + "and buying boosters from";
			String fifth = ChatColor.AQUA + " store.hypixel.net";

			String extra = "--> Hover over me for more info about Network Levels <--";

			String info = ChatColor.GRAY + "Click on " + ChatColor.GREEN + "My Profile" + ChatColor.GRAY + " ->"
					+ ChatColor.GREEN + " Coin Mystery " + ChatColor.GRAY + "To view your current Coin Multiplier \n"
					+ ChatColor.GRAY + " and more information on it.";

			String info2 = ChatColor.GRAY + "What does Coin Multiplier do?" + "\n" + ChatColor.GREEN + "  -"
					+ ChatColor.GRAY + "It increases your coin gain in any game you play.";

			String extra1 = ChatColor.GREEN + "1- " + ChatColor.GRAY + "When reaching Network Level" + ChatColor.GOLD
					+ " 25" + ChatColor.GRAY + ", you'll unlock the " + ChatColor.GREEN + "2.0x Coin Mystery";
			String extra2 = ChatColor.GREEN + "2- " + ChatColor.GRAY + "When reaching Network Level" + ChatColor.GOLD
					+ " 50" + ChatColor.GRAY + ", you'll unlock the " + ChatColor.GREEN + "3.0x Coin Mystery";
			String extra3 = ChatColor.GREEN + "3- " + ChatColor.GRAY + "When reaching Network Level" + ChatColor.GOLD
					+ " 75" + ChatColor.GRAY + ", you'll unlock the " + ChatColor.GREEN + "4.0x Coin Mystery";
			String extra4 = ChatColor.GREEN + "4- " + ChatColor.GRAY + "When reaching Network Level" + ChatColor.GOLD
					+ " 100" + ChatColor.GRAY + ", you'll unlock the " + ChatColor.GREEN + "5.0x Coin Mystery";
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage(StringUtils.center(first, 52));
			Bukkit.broadcastMessage(StringUtils.center(second, 52));
			Bukkit.broadcastMessage(StringUtils.center(third, 52));
			Bukkit.broadcastMessage(StringUtils.center(forth, 52));
			Bukkit.broadcastMessage(StringUtils.center(fifth, 52));
			Bukkit.broadcastMessage("");
			new FancyMessage(extra).style(ChatColor.UNDERLINE).color(ChatColor.RED).tooltip(
					info + "\n" + "\n" + extra1 + "\n" + extra2 + "\n" + extra3 + "\n" + extra4 + "\n" + "\n" + info2)
					.send(p);
		}

		if (p.getLevel() == 50) {
			spawnfw(p, loc);

			String first = p.getDisplayName();
			String second = ChatColor.GOLD.toString() + ChatColor.BOLD + "Has Reached Network Lv. 50!";
			String third = ChatColor.GRAY + "You can level up by doing quests, daily voting";
			String forth = ChatColor.GRAY + "and buying boosters from";
			String fifth = ChatColor.AQUA + " store.hypixel.net";

			String extra = "--> Hover over me for more info about Network Levels <--";

			String info = ChatColor.GRAY + "Click on " + ChatColor.GREEN + "My Profile" + ChatColor.GRAY + " ->"
					+ ChatColor.GREEN + " Coin Mystery " + ChatColor.GRAY + "To view your current Coin Multiplier \n"
					+ ChatColor.GRAY + " and more information on it.";

			String info2 = ChatColor.GRAY + "What does Coin Multiplier do?" + "\n" + ChatColor.GREEN + "  -"
					+ ChatColor.GRAY + "It increases your coin gain in any game you play.";

			String extra1 = ChatColor.GREEN + "1- " + ChatColor.GRAY + "When reaching Network Level" + ChatColor.GOLD
					+ " 25" + ChatColor.GRAY + ", you'll unlock the " + ChatColor.GREEN + "2.0x Coin Mystery";
			String extra2 = ChatColor.GREEN + "2- " + ChatColor.GRAY + "When reaching Network Level" + ChatColor.GOLD
					+ " 50" + ChatColor.GRAY + ", you'll unlock the " + ChatColor.GREEN + "3.0x Coin Mystery";
			String extra3 = ChatColor.GREEN + "3- " + ChatColor.GRAY + "When reaching Network Level" + ChatColor.GOLD
					+ " 75" + ChatColor.GRAY + ", you'll unlock the " + ChatColor.GREEN + "4.0x Coin Mystery";
			String extra4 = ChatColor.GREEN + "4- " + ChatColor.GRAY + "When reaching Network Level" + ChatColor.GOLD
					+ " 100" + ChatColor.GRAY + ", you'll unlock the " + ChatColor.GREEN + "5.0x Coin Mystery";
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage(StringUtils.center(first, 52));
			Bukkit.broadcastMessage(StringUtils.center(second, 52));
			Bukkit.broadcastMessage(StringUtils.center(third, 52));
			Bukkit.broadcastMessage(StringUtils.center(forth, 52));
			Bukkit.broadcastMessage(StringUtils.center(fifth, 52));
			Bukkit.broadcastMessage("");

			new FancyMessage(extra).style(ChatColor.UNDERLINE).color(ChatColor.RED).tooltip(
					info + "\n" + "\n" + extra1 + "\n" + extra2 + "\n" + extra3 + "\n" + extra4 + "\n" + "\n" + info2)
					.send(p);
		}

		if (p.getLevel() == 75) {
			spawnfw(p, loc);

			String first = p.getDisplayName();
			String second = ChatColor.GOLD.toString() + ChatColor.BOLD + "Has Reached Network Lv. 75!";
			String third = ChatColor.GRAY + "You can level up by doing quests, daily voting and";
			String forth = ChatColor.GRAY + "and buying boosters from";
			String fifth = ChatColor.AQUA + " store.hypixel.net";
			String extra = "--> Hover over me for more info about Network Levels <--";

			String info = ChatColor.GRAY + "Click on " + ChatColor.GREEN + "My Profile" + ChatColor.GRAY + " ->"
					+ ChatColor.GREEN + " Coin Mystery " + ChatColor.GRAY + "To view your current Coin Multiplier \n"
					+ ChatColor.GRAY + " and more information on it.";

			String info2 = ChatColor.GRAY + "What does Coin Multiplier do?" + "\n" + ChatColor.GREEN + "  -"
					+ ChatColor.GRAY + "It increases your coin gain in any game you play.";

			String extra1 = ChatColor.GREEN + "1- " + ChatColor.GRAY + "When reaching Network Level" + ChatColor.GOLD
					+ " 25" + ChatColor.GRAY + ", you'll unlock the " + ChatColor.GREEN + "2.0x Coin Mystery";
			String extra2 = ChatColor.GREEN + "2- " + ChatColor.GRAY + "When reaching Network Level" + ChatColor.GOLD
					+ " 50" + ChatColor.GRAY + ", you'll unlock the " + ChatColor.GREEN + "3.0x Coin Mystery";
			String extra3 = ChatColor.GREEN + "3- " + ChatColor.GRAY + "When reaching Network Level" + ChatColor.GOLD
					+ " 75" + ChatColor.GRAY + ", you'll unlock the " + ChatColor.GREEN + "4.0x Coin Mystery";
			String extra4 = ChatColor.GREEN + "4- " + ChatColor.GRAY + "When reaching Network Level" + ChatColor.GOLD
					+ " 100" + ChatColor.GRAY + ", you'll unlock the " + ChatColor.GREEN + "5.0x Coin Mystery";
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage(StringUtils.center(first, 52));
			Bukkit.broadcastMessage(StringUtils.center(second, 52));
			Bukkit.broadcastMessage(StringUtils.center(third, 52));
			Bukkit.broadcastMessage(StringUtils.center(forth, 52));
			Bukkit.broadcastMessage(StringUtils.center(fifth, 52));
			Bukkit.broadcastMessage("");

			new FancyMessage(extra).style(ChatColor.UNDERLINE).color(ChatColor.RED).tooltip(
					info + "\n" + "\n" + extra1 + "\n" + extra2 + "\n" + extra3 + "\n" + extra4 + "\n" + "\n" + info2)
					.send(p);
		}

		if (p.getLevel() == 100) {
			spawnfw(p, loc);

			String first = p.getDisplayName();
			String second = ChatColor.GOLD.toString() + ChatColor.BOLD + "Has Reached Network Lv. 100!";
			String third = ChatColor.GRAY + "You can level up by doing quests, daily voting and";
			String forth = ChatColor.GRAY + "and buying boosters from";
			String fifth = ChatColor.AQUA + " store.hypixel.net";
			String extra = "--> Hover over me for more info about Network Levels <--";

			String info = ChatColor.GRAY + "Click on " + ChatColor.GREEN + "My Profile" + ChatColor.GRAY + " ->"
					+ ChatColor.GREEN + " Coin Mystery " + ChatColor.GRAY + "To view your current Coin Multiplier \n"
					+ ChatColor.GRAY + " and more information on it.";

			String info2 = ChatColor.GRAY + "What does Coin Multiplier do?" + "\n" + ChatColor.GREEN + "  -"
					+ ChatColor.GRAY + "It increases your coin gain in any game you play.";

			String extra1 = ChatColor.GREEN + "1- " + ChatColor.GRAY + "When reaching Network Level" + ChatColor.GOLD
					+ " 25" + ChatColor.GRAY + ", you'll unlock the " + ChatColor.GREEN + "2.0x Coin Mystery";
			String extra2 = ChatColor.GREEN + "2- " + ChatColor.GRAY + "When reaching Network Level" + ChatColor.GOLD
					+ " 50" + ChatColor.GRAY + ", you'll unlock the " + ChatColor.GREEN + "3.0x Coin Mystery";
			String extra3 = ChatColor.GREEN + "3- " + ChatColor.GRAY + "When reaching Network Level" + ChatColor.GOLD
					+ " 75" + ChatColor.GRAY + ", you'll unlock the " + ChatColor.GREEN + "4.0x Coin Mystery";
			String extra4 = ChatColor.GREEN + "4- " + ChatColor.GRAY + "When reaching Network Level" + ChatColor.GOLD
					+ " 100" + ChatColor.GRAY + ", you'll unlock the " + ChatColor.GREEN + "5.0x Coin Mystery";
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage(StringUtils.center(first, 52));
			Bukkit.broadcastMessage(StringUtils.center(second, 52));
			Bukkit.broadcastMessage(StringUtils.center(third, 52));
			Bukkit.broadcastMessage(StringUtils.center(forth, 52));
			Bukkit.broadcastMessage(StringUtils.center(fifth, 52));
			Bukkit.broadcastMessage("");

			new FancyMessage(extra).style(ChatColor.UNDERLINE).color(ChatColor.RED).tooltip(
					info + "\n" + "\n" + extra1 + "\n" + extra2 + "\n" + extra3 + "\n" + extra4 + "\n" + "\n" + info2)
					.send(p);
		}
	}
}
