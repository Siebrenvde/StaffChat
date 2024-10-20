package dev.siebrenvde.staffchat.common.minecraft;

import net.kyori.adventure.text.Component;

public interface CommandSender {

    /**
     * {@return the name of the command sender}
     */
    String getName();

    /**
     * {@return the display name of the command sender}
     */
    String getDisplayName();

    /**
     * {@return the name of the server the command sender is connected to}
     */
    String getServerName();

    /**
     * Sends a message to the command sender
     * @param message the message to send
     */
    void sendMessage(Component message);

    /**
     * {@return whether the command sender has the provided permission}
     * @param permission the permission to check
     */
    boolean hasPermission(String permission);

}
