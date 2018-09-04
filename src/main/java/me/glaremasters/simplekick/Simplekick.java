package me.glaremasters.simplekick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Simplekick extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
       saveDefaultConfig();
       this.getCommand("kick").setExecutor(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        StringBuilder sb = new StringBuilder();
        List<String> message = getConfig().getStringList("kick-message");
        if (!sender.hasPermission("simplekick.kick")) return true;
        if (args.length != 1) return true;
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) return true;
        for (String line : message) {
            sb.append(color(line) + "\n");
        }
        target.kickPlayer(sb.toString());
        return true;
    }
}
