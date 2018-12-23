package com.tiger.apigateway.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService<K, V> implements IRedisService<K, V> {
	
	@Autowired
	private RedisTemplate<K, V> redisTemplate;

	@Override
	public void add(K key, V value) {
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public V get(K key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	@Override
	public void remove(K key) {
		redisTemplate.delete(key);
	}

}
