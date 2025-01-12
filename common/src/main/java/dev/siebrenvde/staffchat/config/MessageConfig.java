package dev.siebrenvde.staffchat.config;

import com.electronwill.nightconfig.core.serde.ObjectDeserializer;
import com.electronwill.nightconfig.core.serde.annotations.SerdeKey;
import dev.siebrenvde.staffchat.api.config.BaseConfig;

import java.nio.file.Path;

public class MessageConfig extends BaseConfig {

    public static MessageConfig load(Path path) {
        return ObjectDeserializer.standard().deserializeFields(load(path, "messages.toml"), MessageConfig::new);
    }

    @SerdeKey("permission_message")
    public String permissionMessage;

    @SerdeKey("staffchat")
    public StaffChat staffChat;
    public Report report;
    @SerdeKey("helpop")
    public HelpOp helpOp;

    public static class StaffChat {

        @SerdeKey("server_from_server")
        public String serverFromServer;

        @SerdeKey("proxy_from_proxy")
        public String proxyFromProxy;

        @SerdeKey("game_from_discord")
        public String gameFromDiscord;

        @SerdeKey("discord_from_server")
        public String discordFromServer;

        @SerdeKey("discord_from_proxy")
        public String discordFromProxy;

        @SerdeKey("player_only")
        public String playerOnly;

        public String enabled;

        public String disabled;

    }

    public static class Report {

        @SerdeKey("server_from_server")
        public String serverFromServer;

        @SerdeKey("proxy_from_proxy")
        public String proxyFromProxy;

        @SerdeKey("discord_from_server")
        public String discordFromServer;

        @SerdeKey("discord_from_proxy")
        public String discordFromProxy;

        public String success;

        @SerdeKey("player_not_found")
        public String playerNotFound;

        public String usage;

    }

    public static class HelpOp {

        @SerdeKey("server_from_server")
        public String serverFromServer;

        @SerdeKey("proxy_from_proxy")
        public String proxyFromProxy;

        @SerdeKey("discord_from_server")
        public String discordFromServer;

        @SerdeKey("discord_from_proxy")
        public String discordFromProxy;

        public String success;

        public String usage;

    }

}
