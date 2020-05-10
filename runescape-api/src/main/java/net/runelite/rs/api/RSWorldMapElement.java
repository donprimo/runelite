package net.runelite.rs.api;

import net.runelite.api.MapElementConfig;
import net.runelite.api.SpritePixels;
import net.runelite.mapping.Import;

public interface RSWorldMapElement extends RSDualNode, MapElementConfig
{
	@Import("getSpriteBool")
	SpritePixels getMapIcon(boolean var1);
}
