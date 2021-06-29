package us.velocity.client.impl.commands;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandSource;
import us.velocity.client.api.gui.util.FileUtil;
import us.velocity.client.api.util.FriendUtil;
import us.velocity.client.api.util.MessageUtil;
import net.minecraft.server.command.Command;

public class Friend extends Command
{
	public Friend(String commandString, CommandSource source) {
		super(commandString, source);
	}

	public boolean allowUsageWithoutPrefix(final Command sender, final String message) {
		return false;
	}

	public String getName() {
		return "friend";
	}

	public String getUsage(final Command sender) {
		return null;
	}

	public void execute(final MinecraftServer server, final Command sender, final String[] args) {
		if (args.length == 0) {
			MessageUtil.sendClientMessage("Usage: /friend add [name]");
		}
		if (args.length == 1) {
			if (FriendUtil.isFriend(args[0])) {
				MessageUtil.sendClientMessage(args[0] + " is a friend.");
			}
			else {
				MessageUtil.sendClientMessage(args[0] + " is not a friend.");
			}
		}
		if (args.length == 2) {
			if (!FriendUtil.isFriend(args[1])) {
				FriendUtil.addFriend(args[1]);
				FileUtil.saveFriends();
				MessageUtil.sendClientMessage("Successfully added " + args[1] + " as a friend.");
			}
			else {
				MessageUtil.sendClientMessage(args[1] + " is already a friend.");
			}
		}
	}

	public boolean checkPermission(final MinecraftServer server, final Command sender) {
		return true;
	}
}