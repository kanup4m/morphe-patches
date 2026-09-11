package app.kanup4m.patches.macrofactor.premium

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.kanup4m.patches.macrofactor.shared.Constants.COMPATIBILITY_WORKOUTS

// Fabricated RevenueCat customer body: lifetime-style premium until 2027-06-01.
// Entitlement IDs + schema ported from hoo-dles EnablePremiumPatch extension;
// injected as raw smali so no extension/root infrastructure is required.
private const val FABRICATED_BODY =
    "{\"request_date\":\"2026-09-11T00:00:00Z\"," +
        "\"subscriber\":{" +
        "\"original_app_user_id\":\"mf-patched\"," +
        "\"first_seen\":\"2025-06-01T00:00:00Z\"," +
        "\"original_purchase_date\":\"2025-06-01T00:00:00Z\"," +
        "\"non_subscriptions\":{}," +
        "\"subscriptions\":{" +
        "\"com.sbs.diet.1y0599.2w0\":{" +
        "\"original_purchase_date\":\"2025-06-01T00:00:00Z\"," +
        "\"purchase_date\":\"2025-06-01T00:00:00Z\"," +
        "\"expires_date\":\"2027-06-01T00:00:00Z\"," +
        "\"is_sandbox\":false," +
        "\"ownership_type\":\"PURCHASED\"," +
        "\"store\":\"app_store\"," +
        "\"period_type\":\"normal\"}," +
        "\"com.sbs.train.subscription.1\":{" +
        "\"original_purchase_date\":\"2025-06-01T00:00:00Z\"," +
        "\"purchase_date\":\"2025-06-01T00:00:00Z\"," +
        "\"expires_date\":\"2027-06-01T00:00:00Z\"," +
        "\"is_sandbox\":false," +
        "\"ownership_type\":\"PURCHASED\"," +
        "\"store\":\"app_store\"," +
        "\"period_type\":\"normal\"}}," +
        "\"entitlements\":{" +
        "\"subscription\":{" +
        "\"product_identifier\":\"com.sbs.diet.1y0599.2w0\"," +
        "\"purchase_date\":\"2025-06-01T00:00:00Z\"," +
        "\"expires_date\":\"2027-06-01T00:00:00Z\"," +
        "\"grace_period_expires_date\":null}," +
        "\"subscription_workouts\":{" +
        "\"product_identifier\":\"com.sbs.train.subscription.1\"," +
        "\"purchase_date\":\"2025-06-01T00:00:00Z\"," +
        "\"expires_date\":\"2027-06-01T00:00:00Z\"," +
        "\"grace_period_expires_date\":null}}}}"

@Suppress("unused")
val macrofactorWorkoutsPremiumPatch = bytecodePatch(
    name = "MacroFactor Workouts Premium",
    description = "Unlocks premium by serving a fabricated RevenueCat customer with active entitlements until 2027. No root required."
) {
    compatibleWith(COMPATIBILITY_WORKOUTS)

    execute {
        // Smali string literals require inner quotes escaped as \".
        val smaliBody = FABRICATED_BODY.replace("\"", "\\\"")
        BuildCustomerInfoFingerprint.method.addInstructions(
            0,
            """
                const-string v0, "$smaliBody"
                new-instance v1, Lorg/json/JSONObject;
                invoke-direct {v1, v0}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V
                move-object p1, v1
            """.trimIndent()
        )
    }
}
