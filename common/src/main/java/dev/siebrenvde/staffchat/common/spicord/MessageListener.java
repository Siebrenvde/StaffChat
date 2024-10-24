package dev.siebrenvde.staffchat.common.spicord;

import dev.siebrenvde.staffchat.common.StaffChat;
import dev.siebrenvde.staffchat.common.messages.Messages;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageListener extends ListenerAdapter {

    private final Addon addon;

    public MessageListener(Addon addon) {
        this.addon = addon;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        User author = event.getAuthor();

        if(
            !(event.getChannel() instanceof TextChannel channel)
            || !channel.equals(addon.getStaffChannel())
            || author.isBot()
        ) return;

        StaffChat.getPlatform().broadcast(
            Messages.staffChat().serverFromDiscord(
                addon.getStaffChannel().getGuild().getMember(author),
                event.getMessage().getContentStripped()
            ),
            "staffchat.see"
        );

    }

}
