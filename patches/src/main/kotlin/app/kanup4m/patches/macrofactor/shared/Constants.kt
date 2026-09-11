package app.kanup4m.patches.macrofactor.shared

import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

object Constants {
    val COMPATIBILITY_WORKOUTS = Compatibility(
        name = "MacroFactor Workouts",
        packageName = "com.sbs.train",
        apkFileType = ApkFileType.XAPK,
        appIconColor = 0x000000,
        targets = listOf(
            AppTarget(version = "1.2.5")
        )
    )
}
