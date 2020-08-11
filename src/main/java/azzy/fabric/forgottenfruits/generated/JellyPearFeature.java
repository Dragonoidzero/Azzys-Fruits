package azzy.fabric.forgottenfruits.generated;

import azzy.fabric.forgottenfruits.util.interaction.PlantType;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

import static azzy.fabric.forgottenfruits.ForgottenFruits.FFLog;
import static azzy.fabric.forgottenfruits.registry.CropRegistry.JELLY_PEAR_WILD;

public class JellyPearFeature extends Feature<DefaultFeatureConfig> {
    public JellyPearFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(ServerWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator generator, Random random, BlockPos pos, DefaultFeatureConfig config) {

        if(world.getBiome(pos) == Biomes.BEACH){
            return this.generatePears(pos, world, random, 8, random.nextInt(12));
        }
        else if(world.getBiome(pos) == Biomes.WARM_OCEAN){
            return this.generatePears(pos, world, random, 12, random.nextInt(16));
        }

        return false;
    }

    private boolean generatePears(BlockPos pos, ServerWorldAccess world, Random random, int maxSpread, int count){
        boolean success = false;
        BlockPos floor = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR, pos);
        for(; count > 0; count--){
            BlockPos blockPos = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR, floor.add(random.nextInt(maxSpread), 0, random.nextInt(maxSpread)));
            BlockState state = world.getBlockState(blockPos);
            BlockState ground = world.getBlockState(blockPos.down());
            if(state.isOf(Blocks.WATER) && PlantType.PLANTTYPE.AQUATIC.contains(ground.getBlock())) {
                world.setBlockState(blockPos, JELLY_PEAR_WILD.getDefaultState(), 3);
                success = true;
            }
        }
        return success;
    }
}
