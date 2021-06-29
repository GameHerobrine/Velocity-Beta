package us.velocity.client.api.util;

import java.util.*;

public class FriendUtil
{
	private static List<String> friends = new ArrayList<String>();;

	public static void addFriend(final String name) {
		FriendUtil.friends.add(name);
	}

	public static boolean isFriend(final String name) {
		return FriendUtil.friends.contains(name);
	}

	public static List<String> getFriends() {
		return FriendUtil.friends;
	}
}