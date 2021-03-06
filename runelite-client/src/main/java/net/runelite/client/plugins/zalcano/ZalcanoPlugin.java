/*
 * Copyright (c) 2019, Hexagon <hexagon@fking.work>
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
 *
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
package net.runelite.client.plugins.zalcano;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.GameObject;
import static net.runelite.api.GraphicID.GRAPHICS_OBJECT_ROCKFALL;
import net.runelite.api.GraphicsObject;
import net.runelite.api.NPC;
import net.runelite.api.NpcID;
import static net.runelite.api.NpcID.ZALCANO;
import net.runelite.api.ObjectID;
import net.runelite.api.Projectile;
import static net.runelite.api.ProjectileID.ZALCANO_PROJECTILE_FIREBALL;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GraphicsObjectCreated;
import net.runelite.api.events.NpcChanged;
import net.runelite.api.events.NpcDespawned;
import net.runelite.api.events.NpcSpawned;
import net.runelite.api.events.ProjectileMoved;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
	name = "Zalcano",
	description = "Assistance for the Zalcano fight",
	enabledByDefault = false
)
public class ZalcanoPlugin extends Plugin
{
	private static final int ZALCANO_WEAKENED = NpcID.ZALCANO_9050;
	private static final int GOLEM = NpcID.GOLEM_9051;

	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private ZalcanoOverlay overlay;

	@Getter
	private LocalPoint targetedGlowingRock;
	@Getter
	private int targetedGlowingRockEndCycle;
	private WorldPoint lastGlowingRock;

	@Getter
	private final List<GraphicsObject> rocks = new ArrayList<>();

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
		rocks.clear();
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onGraphicsObjectCreated(GraphicsObjectCreated graphicsObjectCreated)
	{
		GraphicsObject graphicsObject = graphicsObjectCreated.getGraphicsObject();
		if (graphicsObject.getId() == GRAPHICS_OBJECT_ROCKFALL)
		{
			rocks.add(graphicsObject);
		}
	}

	@Subscribe
	public void onNpcSpawned(NpcSpawned event)
	{
		final NPC npc = event.getNpc();

		if (npc.getId() == GOLEM)
		{
			client.setHintArrow(npc);
		}
	}

	@Subscribe
	public void onNpcDespawned(NpcDespawned event)
	{
		final NPC npc = event.getNpc();

		if (npc.getId() == ZALCANO_WEAKENED)
		{
			client.clearHintArrow();
		}
		else if (npc.getId() == GOLEM)
		{
			if (lastGlowingRock != null)
			{
				client.setHintArrow(lastGlowingRock);
			}
		}
	}

	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned event)
	{
		final GameObject gameObject = event.getGameObject();

		if (gameObject.getId() == ObjectID.ROCK_FORMATION_GLOWING)
		{
			WorldPoint worldLocation = lastGlowingRock = gameObject.getWorldLocation();
			client.setHintArrow(worldLocation);
		}
	}

	@Subscribe
	public void onNpcChanged(NpcChanged event)
	{
		final NPC npc = event.getNpc();

		if (npc.getId() == ZALCANO_WEAKENED)
		{
			client.setHintArrow(npc);
		}
		else if (npc.getId() == ZALCANO)
		{
			if (lastGlowingRock != null)
			{
				client.setHintArrow(lastGlowingRock);
			}
		}
	}

	@Subscribe
	public void onProjectileMoved(ProjectileMoved event)
	{
		final Projectile projectile = event.getProjectile();

		if (projectile.getId() == ZALCANO_PROJECTILE_FIREBALL)
		{
			targetedGlowingRock = event.getPosition();
			targetedGlowingRockEndCycle = projectile.getEndCycle();
		}
	}
}
