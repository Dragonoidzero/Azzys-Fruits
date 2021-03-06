package azzy.fabric.forgottenfruits.registry;

import azzy.fabric.forgottenfruits.generated.CindermoteFeature;
import azzy.fabric.forgottenfruits.generated.JellyPearFeature;
import azzy.fabric.forgottenfruits.generated.PlantGen;
import azzy.fabric.forgottenfruits.generated.VompollolowmFeature;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import static azzy.fabric.forgottenfruits.ForgottenFruits.MOD_ID;
import static azzy.fabric.forgottenfruits.registry.CropRegistry.CLOUD_BERRY_WILD;

public class GeneratedRegistry {

    public static Feature<DefaultFeatureConfig> register(String name, PlantGen item) {
        Registry.register(Registry.FEATURE, new Identifier(MOD_ID, name), item);
        return item;
    }

    private static final Feature<DefaultFeatureConfig> CINDERMOTE_FIELD = Registry.register(Registry.FEATURE, new Identifier(MOD_ID, "cindermote_field"), new CindermoteFeature(DefaultFeatureConfig.CODEC));
    private static final Feature<DefaultFeatureConfig> VOMPOLLOLOWM_FIELD = Registry.register(Registry.FEATURE, new Identifier(MOD_ID, "vompollolowm_field"), new VompollolowmFeature(DefaultFeatureConfig.CODEC));
    private static final Feature<DefaultFeatureConfig> JELLY_PEAR_FIELD = Registry.register(Registry.FEATURE, new Identifier(MOD_ID, "jelly_pear_field"), new JellyPearFeature(DefaultFeatureConfig.CODEC));

    public static void init() {
        //Biomes.TAIGA.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig.Builder(new WeightedBlockStateProvider().addState(CLOUD_BERRY_WILD.getDefaultState(), 1), new SimpleBlockPlacer()).tries(30).spreadX(4).spreadZ(4).build()).createDecoratedFeature(Decorator.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new CountChanceDecoratorConfig(1, 0.05f))));
        //Biomes.NETHER_WASTES.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, CINDERMOTE_FIELD.configure(new DefaultFeatureConfig()).createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(50))));
        //Biomes.MOUNTAINS.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, VOMPOLLOLOWM_FIELD.configure(new DefaultFeatureConfig()).createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(100))));
        //Biomes.BEACH.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, JELLY_PEAR_FIELD.configure(new DefaultFeatureConfig()).createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(25))));
        //Biomes.WARM_OCEAN.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, JELLY_PEAR_FIELD.configure(new DefaultFeatureConfig()).createDecoratedFeature(Decorator.CHANCE_HEIGHTMAP.configure(new ChanceDecoratorConfig(3))));
    }
}
