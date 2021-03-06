#include canvas:shaders/api/fragment.glsl
#include canvas:shaders/lib/math.glsl
#include canvas:shaders/api/world.glsl

varying vec2 v_noise_uv;

void cv_startFragment(inout cv_FragmentData fragData) {
	float time = cv_renderSeconds();
	if (fragData.spriteColor.r > 0.5f) {
		fragData.emissivity = abs(sin(time))+0.25;
		fragData.ao = false;
		fragData.diffuse = false;
	}
}