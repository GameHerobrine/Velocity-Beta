package us.velocity.client;

import us.velocity.client.api.gui.util.FileUtil;

public class BaseShutdownHook extends Thread
{
    @Override
    public void run() {
        FileUtil.saveAll();
        Velocity.LOGGER.info("Saving everything...");
    }
}