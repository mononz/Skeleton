package com.mononz.skeleton.data.response

data class SkeletonDataResponse(
    val skeletons: List<SkeletonResponse>?,
    val source: String?
)

data class SkeletonResponse(
    val id: Long?,
    val name: String?,
    val description: String?,
    val image: String?
)
