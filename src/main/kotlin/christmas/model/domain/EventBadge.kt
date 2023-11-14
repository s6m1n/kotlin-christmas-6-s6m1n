package christmas.model.domain

enum class EventBadge(private val badgeName: String) {
    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    fun getName() = this.badgeName
}