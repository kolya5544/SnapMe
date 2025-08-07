package ax.nk.SnapMe;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import ax.nk.SnapMe.commands.SnapCommand;

@AddonMain
public class SnapMeAddon extends LabyAddon<Configuration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.registerCommand(new SnapCommand());

    this.logger().info("Enabled the Addon");
  }

  @Override
  protected Class<Configuration> configurationClass() {
    return Configuration.class;
  }
}
