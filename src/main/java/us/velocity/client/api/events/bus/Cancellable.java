package us.velocity.client.api.events.bus;

public interface Cancellable
{
    boolean isCancelled();

    void setCancelled(boolean cancel);
}

