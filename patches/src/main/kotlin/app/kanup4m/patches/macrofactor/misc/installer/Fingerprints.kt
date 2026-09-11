package app.kanup4m.patches.macrofactor.misc.installer

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.methodCall

// package_info_plus bridge: reports installer store to Dart.
// Verified in com.sbs.train 1.2.5 smali/classes8.
object GetInstallerPackageNameFingerprint : Fingerprint(
    name = "getInstallerPackageName",
    definingClass = "Ldev/fluttercommunity/plus/packageinfo/PackageInfoPlugin;",
    returnType = "Ljava/lang/String;",
    parameters = listOf(),
    filters = listOf(
        methodCall(
            definingClass = "Landroid/content/Context;",
            name = "getPackageManager"
        ),
        methodCall(
            definingClass = "Landroid/content/Context;",
            name = "getPackageName"
        )
    )
)

// Same plugin: reports build-cert SHA256 to Dart.
// Verified in com.sbs.train 1.2.5 smali/classes8.
object GetBuildSignatureFingerprint : Fingerprint(
    name = "getBuildSignature",
    definingClass = "Ldev/fluttercommunity/plus/packageinfo/PackageInfoPlugin;",
    returnType = "Ljava/lang/String;",
    parameters = listOf("Landroid/content/pm/PackageManager;")
)
