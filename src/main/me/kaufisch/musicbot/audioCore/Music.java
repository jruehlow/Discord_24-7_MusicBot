package me.kaufisch.musicbot.audioCore;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import me.kaufisch.musicbot.utils.Const;
import me.kaufisch.musicbot.main.Main;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.managers.AudioManager;
/**
 * @author Kaufisch
 */
public class Music {

    public static AudioPlayerManager playerManager;
    public static TrackScheduler trackScheduler;
    public static AudioPlayer player;

    public static JDA jda;

    public static void musicBot() {
        playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
        player = playerManager.createPlayer();
        trackScheduler = new TrackScheduler(player);
        player.addListener(trackScheduler);
        VoiceChannel channel = Main.guild.getVoiceChannelById(Const.MusicChannel);
        AudioManager manager = Main.guild.getAudioManager();
        manager.setSendingHandler(new AudioPlayerSendHandler(player));
        manager.openAudioConnection(channel);
        player.setVolume(50);
        trackScheduler.nextTrack();
    }

    public void loadAndPlay(final String trackUrl) {
        playerManager.loadItem(trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                trackScheduler.queue(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                for (AudioTrack track : playlist.getTracks()) {
                    trackScheduler.queue(track);
                }
            }

            @Override
            public void noMatches() {
            }

            @Override
            public void loadFailed(FriendlyException throwable) {
            }
        });
    }

    public static void skip() {
        trackScheduler = new TrackScheduler(player);
        trackScheduler.nextTrack2();

    }

}
