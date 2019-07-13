package me.kaufisch.musicbot.main;

import me.kaufisch.musicbot.events.ChannelListener;
import me.kaufisch.musicbot.events.MessageListener;
import me.kaufisch.musicbot.events.onResume;
import me.kaufisch.musicbot.utils.Const;
import me.kaufisch.musicbot.utils.FileManager;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;

import java.util.ArrayList;
/**
 * @author Kaufisch
 */
public class Main {

    public static JDA jda;
    public static Guild guild = null;
    public static ArrayList<Member> userAlreadyDid = new ArrayList<>();
    public static ArrayList<Member> userInChannel = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        FileManager fileManager = new FileManager();
        fileManager.Config();

        jda = new JDABuilder(AccountType.BOT)
                .setToken(Const.TOKEN)
                .setGame(Game.playing("Music"))
                .addEventListener(new MessageListener())
                .addEventListener(new ChannelListener())
                .addEventListener(new onResume())
                .setAutoReconnect(true)
                .build();
    }
}
