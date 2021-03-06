package io.github.siebrenvde.staffchat.commands.bungee;

import io.github.siebrenvde.staffchat.Bungee;
import io.github.siebrenvde.staffchat.discord.BungeeAddon;
import io.github.siebrenvde.staffchat.util.BungeeUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.ChatColor;

public class Report extends Command {

    private Bungee plugin;
    private BungeeAddon addon;

    public Report(Bungee pl) {
        super("report");
        plugin = pl;
        addon = pl.addon;
    }

    public void execute(CommandSender sender,String[] strings) {

        if(sender instanceof ProxiedPlayer) {

            ProxiedPlayer player = (ProxiedPlayer) sender;

            if(strings.length < 2) {
                player.sendMessage(new TextComponent(ChatColor.RED + "Usage: /report <player> <reason>"));
            }

            else {

                ProxiedPlayer reportedPlayer = ProxyServer.getInstance().getPlayer(strings[0]);

                if(reportedPlayer != null) {

                    String reporter = player.getName();
                    String reported = reportedPlayer.getName();
                    String server = player.getServer().getInfo().getName();

                    String reason = String.join(" ", strings).replaceFirst("(?i)" + reportedPlayer.getName() + " ", "");

                    player.sendMessage(new TextComponent(ChatColor.GRAY + "Reported " + ChatColor.RED + reported + ChatColor.GRAY + " for " + ChatColor.RED + reason + ChatColor.GRAY + "."));

                    BungeeUtils.sendPermissionMessage(plugin.rmLayout(reason, reporter, reported, server), "staffchat.report.see");

                    if(plugin.config.getBoolean("use-embed")) {
                        addon.sendEmbed(
                                "**Report**",
                                "**Reported player**: " + reported +
                                        "\n**Reporter**: " + reporter +
                                        "\n**Server**: " + server +
                                        "\n**Reason**: `" + reason + "`");
                    }
                    else {
                        addon.sendMessage(plugin.rmdLayout(reason, reporter, reported, server));
                    }

                }

                else {
                    player.sendMessage(new TextComponent(ChatColor.RED + strings[0] + ChatColor.GRAY + " is not online."));
                }

            }

        }

        else {

            sender.sendMessage(new TextComponent("The console can't use this command."));

        }

    }

}
