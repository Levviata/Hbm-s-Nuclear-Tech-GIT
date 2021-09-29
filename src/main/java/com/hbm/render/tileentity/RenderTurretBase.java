package com.hbm.render.tileentity;

import org.lwjgl.opengl.GL11;

import com.hbm.forgefluid.FFUtils;
import com.hbm.lib.Library;
import com.hbm.main.ResourceManager;
import com.hbm.tileentity.turret.TileEntityTurretBaseNT;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public abstract class RenderTurretBase<T extends TileEntityTurretBaseNT> extends TileEntitySpecialRenderer<T> {
	
	@Override
	public boolean isGlobalRenderer(T te){
		return true;
	}
	
	protected void renderConnectors(TileEntityTurretBaseNT turret, boolean power, boolean fluid, Fluid type) {

		bindTexture(ResourceManager.turret_connector_tex);
		Vec3d pos = turret.getHorizontalOffset();
		int x = (int)(turret.getPos().getX() + pos.x);
		int y = turret.getPos().getY();
		int z = (int)(turret.getPos().getZ() + pos.z);

		checkPlug(turret.getWorld(), x - 2, y, z, power, fluid, type, 0, 0, 0);
		checkPlug(turret.getWorld(), x - 2, y, z - 1, power, fluid, type, 0, -1, 0);
		
		checkPlug(turret.getWorld(), x - 1, y, z + 1, power, fluid, type, 0, -1, 90);
		checkPlug(turret.getWorld(), x, y, z + 1, power, fluid, type, 0, 0, 90);

		checkPlug(turret.getWorld(), x + 1, y, z, power, fluid, type, 0, -1, 180);
		checkPlug(turret.getWorld(), x + 1, y, z - 1, power, fluid, type, 0, 0, 180);

		checkPlug(turret.getWorld(), x, y, z - 2, power, fluid, type, 0, -1, 270);
		checkPlug(turret.getWorld(), x - 1, y, z - 2, power, fluid, type, 0, 0, 270);
	}
	
	private void checkPlug(World world, int x, int y, int z, boolean power, boolean fluid, Fluid type, int ox, int oz, int rot) {
		
		if( (power && Library.checkCableConnectables(world, x, y, z)) ||
			(fluid && FFUtils.checkFluidConnectablesMk2(world, new BlockPos(x, y, z), type)) ) {
			
			GL11.glPushMatrix();
			GL11.glRotated(rot, 0, 1, 0);
			GL11.glTranslated(ox, 0, oz);
			ResourceManager.turret_chekhov.renderPart("Connectors");
			GL11.glPopMatrix();
		}
	}
}
