service RedisWrapperApplicationIface {
	string get(1:string strRequest);
	string put(1:string strRequest);
	string lpush(1:string strRequest);
	string lrange(1:string strRequest);
	string llen(1:string strRequest);
	string sadd(1:string strRequest);
	string srem(1:string strRequest);
	string smembers(1:string strRequest);
	string sismember(1:string strRequest);
	string scard(1:string strRequest);
	string incr(1:string strRequest);
	string exists(1:string strRequest);
	string expire(1:string strRequest);
	string hgetAll(1:string strReqeust);
	string hmset(1:string strReqeust);
	string hmget(1:string strReqeust);
	string zadd(1:string strReqeust);
	string zrem(1:string strReqeust);
	string zrangeByScore(1:string strReqeust);
	string remove(1:string strReqeust);
}
