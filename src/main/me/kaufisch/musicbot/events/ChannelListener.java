package me.kaufisch.musicbot.events;

import me.kaufisch.musicbot.audioCore.Music;
import me.kaufisch.musicbot.utils.Const;
import me.kaufisch.musicbot.main.Main;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
/**
 * @author Kaufisch
 */
public class ChannelListener extends ListenerAdapter {

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
        try {
            if (event.getChannelJoined().getId().equals(Const.MusicChannel)) {
                if (!event.getMember().getUser().isBot()) {
                    Main.userInChannel.add(event.getMember());
                }
            }
        } catch (NullPointerException exception) {
        }
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
        try {
            if (event.getChannelLeft().getId().equals(Const.MusicChannel)) {
                if (!event.getMember().getUser().isBot()) {
                    Main.userInChannel.remove(event.getMember());
                }
            }
        } catch (NullPointerException exception) {
        }
    }

    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
        try {
            if (event.getChannelLeft().getId().equals(Const.MusicChannel)) {
                if (!event.getMember().getUser().isBot()) {
                    Main.userInChannel.remove(event.getMember());
                }
            }
            if (event.getChannelJoined().getId().equals(Const.MusicChannel)) {
                if (!event.getMember().getUser().isBot()) {
                    Main.userInChannel.add(event.getMember());
                }
            }
        } catch (NullPointerException exception) {
        }
    }
}
