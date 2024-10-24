package dev.siebrenvde.staffchat.common.minecraft.listeners;

import dev.siebrenvde.staffchat.common.minecraft.Player;
import dev.siebrenvde.staffchat.common.minecraft.commands.StaffChatCommand;

public class EventListeners {

    public static boolean chatListener(Player player, String message) {
        if(!StaffChatCommand.ENABLED_PLAYERS.contains(player.getUniqueId())) return false;
        StaffChatCommand.executeSendMessage(player, message);
        return true;
    }

    public static void disconnectListener(Player player) {
        StaffChatCommand.ENABLED_PLAYERS.remove(player.getUniqueId());
    }

}
