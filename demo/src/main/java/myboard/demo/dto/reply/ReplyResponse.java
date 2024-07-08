package myboard.demo.dto.reply;

import myboard.demo.domain.Reply;
import myboard.demo.domain.User;

import java.time.LocalDateTime;

public class ReplyResponse {
    private Long id;
    private String content;
    private LocalDateTime regdate = LocalDateTime.now();
    private LocalDateTime deldate = LocalDateTime.now();
    private User user;
    private Long userId;
    private Long ArticleId;

    /* Entity -> Dto*/
    public CommentResponse(Reply reply) {
        this.id = reply.getReplyId();
        this.content = reply.getContent();
        this.regdate = reply.getRegdate();
        this.deldate = reply.getDeldate();
        this.ArticleId = reply.getArticle().getId();
        this.user = reply.getUser().getUser();
        this.userId = reply.getUser().getId();
    }
}
