package me.kaufisch.musicbot.events;

import me.kaufisch.musicbot.audioCore.Music;
import me.kaufisch.musicbot.utils.Const;
import me.kaufisch.musicbot.main.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.Objects;

/**
 * @author Kaufisch
 */
public class MessageListener extends ListenerAdapter {

    private int a;

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getMessage().getChannel().getId().equals(Const.VoteSkipChannel)) {
            if (event.getMessage().getContentRaw().contentEquals("!skip")) {
                if (Main.userInChannel.contains(event.getMember())) {
                    if (!Main.userAlreadyDid.contains(event.getMember())) {
                        int c = Main.userInChannel.size();
                        a++;
                        Main.userAlreadyDid.add(event.getMember());
                        int temp = a * 100;
                        int b = temp / c;

                        if (c == 1) {
                            Music.skip();
                            event.getChannel().sendMessage("Voteskip: " + a + " / " + c).queue();
                            a = 0;
                            Main.userAlreadyDid.clear();
                        } else if (c == 2) {
                            if (b == 100) {
                                Music.skip();
                                event.getChannel().sendMessage("Voteskip: " + a + " / " + c).queue();
                                a = 0;
                                Main.userAlreadyDid.clear();
                            } else {
                                event.getChannel().sendMessage("Voteskip: " + a + " / " + c).queue();
                            }
                        } else {
                            if (b > 60) {
                                Music.skip();
                                event.getChannel().sendMessage("Voteskip: " + a + " / " + c).queue();
                                a = 0;
                                Main.userAlreadyDid.clear();
                            } else {
                                event.getChannel().sendMessage("Voteskip: " + a + " / " + c).queue();
                            }

                        }
                    } else {
                        event.getChannel().sendMessage(Objects.requireNonNull(event.getMember()).getAsMention() + " " + Const.VoteSkipMessage).queue();
                    }
                } else {
                    event.getMessage().delete().queue();
                }
            } else {
                if (!Objects.requireNonNull(event.getMember()).getUser().isBot()) {
                    event.getMessage().delete().queue();
                }
            }
        }
    }
}
