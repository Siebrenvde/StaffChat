package dev.siebrenvde.staffchat.config;

import com.electronwill.nightconfig.core.serde.ObjectDeserializer;
import com.electronwill.nightconfig.core.serde.annotations.SerdeKey;
import dev.siebrenvde.staffchat.api.config.BaseConfig;

import java.nio.file.Path;

public class CommandConfig extends BaseConfig {

    public static CommandConfig load(Path path) {
        return ObjectDeserializer.standard().deserializeFields(load(path, "commands.toml"), CommandConfig::new);
    }

    @SerdeKey("staffchat")
    public StaffChat staffChat;
    public static class StaffChat {
        public boolean enabled;
        public String name;
        public String[] aliases;
        public String description;
    }

}
