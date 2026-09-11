package app.kanup4m.patches.macrofactor.misc.signature

import app.morphe.patcher.Fingerprint

// Verified in com.sbs.train 1.2.5 smali/classes6:
// public static getPackageCertificateHashBytes(Context, String)[B, .registers 3
object GetSignatureFingerprint : Fingerprint(
    name = "getPackageCertificateHashBytes",
    definingClass = "/AndroidUtilsLight;",
    returnType = "[B"
)
