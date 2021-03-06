package io.github.siebrenvde.staffchat.commands.bungee;

import io.github.siebrenvde.staffchat.Bungee;
import io.github.siebrenvde.staffchat.discord.BungeeAddon;
import io.github.siebrenvde.staffchat.util.BungeeUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.ChatColor;

public class HelpOp extends Command {

    private Bungee plugin;
    private BungeeAddon addon;

    public HelpOp(Bungee pl) {
        super("helpop");
        plugin = pl;
        addon = pl.addon;
    }

    public void execute(CommandSender sender, String[] strings) {

        if(sender instanceof ProxiedPlayer) {

            ProxiedPlayer player = (ProxiedPlayer) sender;

            if(strings.length < 1) {
                player.sendMessage(new TextComponent(ChatColor.RED + "Usage: /helpop <message>"));
            }

            else {

                String playerName = player.getName();
                String server = player.getServer().getInfo().getName();
                String message = String.join(" ", strings);

                if(player.hasPermission("staffchat.helpop.see")) {
                    BungeeUtils.sendPermissionMessage(plugin.homLayout(message, playerName, server), "staffchat.helpop.see");
                }

                else {
                    player.sendMessage(new TextComponent(plugin.homLayout(message, playerName, server)));

                    BungeeUtils.sendPermissionMessage(plugin.homLayout(message, playerName, server), "staffchat.helpop.see");
                }

                if(plugin.config.getBoolean("use-embed")) {
                    addon.sendEmbed(
                            "**HelpOp**",
                            "**Player**: " + playerName +
                                    "\n**Server**: " + server +
                                    "\n**Message**: `" + message + "`");
                }
                else {
                    addon.sendMessage(plugin.homdLayout(message, playerName, server));
                }

            }

        }

        else {

            sender.sendMessage(new TextComponent("The console can't use this command."));

        }

    }

}
