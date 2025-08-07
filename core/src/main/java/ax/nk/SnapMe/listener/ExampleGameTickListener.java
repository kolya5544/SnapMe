package ax.nk.SnapMe.listener;

import net.labymod.api.event.Phase;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.lifecycle.GameTickEvent;
import ax.nk.SnapMe.SnapMeAddon;

public class ExampleGameTickListener {

  private final SnapMeAddon addon;

  public ExampleGameTickListener(SnapMeAddon addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onGameTick(GameTickEvent event) {
    if (event.phase() != Phase.PRE) {
      return;
    }

    this.addon.logger().info(this.addon.configuration().enabled().get() ? "enabled" : "disabled");
  }
}
