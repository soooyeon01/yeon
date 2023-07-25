package com.spring.service;

import com.spring.domain.LikeDTO;

public interface LikeService {
	int likeUp(LikeDTO dto);
	int likeDown(LikeDTO dto);
	int findLike(LikeDTO dto);
	int getLikeCnt(LikeDTO dto);
}
