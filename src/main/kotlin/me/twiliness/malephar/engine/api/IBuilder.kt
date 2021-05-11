package me.twiliness.malephar.engine.api

interface IBuilder<out T : IComponent> {
    fun build() : T
}