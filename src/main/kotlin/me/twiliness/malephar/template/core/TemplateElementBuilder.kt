package me.twiliness.malephar.template.core

interface TemplateElementBuilder<T : TemplateElement> {
    fun build() : T
}