/*
 * Copyright (c) 2019, xzact <https://github.com/xzact>
 * Copyright (c) 2019, ganom <https://github.com/Ganom>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.runelite.client.plugins.coxhelper;

import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("Cox")
public interface CoxConfig extends Config
{
	@ConfigItem(
		position = 1,
		keyName = "muttadile",
		name = "Muttadile Marker",
		description = "Places an overlay around muttadiles showing their melee range."
	)
	default boolean muttadile()
	{
		return true;
	}

	@ConfigItem(
		position = 2,
		keyName = "tekton",
		name = "Tekton Marker",
		description = "Places an overlay around Tekton showing his melee range."
	)
	default boolean tekton()
	{
		return true;
	}

	@ConfigItem(
		position = 3,
		keyName = "tektonTickCounter",
		name = "Tekton Tick Counters",
		description = "Counts down current phase timer, and attack ticks."
	)
	default boolean tektonTickCounter()
	{
		return true;
	}

	@ConfigItem(
		position = 4,
		keyName = "guardians",
		name = "Guardians Overlay",
		description = "Places an overlay near Guardians showing safespot."
	)
	default boolean guardians()
	{
		return true;
	}

	@ConfigItem(
		position = 5,
		keyName = "guardinTickCounter",
		name = "Guardians Tick Timing",
		description = "Places an overlay on Guardians showing attack tick timers."
	)
	default boolean guardinTickCounter()
	{
		return true;
	}

	@ConfigItem(
		position = 6,
		keyName = "vangHighlight",
		name = "Highlight Vanguards",
		description = "Color is based on their attack style."
	)
	default boolean vangHighlight()
	{
		return true;
	}

	@ConfigItem(
		position = 7,
		keyName = "vangHealth",
		name = "Show Vanguards Current HP",
		description = "This will create an infobox with vanguards current hp."
	)
	default boolean vangHealth()
	{
		return true;
	}

	@ConfigItem(
		position = 8,
		keyName = "prayAgainstOlm",
		name = "Olm Show Prayer",
		description = "Shows what prayer to use during olm."
	)
	default boolean prayAgainstOlm()
	{
		return true;
	}

	@Range(
		min = 40,
		max = 100
	)
	@ConfigItem(
		position = 11,
		keyName = "prayAgainstOlmSize",
		name = "Olm Prayer Size",
		description = "Change the Size of the Olm Infobox."
	)
	default int prayAgainstOlmSize()
	{
		return 40;
	}

	@ConfigItem(
		position = 12,
		keyName = "timers",
		name = "Olm Show Burn/Acid Timers",
		description = "Shows tick timers for burns/acids."
	)
	default boolean timers()
	{
		return true;
	}

	@ConfigItem(
		position = 13,
		keyName = "tpOverlay",
		name = "Olm Show Teleport Overlays",
		description = "Shows Overlays for targeted teleports."
	)
	default boolean tpOverlay()
	{
		return true;
	}

	@ConfigItem(
		position = 14,
		keyName = "olmTick",
		name = "Olm Tick Counter",
		description = "Show Tick Counter on Olm"
	)
	default boolean olmTick()
	{
		return true;
	}

	@ConfigItem(
		position = 16,
		keyName = "muttaColor",
		name = "Muttadile Tile Color",
		description = "Change hit area tile color for muttadiles"
	)
	default Color muttaColor()
	{
		return new Color(0, 255, 99);
	}

	@ConfigItem(
		position = 17,
		keyName = "guardColor",
		name = "Guardians Tile Color",
		description = "Change safespot area tile color for Guardians"
	)
	default Color guardColor()
	{
		return new Color(0, 255, 99);
	}

	@ConfigItem(
		position = 18,
		keyName = "tektonColor",
		name = "Tekton Tile Color",
		description = "Change hit area tile color for Tekton"
	)
	default Color tektonColor()
	{
		return new Color(193, 255, 245);
	}

	@ConfigItem(
		position = 19,
		keyName = "burnColor",
		name = "Burn Victim Color",
		description = "Changes tile color for burn victim."
	)
	default Color burnColor()
	{
		return new Color(255, 100, 0);
	}

	@ConfigItem(
		position = 20,
		keyName = "acidColor",
		name = "Acid Victim Color",
		description = "Changes tile color for acid victim."
	)
	default Color acidColor()
	{
		return new Color(69, 241, 44);
	}

	@ConfigItem(
		position = 21,
		keyName = "tpColor",
		name = "Teleport Target Color",
		description = "Changes tile color for teleport target."
	)
	default Color tpColor()
	{
		return new Color(193, 255, 245);
	}

	@Range(
		min = 9,
		max = 20
	)
	@ConfigItem(
		position = 24,
		keyName = "textSize",
		name = "Text Size",
		description = "Text Size for Timers."
	)
	default int textSize()
	{
		return 14;
	}

	@ConfigItem(
		position = 25,
		keyName = "shadows",
		name = "Shadows",
		description = "Adds Shadows to text."
	)
	default boolean shadows()
	{
		return true;
	}
}
