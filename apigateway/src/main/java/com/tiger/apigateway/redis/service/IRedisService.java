package com.tiger.apigateway.redis.service;

public interface IRedisService<K,V> {
   void add(K key,V value);
   V get(K key);
   void remove(K key);
}
