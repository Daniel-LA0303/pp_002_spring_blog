package com.mx.dev.blog.spring_001_blog.services;

import org.springframework.data.domain.Page;

import com.mx.dev.blog.spring_001_blog.entities.reply.ReplyEntity;
import com.mx.dev.blog.spring_001_blog.utils.dtos.reply.ReplyCardDTO;
import com.mx.dev.blog.spring_001_blog.utils.dtos.reply.ReplyCreateRequestDTO;
import com.mx.dev.blog.spring_001_blog.utils.exceptions.ServiceException;

public interface ReplyService {

	ReplyCardDTO createReply(ReplyCreateRequestDTO replyCreateRequestDTO) throws ServiceException;

	ReplyEntity getOneReplyOrThrow(Long replyId) throws ServiceException;

	Page<ReplyCardDTO> getRepliesByComment(Long commentId, int page, int size) throws ServiceException;

	ReplyCardDTO updateReply(ReplyCreateRequestDTO replyCreateRequestDTO, Long replyId) throws ServiceException;

}
