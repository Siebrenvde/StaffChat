package dev.siebrenvde.staffchat.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.siebrenvde.staffchat.StaffChat;
import dev.siebrenvde.staffchat.config.Config;
import dev.siebrenvde.staffchat.messages.Messages;
import dev.siebrenvde.staffchat.api.command.BaseCommand;
import dev.siebrenvde.staffchat.api.command.BrigadierCommandManager;
import dev.siebrenvde.staffchat.api.command.CommonCommandSender;
import dev.siebrenvde.staffchat.api.player.CommonPlayer;
import dev.siebrenvde.staffchat.util.Permissions;
import dev.siebrenvde.staffchat.util.SignedMessageCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StaffChatCommand extends BaseCommand {

    public static final List<UUID> ENABLED_PLAYERS = new ArrayList<>();

    public StaffChatCommand() {
        super(Config.COMMANDS.staffChat, Permissions.COMMAND_STAFFCHAT);
    }

    @Override
    public <C> LiteralCommandNode<C> brigadier(BrigadierCommandManager<C> manager) {
        return manager.literal(getName())
            .requires(sender -> CommonCommandSender.of(sender).hasPermission(getRootPermission()))
            .executes(ctx -> {
                executeToggle(CommonCommandSender.of(ctx.getSource()));
                return 1;
            })
            .then(manager.argument("message", StringArgumentType.greedyString())
                .executes(ctx -> {
                    executeSendMessage(
                        CommonCommandSender.of(ctx.getSource()),
                        StringArgumentType.getString(ctx, "message")
                    );
                    return 1;
                })
            )
            .build();
    }

    @Override
    public void simple(CommonCommandSender sender, String[] args) {
        if(!checkPermission(sender, getRootPermission())) return;
        if(args.length == 0) executeToggle(sender);
        else executeSendMessage(sender, String.join(" ", args));
    }

    private void executeToggle(CommonCommandSender sender) {
        if(!(sender instanceof CommonPlayer player)) {
            sender.sendMessage(Messages.staffChat().playerOnly());
            return;
        }
        if(!SignedMessageCompat.isSupported(player)) return;
        UUID uuid = player.getUniqueId();
        if(!ENABLED_PLAYERS.contains(uuid)) {
            ENABLED_PLAYERS.add(uuid);
            player.sendMessage(Messages.staffChat().enabled());
        } else {
            ENABLED_PLAYERS.remove(uuid);
            player.sendMessage(Messages.staffChat().disabled());
        }
    }

    public static void executeSendMessage(CommonCommandSender sender, String message) {
        StaffChat.getServer().broadcast(
            Messages.staffChat().serverFromServer(sender, message),
            Permissions.RECEIVE_STAFFCHAT
        );
        StaffChat.getAddon().sendMessage(Messages.staffChat().discordFromServer(sender, message));
    }

}
