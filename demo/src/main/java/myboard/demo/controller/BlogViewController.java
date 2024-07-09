package myboard.demo.controller;
import lombok.RequiredArgsConstructor;
import myboard.demo.domain.Board;
import myboard.demo.dto.board.boardListViewResponse;
import myboard.demo.dto.board.boardViewResponse;
import myboard.demo.dto.reply.ReplyResponse;
import myboard.demo.service.BoardService;
import myboard.demo.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
    private final BoardService boardService;
    private final ReplyService replyService;
    @GetMapping("/new-board")
    public String newArticle(@RequestParam(required = false)Long id, Model model){
        if(id==null)
            model.addAttribute("board",new boardViewResponse());
        else{
            Board board= boardService.findById(id);
            model.addAttribute("board",new boardViewResponse(board));
        }
        return "newBoard";
    }
    @GetMapping("/boards")
    public String getArticles(Model model){
        List<boardListViewResponse> boards = boardService.findAll().stream()
                .map(boardListViewResponse::new)
                .toList();
        model.addAttribute("boards",boards);//블로그 글 리스트 저장
        return "articleList";//articleList.html 라는 뷰 조회. resource/templates/articleList.html을 찾도록 뷰의 이름을 적어준 것.
    }
    @GetMapping("/boards/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        Board article = boardService.findById(id);
        model.addAttribute("article",new boardViewResponse(article));//화면에서 사용할 모델에 데이터를 저장한다.
        List<ReplyResponse> replies = replyService.findAll(id).stream()
                .map(ReplyResponse::new).toList();
        if (replies != null && !replies.isEmpty()) {
            model.addAttribute("replies", replies);
        }
        return "article";//보여줄 화면의 템플릿 이름을 반환한다.
    }
}
