package me.kaufisch.musicbot.events;

import me.kaufisch.musicbot.audioCore.Music;
import me.kaufisch.musicbot.utils.Const;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ReconnectedEvent;
import net.dv8tion.jda.api.events.ResumedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author Kaufisch
 */
public class Resume extends ListenerAdapter {

    @Override
    public void onResume(@NotNull ResumedEvent event) {
        Music.skip();
    }

    @Override
    public void onReconnect(@NotNull ReconnectedEvent event) {
        Music.skip();
    }

    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("Bot started!");
        Music.musicBot(event.getJDA(), Objects.requireNonNull(event.getJDA().getGuildById(Const.GUILD_ID)));
    }
}
