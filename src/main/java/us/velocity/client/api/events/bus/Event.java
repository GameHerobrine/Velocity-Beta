package us.velocity.client.api.events.bus;

public class Event implements Cancellable
{
    private boolean cancelled;

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
    
    public void cancel() {
        this.cancelled = true;
    }
}
