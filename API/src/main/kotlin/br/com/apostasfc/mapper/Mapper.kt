package br.com.apostasfc.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}