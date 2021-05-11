package me.twiliness.malephar.conf

data class UserModel(val qq: Long, val password: String)

data class ConfigModel(val user: UserModel)
