package app.kanup4m.patches.macrofactor.misc.installer

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.kanup4m.patches.macrofactor.shared.Constants.COMPATIBILITY_WORKOUTS

// Original Play-store cert SHA-256 of com.sbs.train (from the unmodified 1.2.5 APK).
private const val ORIGINAL_CERT_SHA256 =
    "b527339ff89d5e2a97ee76d93a7e77628fac84fa2e75c174cde8cfeca1e3c034"

// Makes Dart believe the app came from Google Play with its original certificate.
// Targets the "Device integrity can't be verified ... wasn't installed from the
// Google Play Store" login gate.
@Suppress("unused")
val spoofInstallerPatch = bytecodePatch(
    name = "MacroFactor Workouts Spoof Installer",
    description = "Reports Play Store as installer and the original certificate hash to the app. No root required."
) {
    compatibleWith(COMPATIBILITY_WORKOUTS)

    execute {
        GetInstallerPackageNameFingerprint.method.addInstructions(
            0,
            """
                const-string v0, "com.android.vending"
                return-object v0
            """.trimIndent()
        )
        GetBuildSignatureFingerprint.method.addInstructions(
            0,
            """
                const-string v0, "$ORIGINAL_CERT_SHA256"
                return-object v0
            """.trimIndent()
        )
    }
}
