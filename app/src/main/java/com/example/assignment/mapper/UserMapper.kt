package com.example.assignment.mapper

import com.example.assignment.data.local.entity.UserEntity
import com.example.assignment.data.remote.dto.UserListDTO
import com.example.assignment.domain.model.User

fun UserListDTO.toUserEntity(): List<UserEntity>? = results?.map { it ->
    UserEntity(
        id = (it.id?.name + " " + it.id?.value),
        cell = it.cell,
        email = it.email,
        gender = it.gender,
        address = it.location?.street?.number.toString() + ", " +
                it.location?.street?.name + ", " +
                it.location?.city + ", " +
                it.location?.state + ", " +
                it.location?.country,
        name = it.name?.first + it.name?.last,
        phone = it.phone,
        image = it.picture?.large
    )
}

fun UserEntity.toUser(): User = User(
    id = id,
    cell = cell,
    email = email,
    gender = gender,
    address = address,
    name = name,
    phone = phone,
    image = image,
    status = status
)

fun User.toUserEntity(): UserEntity = UserEntity(
    id = id.toString(),
    cell = cell,
    email = email,
    gender = gender,
    address = address,
    name = name,
    phone = phone,
    image = image,
    status = status
)