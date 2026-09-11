package app.kanup4m.patches.macrofactor.premium

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.string

// RevenueCat CustomerInfoFactory.buildCustomerInfo — verified in com.sbs.train 1.2.5
// smali/classes8. Strategy ported from hoo-dles/morphe-patches (pinned 1.2.1),
// re-verified: const-string order non_subscriptions -> subscriptions intact.
object BuildCustomerInfoFingerprint : Fingerprint(
    name = "buildCustomerInfo",
    definingClass = "/CustomerInfoFactory;",
    filters = listOf(
        string("non_subscriptions"),
        string("subscriptions"),
    )
)
