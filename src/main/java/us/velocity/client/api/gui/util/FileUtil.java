package us.velocity.client.api.gui.util;

import us.velocity.client.api.gui.main.Window;
import us.velocity.client.api.module.Module;
import us.velocity.client.api.module.ModuleManager;
import us.velocity.client.api.setting.Setting;
import us.velocity.client.api.setting.SubSetting;
import us.velocity.client.api.util.FriendUtil;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileUtil
{
	private static File velocity;

	public static void loadAll() {
		createDirectory();
		loadGui();
		loadFriends();
		loadSettings();
		loadSubSettings();
		loadActiveModules();
	}

	public static void saveAll() {
		saveGui();
		saveFriends();
		saveSettings();
		saveSubSettings();
		saveActiveModules();
	}

	public static void createDirectory() {
		FileUtil.velocity = new File("Velocity");
		if (!FileUtil.velocity.exists()) {
			FileUtil.velocity.mkdirs();
		}
	}

	public static void saveGui() {
		try {
			final File guiPos = new File(FileUtil.velocity.getAbsolutePath(), "ClickGui.txt");
			final BufferedWriter bw = new BufferedWriter(new FileWriter(guiPos));
			for (final Window w : Window.windows) {
				bw.write("x:" + w.x + ":y:" + w.y);
				bw.write("\r\n");
			}
			bw.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadGui() {
		try {
			final File guiPos = new File(FileUtil.velocity.getAbsolutePath(), "ClickGui.txt");
			final BufferedReader br = new BufferedReader(new FileReader(guiPos));
			final List<String> linezz = Files.readAllLines(guiPos.toPath());
			int lineIndex = 0;
			for (final String line : linezz) {
				final String[] regex = line.split(":");
				final Window w = Window.windows.get(lineIndex);
				w.x = Integer.parseInt(regex[1]);
				w.y = Integer.parseInt(regex[3]);
				++lineIndex;
			}
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveSettings() {
		try {
			final File settings = new File(FileUtil.velocity.getAbsolutePath(), "Settings.txt");
			final BufferedWriter bw = new BufferedWriter(new FileWriter(settings));
			for (Module m : ModuleManager.getModules()) {
				bw.write(m.getName() + ":");
				for (final Setting<?> s : m.getSettings()) {
					if (s.getValue() instanceof Boolean) {
						final Setting<Boolean> sb = (Setting<Boolean>)s;
						bw.write(Boolean.toString(sb.getValue()) + ":");
					}
					if (s.getValue() instanceof Integer) {
						final Setting<Integer> si = (Setting<Integer>)s;
						bw.write(si.getValue() + ":");
					}
					if (s.getValue() instanceof Double) {
						final Setting<Double> sd = (Setting<Double>)s;
						bw.write(sd.getValue() + ":");
					}
					if (s.getValue() instanceof String) {
						final Setting<String> ss = (Setting<String>)s;
						bw.write(ss.getValue() + ":");
					}
				}
				bw.write("\r\n");
			}
			bw.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadSettings() {
		try {
			final File settings = new File(FileUtil.velocity.getAbsolutePath(), "Settings.txt");
			final BufferedReader br = new BufferedReader(new FileReader(settings));
			final List<String> linezz = Files.readAllLines(settings.toPath());
			int lineIndex = 0;
			for (final String line : linezz) {
				final String[] regex = line.split(":");
				final Module m = ModuleManager.getModules().get(lineIndex);
				if (regex[0].startsWith(m.getName())) {
					int regexCount = 0;
					for (final Setting s : m.getSettings()) {
						if (s.getValue() instanceof Boolean) {
							s.setValue(Boolean.parseBoolean(regex[regexCount + 1]));
							++regexCount;
						}
						if (s.getValue() instanceof Integer) {
							s.setValue(Integer.parseInt(regex[regexCount + 1]));
							++regexCount;
						}
						if (s.getValue() instanceof Double) {
							s.setValue(Double.parseDouble(regex[regexCount + 1]));
							++regexCount;
						}
						if (s.getValue() instanceof String) {
							s.setValue(regex[regexCount + 1]);
							++regexCount;
						}
					}
				}
				++lineIndex;
			}
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveSubSettings() {
		try {
			final File subsettings = new File(FileUtil.velocity.getAbsolutePath(), "SubSettings.txt");
			final BufferedWriter bw = new BufferedWriter(new FileWriter(subsettings));
			for (final Module m : ModuleManager.getModules()) {
				if (m.hasSettings()) {
					for (final Setting s : m.getSettings()) {
						if (s.hasSubSettings()) {
							bw.write(m.getName() + ":" + s.getName() + ":");
							if (s.getValue() instanceof Boolean) {
								final Setting<Boolean> sb = (Setting<Boolean>)s;
								for (final SubSetting ss : sb.getSubSettings()) {
									if (ss.getValue() instanceof Boolean) {
										final SubSetting<Boolean> ssb = (SubSetting<Boolean>)ss;
										bw.write(Boolean.toString(ssb.getValue()) + ":");
									}
									if (ss.getValue() instanceof Integer) {
										final SubSetting<Integer> ssi = (SubSetting<Integer>)ss;
										bw.write(ssi.getValue() + ":");
									}
									if (ss.getValue() instanceof Double) {
										final SubSetting<Double> ssd = (SubSetting<Double>)ss;
										bw.write(ssd.getValue() + ":");
									}
									if (ss.getValue() instanceof String) {
										final SubSetting<String> sss = (SubSetting<String>)ss;
										bw.write(sss.getValue() + ":");
									}
								}
								bw.write("\r\n");
							}
							if (s.getValue() instanceof Integer) {
								final Setting<Integer> si = (Setting<Integer>)s;
								for (final SubSetting ss : si.getSubSettings()) {
									if (ss.getValue() instanceof Boolean) {
										final SubSetting<Boolean> ssb = (SubSetting<Boolean>)ss;
										bw.write(Boolean.toString(ssb.getValue()) + ":");
									}
									if (ss.getValue() instanceof Integer) {
										final SubSetting<Integer> ssi = (SubSetting<Integer>)ss;
										bw.write(ssi.getValue() + ":");
									}
									if (ss.getValue() instanceof Double) {
										final SubSetting<Double> ssd = (SubSetting<Double>)ss;
										bw.write(ssd.getValue() + ":");
									}
									if (ss.getValue() instanceof String) {
										final SubSetting<String> sss = (SubSetting<String>)ss;
										bw.write(sss.getValue() + ":");
									}
								}
								bw.write("\r\n");
							}
							if (s.getValue() instanceof Double) {
								final Setting<Double> sd = (Setting<Double>)s;
								for (final SubSetting ss : sd.getSubSettings()) {
									if (ss.getValue() instanceof Boolean) {
										final SubSetting<Boolean> ssb = (SubSetting<Boolean>)ss;
										bw.write(Boolean.toString(ssb.getValue()) + ":");
									}
									if (ss.getValue() instanceof Integer) {
										final SubSetting<Integer> ssi = (SubSetting<Integer>)ss;
										bw.write(ssi.getValue() + ":");
									}
									if (ss.getValue() instanceof Double) {
										final SubSetting<Double> ssd = (SubSetting<Double>)ss;
										bw.write(ssd.getValue() + ":");
									}
									if (ss.getValue() instanceof String) {
										final SubSetting<String> sss = (SubSetting<String>)ss;
										bw.write(sss.getValue() + ":");
									}
								}
								bw.write("\r\n");
							}
							if (!(s.getValue() instanceof String)) {
								continue;
							}
							final Setting<String> sstr = (Setting<String>)s;
							for (final SubSetting ss : sstr.getSubSettings()) {
								if (ss.getValue() instanceof Boolean) {
									final SubSetting<Boolean> ssb = (SubSetting<Boolean>)ss;
									bw.write(Boolean.toString(ssb.getValue()) + ":");
								}
								if (ss.getValue() instanceof Integer) {
									final SubSetting<Integer> ssi = (SubSetting<Integer>)ss;
									bw.write(ssi.getValue() + ":");
								}
								if (ss.getValue() instanceof Double) {
									final SubSetting<Double> ssd = (SubSetting<Double>)ss;
									bw.write(ssd.getValue() + ":");
								}
								if (ss.getValue() instanceof String) {
									final SubSetting<String> sss = (SubSetting<String>)ss;
									bw.write(sss.getValue() + ":");
								}
							}
							bw.write("\r\n");
						}
					}
				}
			}
			bw.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadSubSettings() {
		try {
			final File subsettings = new File(FileUtil.velocity.getAbsolutePath(), "SubSettings.txt");
			final BufferedReader br = new BufferedReader(new FileReader(subsettings));
			final List<String> linezz = Files.readAllLines(subsettings.toPath());
			int lineIndex = 0;
			for (final String line : linezz) {
				final String[] regex = line.split(":");
				for (final Module m : ModuleManager.getModules()) {
					final Setting s = ModuleManager.getModuleByName(regex[0]).getSettings().get(lineIndex);
					if (regex[1].startsWith(s.getName())) {
						int regexCount = 0;
						if (s.getValue() instanceof Boolean) {
							final Setting<Boolean> sb = (Setting<Boolean>)s;
							for (final SubSetting ss : sb.getSubSettings()) {
								if (ss.getValue() instanceof Boolean) {
									ss.setValue(Boolean.parseBoolean(regex[regexCount + 2]));
									++regexCount;
								}
								if (ss.getValue() instanceof Integer) {
									ss.setValue(Integer.parseInt(regex[regexCount + 2]));
									++regexCount;
								}
								if (ss.getValue() instanceof Double) {
									ss.setValue(Double.parseDouble(regex[regexCount + 2]));
									++regexCount;
								}
								if (ss.getValue() instanceof String) {
									ss.setValue(regex[regexCount + 2]);
									++regexCount;
								}
							}
						}
						if (s.getValue() instanceof Integer) {
							final Setting<Integer> si = (Setting<Integer>)s;
							for (final SubSetting ss : si.getSubSettings()) {
								if (ss.getValue() instanceof Boolean) {
									ss.setValue(Boolean.parseBoolean(regex[regexCount + 2]));
									++regexCount;
								}
								if (ss.getValue() instanceof Integer) {
									ss.setValue(Integer.parseInt(regex[regexCount + 2]));
									++regexCount;
								}
								if (ss.getValue() instanceof Double) {
									ss.setValue(Double.parseDouble(regex[regexCount + 2]));
									++regexCount;
								}
								if (ss.getValue() instanceof String) {
									ss.setValue(regex[regexCount + 2]);
									++regexCount;
								}
							}
						}
						if (s.getValue() instanceof Double) {
							final Setting<Double> sd = (Setting<Double>)s;
							for (final SubSetting ss : sd.getSubSettings()) {
								if (ss.getValue() instanceof Boolean) {
									ss.setValue(Boolean.parseBoolean(regex[regexCount + 2]));
									++regexCount;
								}
								if (ss.getValue() instanceof Integer) {
									ss.setValue(Integer.parseInt(regex[regexCount + 2]));
									++regexCount;
								}
								if (ss.getValue() instanceof Double) {
									ss.setValue(Double.parseDouble(regex[regexCount + 2]));
									++regexCount;
								}
								if (ss.getValue() instanceof String) {
									ss.setValue(regex[regexCount + 2]);
									++regexCount;
								}
							}
						}
						if (!(s.getValue() instanceof String)) {
							continue;
						}
						final Setting<String> str = (Setting<String>)s;
						for (final SubSetting ss : str.getSubSettings()) {
							if (ss.getValue() instanceof Boolean) {
								ss.setValue(Boolean.parseBoolean(regex[regexCount + 2]));
								++regexCount;
							}
							if (ss.getValue() instanceof Integer) {
								ss.setValue(Integer.parseInt(regex[regexCount + 2]));
								++regexCount;
							}
							if (ss.getValue() instanceof Double) {
								ss.setValue(Double.parseDouble(regex[regexCount + 2]));
								++regexCount;
							}
							if (ss.getValue() instanceof String) {
								ss.setValue(regex[regexCount + 2]);
								++regexCount;
							}
						}
					}
				}
				++lineIndex;
			}
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveActiveModules() {
		try {
			final File modules = new File(FileUtil.velocity.getAbsolutePath(), "ActiveModules.txt");
			final BufferedWriter bw = new BufferedWriter(new FileWriter(modules));
			for (final Module m : ModuleManager.getModules()) {
				bw.write(m.getName() + ":");
				if (m.isEnabled()) {
					bw.write("true");
				}
				else {
					bw.write("false");
				}
				bw.write("\r\n");
			}
			bw.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadActiveModules() {
		try {
			final File modules = new File(FileUtil.velocity.getAbsolutePath(), "ActiveModules.txt");
			final BufferedReader br = new BufferedReader(new FileReader(modules));
			final List<String> linezz = Files.readAllLines(modules.toPath());
			int lineIndex = 0;
			for (final String line : linezz) {
				final String[] regex = line.split(":");
				final Module m = ModuleManager.getModules().get(lineIndex);
				if (regex[0].startsWith(m.getName()) && Boolean.parseBoolean(regex[1])) {
					m.enable();
				}
				++lineIndex;
			}
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveFriends() {
		try {
			final File friends = new File(FileUtil.velocity.getAbsolutePath(), "Friends.txt");
			final BufferedWriter bw = new BufferedWriter(new FileWriter(friends));
			for (final String s : FriendUtil.getFriends()) {
				bw.write(s);
				bw.write("\r\n");
			}
			bw.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadFriends() {
		try {
			final File friends = new File(FileUtil.velocity.getAbsolutePath(), "Friends.txt");
			final BufferedReader br = new BufferedReader(new FileReader(friends));
			final List<String> linezz = Files.readAllLines(friends.toPath());
			final int lineIndex = 0;
			for (final String line : linezz) {
				FriendUtil.addFriend(line);
			}
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}