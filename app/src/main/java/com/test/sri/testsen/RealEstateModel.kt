package com.test.sri.testsen

/**
 * Created by Myworld on 24/03/2018.
 */


//  Json model class
data class RealEstateModel(
        val data: List<Data>
)

data class Data(
		val id: Int,
		val is_premium: Boolean,
		val state: String,
		val title: String,
		val bedrooms: Int,
		val bathrooms: Int,
		val carspots: Int,
		val description: String,
		val price: Double,
		val owner: Owner,
		val location: Location,
		val photo: Photo
)

data class Photo(
		val id: Int,
		val default: Boolean,
		val image: Image
)

data class Image(
		val url: String,
		val small: ImageSmall,
		val medium: ImageMedium,
		val large: ImageLarge
)

data class ImageMedium(
		val url: String
)

data class ImageSmall(
		val url: String
)

data class ImageLarge(
		val url: String
)

data class Location(
		val id: Int,
		val address_1: String,
		val address_2: String,
		val suburb: String,
		val state: String,
		val postcode: String,
		val country: String,
		val latitude: Double,
		val longitude: Double,
		val full_address: String
)

data class Owner(
		val id: Int,
		val first_name: String,
		val last_name: String,
		val email: String,
		val avatar: Avatar
)

data class Avatar(
		val url: String,
		val small: Small,
		val medium: Medium,
		val large: Large,
		val profile: Profile
)

data class Medium(
		val url: String
)

data class Small(
		val url: String
)

data class Large(
		val url: String
)

data class Profile(
		val url: String
)