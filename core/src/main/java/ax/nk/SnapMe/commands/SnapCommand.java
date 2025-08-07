package ax.nk.SnapMe.commands;

import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;

public class SnapCommand extends Command {

  public SnapCommand() {
    super("snap", "snapme");
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    var mc = labyAPI.minecraft();
    var player = mc.getClientPlayer();
    var pos = player.position();

    var curX = pos.getX();
    var curZ = pos.getZ();

    // snap to closest 0.5
    double snappedX = Math.round(curX * 2.0) / 2.0;
    double snappedZ = Math.round(curZ * 2.0) / 2.0;

    pos.setX(snappedX - curX);
    pos.setZ(snappedZ - curZ);

    float curYaw = player.getRotationYaw();
    float curPitch = player.getRotationPitch();

    // yaw -> multiples of 45deg (-180 ... 180)
    float snappedYaw = (float) (Math.round(curYaw / 45.0) * 45.0);
    if (snappedYaw > 180)
      snappedYaw -= 360;
    if (snappedYaw <= -180)
      snappedYaw += 360;

    // pitch -> one of −90, −45, 0, 45, 90
    float snappedPitch = (float) Math.max(-90,
        Math.min(90, Math.round(curPitch / 45.0) * 45.0));

    player.setRotationYaw(snappedYaw);
    player.setRotationPitch(snappedPitch);

    player.setBodyRotationY(snappedYaw);
    player.setHeadRotationY(snappedYaw);

    this.displayMessage(
        Component.translatable(
            "snapme.snappedtext",
            Component.text(
                snappedX
            ),
            Component.text(
                snappedZ
            ),
            Component.text(
                snappedYaw
            ),
            Component.text(
                snappedPitch
            )
        ).color(NamedTextColor.GOLD)
    );
    return true;
  }
}
