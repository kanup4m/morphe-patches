package app.kanup4m.patches.macrofactor.misc.signature

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch
import app.kanup4m.patches.macrofactor.shared.Constants.COMPATIBILITY_WORKOUTS

// Original Play-store cert hash bytes for com.sbs.train (source: hoo-dles WORKOUTS_SIGNATURE).
// Returned in place of the real cert so signature/tamper checks pass on the resigned APK.
@Suppress("unused")
val spoofSignaturePatch = bytecodePatch(
    name = "MacroFactor Workouts Spoof Signature",
    description = "Spoofs the original signing certificate so signature checks pass."
) {
    compatibleWith(COMPATIBILITY_WORKOUTS)

    execute {
        GetSignatureFingerprint.method.addInstructions(
            0,
            """
                const/16 v0, 0x14
                new-array v0, v0, [B
                fill-array-data v0, :array_sig
                return-object v0

                :array_sig
                .array-data 1
                    68t
                    64t
                    -49t
                    -5t
                    -38t
                    -124t
                    -116t
                    108t
                    -117t
                    -98t
                    2t
                    -124t
                    121t
                    80t
                    116t
                    88t
                    121t
                    -99t
                    81t
                    44t
                .end array-data
            """.trimIndent()
        )
    }
}
