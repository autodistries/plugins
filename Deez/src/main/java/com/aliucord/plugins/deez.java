package com.aliucord.plugins;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.aliucord.api.CommandsAPI;
import com.aliucord.entities.Plugin;
import java.util.*;

@SuppressWarnings("unused")
public class Deez extends Plugin {

    @NonNull
    @Override
    public Manifest getManifest() {
        Manifest manifest = new Manifest();
        manifest.authors = new Manifest.Author[]{ new Manifest.Author("Wing", 298295889720770563L) };
        manifest.description = "Deez nuts";
        manifest.version = "1.0";
        manifest.updateUrl = "https://raw.githubusercontent.com/wingio/plugins/builds/updater.json";
        return manifest;
    }

    @Override
    public void start(Context context) {
        commands.registerCommand("clap", "Clap your messages", Collections.singletonList(CommandsAPI.requiredMessageOption), args -> {
            String msg = (String) args.get("message");
            if (msg == null) return new CommandsAPI.CommandResult(msg);
            String Clap;
            if(msg.length() == 1) {
                String[] x = msg.split("", 0);
                Clap = TextUtils.join(" :clap: ", x);
            } else {
                Clap = TextUtils.join(" :clap: ", msg.split(" ", 0));
            }
            return new CommandsAPI.CommandResult(Clap);
        });

    }

    @Override
    public void stop(Context context) {
        commands.unregisterAll();
    }

}