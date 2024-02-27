package com.doctor.mapper;

/**
 *
 * @param <T> Entity
 * @param <V> Request
 * @param <I> Response
 */
public interface Mapper<T, V, I> {
    T mapToEntity(V request, MapperPattern pattern);
    I mapToResponse(T entity);
}
